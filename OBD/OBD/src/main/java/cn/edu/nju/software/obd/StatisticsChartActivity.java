package cn.edu.nju.software.obd;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import com.astuetz.PagerSlidingTabStrip;

public class StatisticsChartActivity extends FragmentActivity {

    private static int NUM_OF_PAGES = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_chart);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new SwipeChartAdapter(getSupportFragmentManager()));

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }

    private class SwipeChartAdapter extends FragmentStatePagerAdapter {
        public SwipeChartAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return new ChartFragment();
        }

        @Override
        public int getCount() {
            return NUM_OF_PAGES;
        }
    }


}
