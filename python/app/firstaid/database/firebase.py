from firebase_admin import firestore
from google.cloud import translate

from firstaid.model.call_transcript import CallTranscript
from firstaid.nlp.nlp_processor import EditDistanceMatcher

translate_client = translate.Client()
processor = EditDistanceMatcher()
db = firestore.client()


def on_speech_recognition(col_snapshot, changes, read_time):
    print('Callback received, full_result collection has changed.')
    for change in changes:
        if change.type.name == 'ADDED':
            model = extract_to_model(change)
            translation = translate_client.translate(model.text, target_language='en')
            print('tranlation is' + translation['translatedText'])
            translated_text = translation['translatedText']
            matched_categories = processor.match(translated_text)
            write_analysis_result_to_firebase(model, matched_categories)


def extract_to_model(change):
    change_dict = change.document.to_dict()
    text = change_dict['text']
    accident_id = change_dict['accidentId']
    print('Change happened. Text: {}, accidentId: {}'.format(text, accident_id))
    return CallTranscript(text, accident_id)


def write_analysis_result_to_firebase(model, matched_categories):
    doc_ref = db.collection('analysis_result').document(model.accident_id)
    doc_ref.set({
        'matched_categories': matched_categories
    })


def initialize_database_callbacks():
    full_result_collection_query = db.collection('speech_recognition_result')
    full_result_collection_query.on_snapshot(on_speech_recognition)
