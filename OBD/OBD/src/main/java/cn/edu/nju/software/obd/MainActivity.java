package cn.edu.nju.software.obd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 家敏 on 14-3-18.
 */
public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Button btn_firstView = (Button)findViewById(R.id.btn_firstView);
        Button btn_secondView = (Button)findViewById(R.id.btn_secondView);

        btn_firstView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, DialsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        btn_secondView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



}
