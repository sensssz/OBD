package cn.edu.nju.software.obd.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.astuetz.PagerSlidingTabStrip;

import cn.edu.nju.software.obd.R;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class SwipeChartAdapter extends FragmentPagerAdapter {
        private String[] titles;

        public SwipeChartAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

            titles = new String[]{
                    getString(R.string.average_fuel_consumption),
                    getString(R.string.average_speed),
                    getString(R.string.highest_water_temperature_of_engine)};
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
