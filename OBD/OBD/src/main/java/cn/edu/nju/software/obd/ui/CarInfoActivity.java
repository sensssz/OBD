package cn.edu.nju.software.obd.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import cn.edu.nju.software.obd.R;

/**
 * Display information of the car
 */
public class CarInfoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
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
}
