package cn.edu.nju.software.obd.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        Button firstViewButton = (Button) findViewById(R.id.firstViewButton);
        Button secondViewButton = (Button) findViewById(R.id.secondViewButton);
        Button thirdViewButton = (Button) findViewById(R.id.thirdViewButton);
        Button forthViewButton = (Button) findViewById(R.id.forthViewButton);
        Button fifthViewButton = (Button) findViewById(R.id.fifthViewButton);

        firstViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        secondViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DialsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        thirdViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, StatisticsChartActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        forthViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CarInfoActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        fifthViewButton.setOnClickListener(new View.OnClickListener() {
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
