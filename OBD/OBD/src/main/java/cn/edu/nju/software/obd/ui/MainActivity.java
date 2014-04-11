package cn.edu.nju.software.obd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import cn.edu.nju.software.obd.R;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;

/**
 * Displays two buttons
 */
public class MainActivity extends InstrumentedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton dialsViewButton = (ImageButton) findViewById(R.id.dialsViewButton);
        ImageButton statisticsChartViewButton = (ImageButton) findViewById(R.id.statisticsChartViewButton);
        ImageButton carInfoViewButton = (ImageButton) findViewById(R.id.carInfoViewButton);
        ImageButton habitViewButton = (ImageButton) findViewById(R.id.habitViewButton);

        dialsViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DialsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        statisticsChartViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, StatisticsChartActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        carInfoViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CarInfoActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        habitViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HabitActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });


    }

    @Override
    public void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }
}
