package pl.kasztany.orlenfirstaid.service;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;

public class AudioToggleService {
    private Activity activity;
    private AudioManager audioManager;


    public AudioToggleService(Activity activity) {
        this.activity = activity;
        this.audioManager = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
    }

    public void disableAudio() {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }

    public void enableAudio() {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
    }
}
