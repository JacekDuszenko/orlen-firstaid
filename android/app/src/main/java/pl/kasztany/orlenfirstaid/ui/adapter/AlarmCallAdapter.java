package pl.kasztany.orlenfirstaid.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.kasztany.orlenfirstaid.R;
import pl.kasztany.orlenfirstaid.model.AlarmCallData;

public class AlarmCallAdapter extends BaseAdapter {
    private Integer count;
    private Context context;
    private LayoutInflater layoutInflater;
    private List<AlarmCallData> alarmCallDataList = new ArrayList<>();

    public AlarmCallAdapter(Context context, Integer count) {
        this.count = count;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int i) {
        return alarmCallDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AlarmCallData alarmCallData = alarmCallDataList.get(i);
        View v = layoutInflater.inflate(R.layout.alarm_call_list_item, viewGroup, false);
        TextView tv = v.findViewById(R.id.alarm_call_tv);
        ImageView iv = v.findViewById(R.id.alarm_call_img);
        tv.setText(alarmCallData.getDescription());
        iv.setBackground(alarmCallData.getImage());

        return v;
    }

    public void addToAlarmCallDataList(AlarmCallData alarmCallData) {
        this.alarmCallDataList.add(alarmCallData);
    }
}
