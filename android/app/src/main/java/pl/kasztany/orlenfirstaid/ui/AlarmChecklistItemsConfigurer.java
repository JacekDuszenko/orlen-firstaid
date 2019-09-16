package pl.kasztany.orlenfirstaid.ui;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import pl.kasztany.orlenfirstaid.R;


class AlarmChecklistItemsConfigurer {
    ChecklistAnimationObject createChecklistAnimationObject(Activity activity) {
        final ViewGroup tc1 = activity.findViewById(R.id.checklist_items_transformation_container_1);
        final ViewGroup tc2 = activity.findViewById(R.id.checklist_items_transformation_container_2);
        final ViewGroup tc3 = activity.findViewById(R.id.checklist_items_transformation_container_3);
        final ViewGroup tc4 = activity.findViewById(R.id.checklist_items_transformation_container_4);
        final View v1 = activity.findViewById(R.id.checklist_item_1);
        final View v2 = activity.findViewById(R.id.checklist_item_2);
        final View v3 = activity.findViewById(R.id.checklist_item_3);
        final View v4 = activity.findViewById(R.id.checklist_item_4);
        return new ChecklistAnimationObject.ChecklistAnimationObjectBuilder()
                .tc1(tc1).tc2(tc2).tc3(tc3)
                .tc4(tc4).v1(v1)
                .v2(v2).v3(v3).v4(v4)
                .build();
    }
}
