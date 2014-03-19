package cn.edu.nju.software.obd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Displays two buttons
 */
public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button firstViewButton = (Button)findViewById(R.id.firstViewButton);
        Button secondViewButton = (Button)findViewById(R.id.secondViewButton);

        firstViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DialsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        secondViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, StatisticsChartActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }



}
