package pl.kasztany.orlenfirstaid.service;

import android.os.Build;
import android.telecom.Call;
import android.telecom.InCallService;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;

import pl.kasztany.orlenfirstaid.activity.AlarmPhoneCallActivity;
import pl.kasztany.orlenfirstaid.activity.PhoneCallActivity;
import pl.kasztany.orlenfirstaid.activity.util.ActivityStack;

@RequiresApi(api = Build.VERSION_CODES.M)
public class PhoneCallService extends InCallService {

    private List alarmNumbers = Arrays.asList("112",
            "997",
            "998",
            "999",
            "123456",
            "794655800");

    private Call.Callback callback = new Call.Callback() {
        @Override
        public void onStateChanged(Call call, int state) {
            super.onStateChanged(call, state);

            switch (state) {
                case Call.STATE_ACTIVE: {
                    break;
                }
                case Call.STATE_DISCONNECTED: {
                    ActivityStack.getInstance().finishActivity(PhoneCallActivity.class);
                    ActivityStack.getInstance().finishActivity(AlarmPhoneCallActivity.class);
                    break;
                }
            }
        }
    };

    @Override
    public void onCallAdded(Call call) {
        super.onCallAdded(call);
        call.registerCallback(callback);
        PhoneCallManager.call = call;

        CallType callType = null;

        if (call.getState() == Call.STATE_RINGING) {
            callType = CallType.CALL_IN;
        } else if (call.getState() == Call.STATE_CONNECTING) {
            callType = CallType.CALL_OUT;
        }

        if (callType != null) {
            Call.Details details = call.getDetails();
            String phoneNumber = details.getHandle().getSchemeSpecificPart();
            if (alarmNumbers.contains(phoneNumber)) {
                AlarmPhoneCallActivity.actionStart(this, phoneNumber, callType);
            } else {
                PhoneCallActivity.actionStart(this, phoneNumber, callType);
            }
        }
    }

    @Override
    public void onCallRemoved(Call call) {
        super.onCallRemoved(call);

        call.unregisterCallback(callback);
        PhoneCallManager.call = null;
    }

    public enum CallType {
        CALL_IN,
        CALL_OUT,
    }
}
