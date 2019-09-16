package pl.kasztany.orlenfirstaid.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pl.kasztany.orlenfirstaid.R;
import pl.kasztany.orlenfirstaid.service.FirebaseService;
import pl.kasztany.orlenfirstaid.service.PermissionHandler;
import pl.kasztany.orlenfirstaid.ui.adapter.ViewPagerAdapter;
import pl.kasztany.orlenfirstaid.ui.fragment.factory.ViewPagerFactory;

public class MainActivity extends AppCompatActivity {
    private final PermissionHandler permissionHandler = new PermissionHandler(this);
    private final ViewPagerFactory viewPagerFactory = new ViewPagerFactory(this);
    private FirebaseService firebaseService = new FirebaseService();
    public ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPagerFactory.initViewPager();
        permissionHandler.handlePermissions();
        addNotificationTrigger();
        addPostCallTrigger();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void addNotificationTrigger() {
        firebaseService.setOnAccidentListener(this);
    }

    private void addPostCallTrigger() {
        firebaseService.postCallTrigger(this);
    }
}
