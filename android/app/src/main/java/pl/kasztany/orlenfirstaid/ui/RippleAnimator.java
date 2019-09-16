package pl.kasztany.orlenfirstaid.ui;


import android.app.Activity;

import com.skyfishjy.library.RippleBackground;

import pl.kasztany.orlenfirstaid.R;


public class RippleAnimator {

    public void startRippleAnimation(Activity activity) {
        final RippleBackground rippleBackground = activity.findViewById(R.id.rippleBackground);
        rippleBackground.startRippleAnimation();
    }

    public void stopRippleAnimation(Activity activity) {
        final RippleBackground rippleBackground = activity.findViewById(R.id.rippleBackground);
        rippleBackground.startRippleAnimation();
    }
}
