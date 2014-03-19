package cn.edu.nju.software.obd;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;

public class StatisticsChartActivity extends FragmentActivity {

    private static final int NUM_OF_PAGES = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_chart);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new SwipeChartAdapter(getSupportFragmentManager()));

        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
    }

    private class SwipeChartAdapter extends FragmentPagerAdapter {
        private String[] titles;

        public SwipeChartAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

            titles = new String[]{
                    getString(R.string.average_fuel_consumption),
                    getString(R.string.odo),
                    getString(R.string.total_fuel_consumption)};
        }

        @Override
        public Fragment getItem(int position) {
            return new ChartFragment();
        }

        @Override
        public int getCount() {
            return NUM_OF_PAGES;
        }

        @Override
        public String getPageTitle(int position) {
            return titles[position];
        }
    }


}
