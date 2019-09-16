package pl.kasztany.orlenfirstaid.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import pl.kasztany.orlenfirstaid.R;
import pl.kasztany.orlenfirstaid.service.FirebaseService;
import pl.kasztany.orlenfirstaid.ui.adapter.AfterCallListAdapter;

public class AfterCallActivity extends AppCompatActivity {
    private FirebaseService firebaseService = new FirebaseService();
    private ListView listView;
    private AfterCallListAdapter afterCallListAdapter;
    private String accidentId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.after_call_activity);
        setTheme(R.style.AppTheme);
        Bundle bundle = getIntent().getExtras();
        String s = bundle.getString("ACCIDENT_ID");
        if (s == null) {
            Log.wtf("WTF", "WTF");
        }
        this.accidentId = s;
        this.listView = findViewById(R.id.aslv);
        afterCallListAdapter = new AfterCallListAdapter(this);
        firebaseService.loadContent(accidentId, afterCallListAdapter, this, listView);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
