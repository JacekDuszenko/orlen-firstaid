package pl.kasztany.orlenfirstaid.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.GridHolder;
import com.orhanobut.dialogplus.OnItemClickListener;

import pl.kasztany.orlenfirstaid.R;
import pl.kasztany.orlenfirstaid.activity.MainActivity;
import pl.kasztany.orlenfirstaid.model.AlarmCallData;
import pl.kasztany.orlenfirstaid.service.FirebaseService;
import pl.kasztany.orlenfirstaid.service.LocationService;
import pl.kasztany.orlenfirstaid.ui.adapter.AlarmCallAdapter;
import pl.kasztany.orlenfirstaid.ui.fragment.factory.AlarmCallAdapterFactory;

import static pl.kasztany.orlenfirstaid.util.Constants.LOCATION_CHECKING_INTERVAL_MS;

public class MenuFragment extends Fragment implements View.OnClickListener {
    private ViewPager viewPager;
    private LocationService locationService;
    private MainActivity mainActivity;
    private AlarmCallAdapter alarmCallAdapter;

    public MenuFragment(ViewPager viewPager, MainActivity mainActivity) {
        this.viewPager = viewPager;
        this.mainActivity = mainActivity;
        this.alarmCallAdapter = AlarmCallAdapterFactory.creatDefaultAlarmCallAdapter(mainActivity);
    }

    public static MenuFragment newInstance(int page, ViewPager viewPager, MainActivity mainActivity) {
        MenuFragment menuFragment = new MenuFragment(viewPager, mainActivity);
        Bundle args = new Bundle();
        args.putInt("page", page);
        menuFragment.setArguments(args);
        return menuFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        TextView locationTv = view.findViewById(R.id.locationTv);
        TextView latlongtv = view.findViewById(R.id.latLongTv);
        TextView phoneCallTv = view.findViewById(R.id.tv_phone_hang_start_call);
        phoneCallTv.setOnClickListener(this);
        locationService = new LocationService(mainActivity, locationTv, latlongtv);
        locationService.registerLocationCallback(LOCATION_CHECKING_INTERVAL_MS);
        new FirebaseService().postCallTrigger(getContext());
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_phone_hang_start_call) {
            showCallSelectionDialog();
        }
    }

    private void showCallSelectionDialog() {
        DialogPlus dialog = DialogPlus.newDialog(mainActivity)
                .setContentHolder(new GridHolder(4))
                .setAdapter(alarmCallAdapter)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        dialog.dismiss();
                        callANumber((AlarmCallData) item);
                    }
                })
                .setExpanded(false)
                .setPadding(20, 100, 10, 100)
                .setGravity(Gravity.CENTER)
                .create();
        dialog.show();
    }

    private void callANumber(AlarmCallData alarmCallData) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + alarmCallData.getNumber()));
        startActivity(intent);
    }
}
