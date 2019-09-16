package pl.kasztany.orlenfirstaid.service;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.telecom.Call;
import android.telecom.VideoProfile;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.M)
public class PhoneCallManager {

    static Call call;

    private Context context;
    private AudioManager audioManager;

    public PhoneCallManager(Context context) {
        this.context = context;
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    }

    public void answer() {
        if (call != null) {
            call.answer(VideoProfile.STATE_AUDIO_ONLY);
            openSpeaker();
        }
    }

    public void disconnect() {
        if (call != null) {
            call.disconnect();
        }
    }

    public void openSpeaker() {
        if (audioManager != null) {
            audioManager.setMode(AudioManager.MODE_IN_CALL);
            audioManager.setSpeakerphoneOn(true);
        }
    }

    public void destroy() {
        call = null;
        context = null;
        audioManager = null;
    }
}
