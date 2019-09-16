package pl.kasztany.orlenfirstaid.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import net.gotev.speech.Speech;

import java.util.Timer;
import java.util.TimerTask;

import pl.kasztany.orlenfirstaid.R;
import pl.kasztany.orlenfirstaid.activity.util.ActivityStack;
import pl.kasztany.orlenfirstaid.service.AudioToggleService;
import pl.kasztany.orlenfirstaid.service.FirebaseService;
import pl.kasztany.orlenfirstaid.service.LocationService;
import pl.kasztany.orlenfirstaid.service.PhoneCallManager;
import pl.kasztany.orlenfirstaid.service.PhoneCallService;
import pl.kasztany.orlenfirstaid.service.SpeechRecognitionService;
import pl.kasztany.orlenfirstaid.ui.AlarmChecklistAnimator;
import pl.kasztany.orlenfirstaid.ui.RippleAnimator;
import pl.kasztany.orlenfirstaid.util.CallUtils;
import pl.kasztany.orlenfirstaid.util.PolishLocaleProvider;

import static pl.kasztany.orlenfirstaid.util.Constants.LOCATION_CHECKING_INTERVAL_MS;


@RequiresApi(api = Build.VERSION_CODES.M)
public class AlarmPhoneCallActivity extends AppCompatActivity implements View.OnClickListener {
    private PhoneCallManager phoneCallManager;
    private PhoneCallService.CallType callType;
    private LocationService locationService;
    private AudioToggleService audioToggleService;
    private SpeechRecognitionService speechRecognitionService;

    private String phoneNumber;
    public String accidentId;
    private Timer onGoingCallTimer;
    private int callingTime;

    public static void actionStart(Context context, String phoneNumber,
                                   PhoneCallService.CallType callType) {
        Intent intent = new Intent(context, AlarmPhoneCallActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Intent.EXTRA_MIME_TYPES, callType);
        intent.putExtra(Intent.EXTRA_PHONE_NUMBER, phoneNumber);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_phone_call);
        ActivityStack.getInstance().addActivity(this);
        initData();
        initView();
        initAction();
    }

    private void initData() {
        Speech.init(this);
        Speech.getInstance().setLocale(PolishLocaleProvider.getLocale());
        phoneCallManager = new PhoneCallManager(this);
        onGoingCallTimer = new Timer();
        locationService = new LocationService(this, findViewById(R.id.latLongAlarmTv), findViewById(R.id.lcAlarmTv));
        audioToggleService = new AudioToggleService(this);
        speechRecognitionService = new SpeechRecognitionService(this);
    }

    private void initView() {
        locationService.registerLocationCallback(LOCATION_CHECKING_INTERVAL_MS);
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        getWindow().getDecorView().setSystemUiVisibility(uiOptions);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        new AlarmChecklistAnimator().animateAlarmChecklist(this);
        new RippleAnimator().startRippleAnimation(this);
        if (callType == PhoneCallService.CallType.CALL_IN) {
        } else if (callType == PhoneCallService.CallType.CALL_OUT) {
            phoneCallManager.openSpeaker();
        }
    }

    private void initAction() {
        initPhoneRelatedUI();
        setAccidentId();
        audioToggleService.disableAudio();
        speechRecognitionService.startSpeechRecognition(onGoingCallTimer, accidentId);
    }

    private void setAccidentId() {
        this.accidentId = CallUtils.buildAccidentId();
    }

    private void initPhoneRelatedUI() {
        if (getIntent() != null) {
            phoneNumber = getIntent().getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            callType = (PhoneCallService.CallType) getIntent().getSerializableExtra(Intent.EXTRA_MIME_TYPES);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_phone_pick_up) {
            phoneCallManager.answer();
            onGoingCallTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void run() {
                            callingTime++;
                        }
                    });
                }
            }, 0, 1000);
        }
    }

    @Override
    protected void onDestroy() {
        locationService.unregisterLocationCallback();
        speechRecognitionService.finalizeSpeechRecognition(accidentId);
        audioToggleService.enableAudio();
        phoneCallManager.destroy();
        super.onDestroy();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}