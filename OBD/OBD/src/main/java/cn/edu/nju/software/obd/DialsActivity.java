package cn.edu.nju.software.obd;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Shows the current status of the car
 */
public class DialsActivity extends Activity {
    private static final double VOLTAGE_MAX_ANGLE = 129.0;
    private static final double SPEED_MAX_ANGLE = 246.33;

    private ImageView mVoltageDialPointer;
    private ImageView mSpeedDialPointer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dials);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mVoltageDialPointer = (ImageView) findViewById(R.id.voltage_dial_pointer);
        mSpeedDialPointer = (ImageView) findViewById(R.id.speed_dial_pointer);

        mVoltageDialPointer.post(new Runnable() {
            @Override
            public void run() {
                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 100.0f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
                rotateAnimation.setInterpolator(DialsActivity.this, android.R.anim.accelerate_decelerate_interpolator);
                rotateAnimation.setDuration(1000);
                rotateAnimation.setFillEnabled(true);
                rotateAnimation.setFillAfter(true);
                mVoltageDialPointer.startAnimation(rotateAnimation);
            }
        });
        mSpeedDialPointer.post(new Runnable() {
            @Override
            public void run() {
                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 100.0f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setInterpolator(DialsActivity.this, android.R.anim.accelerate_decelerate_interpolator);
                rotateAnimation.setDuration(1000);
                rotateAnimation.setFillEnabled(true);
                rotateAnimation.setFillAfter(true);
                mSpeedDialPointer.startAnimation(rotateAnimation);
            }
        });
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
