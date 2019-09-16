import time

import nltk
import spacy
from nltk.corpus import wordnet, stopwords
from nltk.stem import PorterStemmer, WordNetLemmatizer

spacy_nlp = spacy.load('en')
nltk_stopwords = stopwords.words('english')
spacy_stopwords = spacy_nlp.Defaults.stop_words
stopwords = list(set().union(spacy_stopwords, list(nltk_stopwords)))
ps = PorterStemmer()
wnl = WordNetLemmatizer()


def to_lower(text):
    return text.lower()


def word_tokenize(text):
    return nltk.word_tokenize(text)


def remove_stops(word_list):
    return list(filter(lambda w: w not in stopwords, word_list))


def synonymify(word_list):
    result = []
    for w in word_list:
        for syn in wordnet.synsets(w):
            for l in syn.lemmas():
                result.append(l.name())
    return list(dict.fromkeys(result))


def stem(word_list):
    return [ps.stem(w) for w in word_list]


def lemmatize(word_list):
    return [wnl.lemmatize(w) for w in word_list]



def get_processed_words(text):
    with_lowercase = to_lower(text)
    tokenized = word_tokenize(with_lowercase)
    with_removed_stops = remove_stops(tokenized)
    synonymified = synonymify(with_removed_stops)
    lemmatized = lemmatize(synonymified)
    stemmed = stem(synonymified)
    all = synonymified + lemmatized + stemmed
    return list(dict.fromkeys(all))

def edit_distance_matching(text):
    words = get_processed_words(text)
    categories = get_categories()
    return all

test_text = "Hello this is Jacek Duszenko. There was accident on the highway, I'm in Smolec near Wrocław. Two cars crashed and there is unconscious man who bleeds from his head. Please help me"

start_time = time.time()

print(edit_distance_matching(test_text))
end_time = time.time() - start_time
in_ms = end_time * 1000
print('took: ' + str(in_ms))
