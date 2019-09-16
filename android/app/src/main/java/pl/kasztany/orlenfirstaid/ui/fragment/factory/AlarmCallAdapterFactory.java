package pl.kasztany.orlenfirstaid.ui.fragment.factory;

import android.content.Context;
import android.graphics.drawable.Drawable;

import pl.kasztany.orlenfirstaid.R;
import pl.kasztany.orlenfirstaid.model.AlarmCallData;
import pl.kasztany.orlenfirstaid.ui.adapter.AlarmCallAdapter;

public class AlarmCallAdapterFactory {

    public static AlarmCallAdapter creatDefaultAlarmCallAdapter(Context context) {
        AlarmCallAdapter alarmCallAdapter = new AlarmCallAdapter(context, 4);
        Drawable ambu = context.getResources().getDrawable(R.drawable.ambulance);
        Drawable police = context.getResources().getDrawable(R.drawable.police);
        Drawable ft = context.getResources().getDrawable(R.drawable.firetruck);
        Drawable oneonetwo = context.getResources().getDrawable(R.drawable.oneonetwo);
        alarmCallAdapter.addToAlarmCallDataList(new AlarmCallData(oneonetwo, "794655800", "112"));
        alarmCallAdapter.addToAlarmCallDataList(new AlarmCallData(ambu, "123123123", "999"));
        alarmCallAdapter.addToAlarmCallDataList(new AlarmCallData(ft, "321321321", "998"));
        alarmCallAdapter.addToAlarmCallDataList(new AlarmCallData(police, "555333222", "997"));

        return alarmCallAdapter;
    }
}
