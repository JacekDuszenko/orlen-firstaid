package pl.kasztany.orlenfirstaid.util;

import android.text.TextUtils;

import java.util.Calendar;
import java.util.UUID;

public class CallUtils {
    public static String formatPhoneNumber(String phoneNum) {
        if (!TextUtils.isEmpty(phoneNum) && phoneNum.length() == 11) {
            return phoneNum.substring(0, 3) + "-"
                    + phoneNum.substring(3, 7) + "-"
                    + phoneNum.substring(7);
        }
        return phoneNum;
    }

    public static String buildAccidentId() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DAY_OF_MONTH)
                + "_"
                + now.get(Calendar.HOUR)
                + "_"
                + now.get(Calendar.MINUTE)
                + "_"
                + UUID.randomUUID().toString();
    }
}
