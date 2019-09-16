import sys

import firebase_admin
from firebase_admin import credentials
from firebase_admin import firestore
from flask import Flask, request, make_response, jsonify

cred = credentials.Certificate(sys.argv[1])
firebase_admin.initialize_app(cred)
firestore = firestore.client()

from firstaid.database.firebase import initialize_database_callbacks
from firstaid.model.call_transcript import CallTranscript
from firstaid.nlp.nlp_processor import EditDistanceMatcher
app = Flask(__name__)


@app.route('/api/first-aid', methods=['GET'])
def get_firstaid_assistance():
    dataAsJson = request.get_json()
    transcript_text = dataAsJson.get('transcript')
    if transcript_text is None:
        return make_response(jsonify({'response:': 'Request body must contain transcript key'}, 400))
    transcript = CallTranscript(transcript_text)
    return make_response(EditDistanceMatcher().get_assistance(transcript), 200)


if __name__ == '__main__':
    initialize_database_callbacks()
    app.run()
