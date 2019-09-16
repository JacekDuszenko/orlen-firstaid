package pl.kasztany.orlenfirstaid.service;


import android.app.Activity;
import android.util.Log;

import net.gotev.speech.GoogleVoiceTypingDisabledException;
import net.gotev.speech.Speech;
import net.gotev.speech.SpeechRecognitionNotAvailable;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import pl.kasztany.orlenfirstaid.model.TranscriptData;
import pl.kasztany.orlenfirstaid.util.PolishLocaleProvider;


public class SpeechRecognitionService {

    private AudioToggleService audioToggleService;
    private Activity activity;
    private List<String> recognizedWords;
    private FirebaseService firebaseService;

    public SpeechRecognitionService(Activity activity) {
        this.activity = activity;
        recognizedWords = new ArrayList<>();
        this.audioToggleService = new AudioToggleService(activity);
        this.firebaseService = new FirebaseService();
    }

    public void startSpeechRecognition(Timer timer, String accidentId) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(() -> {
                    if (!Speech.getInstance().isListening()) {
                        initSingleSpeechRecognition(accidentId);
                    }
                });
            }
        }, 0, 1200);
    }

    public void finalizeSpeechRecognition(String accidentId) {
        Speech.getInstance().stopListening();
        audioToggleService.enableAudio();
        String transcript = StringUtils.join(recognizedWords, ". ");
        firebaseService.insertTranscriptToNode("speech_recognition_result", new TranscriptData(transcript, accidentId));
        Log.i("INFO", "now recognition should not work, data is: " + transcript);
        this.recognizedWords.clear();
    }

    private void initSingleSpeechRecognition(String accidentId) {
        try {
            audioToggleService.disableAudio();
            Speech.getInstance().setLocale(PolishLocaleProvider.getLocale());
            Speech.getInstance().startListening(new SpeechRecognitionDelegate(recognizedWords, accidentId));
        } catch (SpeechRecognitionNotAvailable speechRecognitionNotAvailable) {
            speechRecognitionNotAvailable.printStackTrace();
        } catch (GoogleVoiceTypingDisabledException e) {
            e.printStackTrace();
        }
    }
}
