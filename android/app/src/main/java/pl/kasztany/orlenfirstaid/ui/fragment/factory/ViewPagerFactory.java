package pl.kasztany.orlenfirstaid.ui.fragment.factory;


import androidx.viewpager.widget.ViewPager;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;

import me.relex.circleindicator.CircleIndicator;
import pl.kasztany.orlenfirstaid.R;
import pl.kasztany.orlenfirstaid.activity.MainActivity;
import pl.kasztany.orlenfirstaid.ui.adapter.ViewPagerAdapter;

public class ViewPagerFactory {
    private final MainActivity mainActivity;

    public ViewPagerFactory(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void initViewPager() {
        ViewPager viewPager = mainActivity.findViewById(R.id.viewPager);
        CircleIndicator indicator = mainActivity.findViewById(R.id.circleIndicator);
        mainActivity.viewPagerAdapter = new ViewPagerAdapter(mainActivity.getSupportFragmentManager(), viewPager, mainActivity);
        viewPager.setAdapter(mainActivity.viewPagerAdapter);
        viewPager.setPageTransformer(true, new CubeOutTransformer());
        indicator.setViewPager(viewPager);
    }
}