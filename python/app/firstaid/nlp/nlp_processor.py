import nltk

import spacy
from nltk.corpus import wordnet, stopwords
from nltk.stem import PorterStemmer, WordNetLemmatizer
from firebase_admin import firestore
import time
from firstaid.model.category import Category

db = firestore.client()
spacy_nlp = spacy.load('en')
nltk_stopwords = stopwords.words('english')
spacy_stopwords = spacy_nlp.Defaults.stop_words
stopwords = list(set().union(spacy_stopwords, list(nltk_stopwords)))
ps = PorterStemmer()
wnl = WordNetLemmatizer()


def matches(tag, word):
    return nltk.edit_distance(tag, word) == 0  # for now lets keep it strict


class EditDistanceMatcher:
    def match(self, text):
        start = time.time()
        matched_categories = []
        words = self._get_processed_words(text)
        categories = self._get_categories()
        for cat in categories:
            if cat.category_name is 'emergency':
                matched_categories.append(cat)
            else:
                self._match_words_to_cat(words, cat, matched_categories)
        print(' algo took ' + str(1000 * (time.time() - start)) + ' ms \n')
        print('matched categories are ' + str(matched_categories))
        return matched_categories

    def _match_words_to_cat(self, words, cat, matched_categories):
        for w in words:
            for tag in cat.tags_en:
                if matches(tag, w):
                    print('matched word ' + w + ' with tag ' + tag + ' in category ' + cat.category_name)
                    matched_categories.append(cat.category_name)
                    return True
        return False

    def _get_processed_words(self, text):
        with_lowercase = self._to_lower(text)
        tokenized = self._word_tokenize(with_lowercase)
        with_removed_stops = self._remove_stops(tokenized)
        synonymified = self._synonymify(with_removed_stops)
        lemmatized = self._lemmatize(synonymified)
        stemmed = self._stem(synonymified)
        all = synonymified + lemmatized + stemmed
        return list(dict.fromkeys(all))

    def _get_categories(self):
        result = []
        docs = db.collection('case').stream()
        for doc in docs:
            docDict = doc.to_dict()
            result.append(Category(doc.id, docDict['tags_en']))
        return result

    def _to_lower(self, text):
        return text.lower()

    def _word_tokenize(self, text):
        return nltk.word_tokenize(text)

    def _remove_stops(self, word_list):
        return list(filter(lambda w: w not in stopwords, word_list))

    def _synonymify(self, word_list):
        result = []
        for w in word_list:
            for syn in wordnet.synsets(w):
                for l in syn.lemmas():
                    result.append(l.name())
        return list(dict.fromkeys(result))

    def _stem(self, word_list):
        return [ps.stem(w) for w in word_list]

    def _lemmatize(self, word_list):
        return [wnl.lemmatize(w) for w in word_list]
