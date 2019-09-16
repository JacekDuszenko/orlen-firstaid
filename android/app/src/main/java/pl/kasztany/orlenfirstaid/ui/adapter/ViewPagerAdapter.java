package pl.kasztany.orlenfirstaid.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import pl.kasztany.orlenfirstaid.activity.MainActivity;
import pl.kasztany.orlenfirstaid.ui.fragment.EncyclopediaFragment;
import pl.kasztany.orlenfirstaid.ui.fragment.MapFragment;
import pl.kasztany.orlenfirstaid.ui.fragment.MenuFragment;
import pl.kasztany.orlenfirstaid.ui.fragment.QuizFragment;
import pl.kasztany.orlenfirstaid.ui.fragment.SettingsFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ViewPager viewPager;
    private MainActivity mainActivity;

    public ViewPagerAdapter(FragmentManager fm, ViewPager viewPager, MainActivity mainActivity) {
        super(fm);
        this.viewPager = viewPager;
        this.mainActivity = mainActivity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MenuFragment.newInstance(0, viewPager, mainActivity);
            case 1:
                return MapFragment.newInstance(1);
            case 2:
                return EncyclopediaFragment.newInstance(2);
            case 3:
                return QuizFragment.newInstance(3);
            case 4:
                return SettingsFragment.newInstance(4);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
