package pl.kasztany.orlenfirstaid.ui;

import android.view.View;
import android.view.ViewGroup;

import lombok.Builder;

@Builder
 class ChecklistAnimationObject {
    ViewGroup tc1;
    ViewGroup tc2;
    ViewGroup tc3;
    ViewGroup tc4;
    View v1;
    View v2;
    View v3;
    View v4;

    void setViewsVisible() {
        v1.setVisibility(View.VISIBLE);
        v2.setVisibility(View.VISIBLE);
        v3.setVisibility(View.VISIBLE);
        v4.setVisibility(View.VISIBLE);
    }
}
