package pl.kasztany.orlenfirstaid.service;

import android.util.Log;

import net.gotev.speech.SpeechDelegate;

import java.util.List;

public class SpeechRecognitionDelegate implements SpeechDelegate {

    private List<String> recognizedWords;
    private String accidentId;

    SpeechRecognitionDelegate(List<String> recognizedWords, String accidentId) {
        this.recognizedWords = recognizedWords;
        this.accidentId = accidentId;
    }

    @Override
    public void onStartOfSpeech() {
        Log.i("INFO", "start of speech recognition for accident id : " + accidentId);
    }

    @Override
    public void onSpeechRmsChanged(float value) {
    }

    @Override
    public void onSpeechPartialResults(List<String> results) {
        Log.i("INFO", "partial result accepted for accident id: " + accidentId);
        Log.i("INFO", "results are: ");
        for (String s : results) {
            Log.i("", s);
        }

    }

    @Override
    public void onSpeechResult(String result) {
        recognizedWords.add(result);
    }
}
