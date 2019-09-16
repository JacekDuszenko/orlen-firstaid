package pl.kasztany.orlenfirstaid.ui;

import android.app.Activity;
import android.os.Handler;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;


public class AlarmChecklistAnimator {
    private static final Integer ANIMATION_DELAY_MS = 500;
    private AlarmChecklistItemsConfigurer alarmChecklistItemsConfigurer = new AlarmChecklistItemsConfigurer();

    public void animateAlarmChecklist(final Activity activity) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                final ChecklistAnimationObject animationObject = alarmChecklistItemsConfigurer.createChecklistAnimationObject(activity);
                TransitionManager.beginDelayedTransition(animationObject.tc1, new Slide(Gravity.LEFT).setStartDelay(500));
                TransitionManager.beginDelayedTransition(animationObject.tc2, new Slide(Gravity.RIGHT).setStartDelay(1000));
                TransitionManager.beginDelayedTransition(animationObject.tc3, new Slide(Gravity.LEFT).setStartDelay(1500));
                TransitionManager.beginDelayedTransition(animationObject.tc4, new Slide(Gravity.RIGHT).setStartDelay(2000));
                animationObject.setViewsVisible();
            }
        }, ANIMATION_DELAY_MS);
    }
}
