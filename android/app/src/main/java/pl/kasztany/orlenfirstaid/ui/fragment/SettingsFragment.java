package pl.kasztany.orlenfirstaid.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import pl.kasztany.orlenfirstaid.R;


public class SettingsFragment extends Fragment {
    private String title;
    private int page;

    public static SettingsFragment newInstance(int page) {
        SettingsFragment map = new SettingsFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        map.setArguments(args);
        return map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        return view;
    }
}
