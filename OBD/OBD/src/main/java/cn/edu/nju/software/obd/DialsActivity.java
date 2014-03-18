package cn.edu.nju.software.obd;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Shows the current status of the car
 */
public class DialsActivity extends Activity {
    private ImageView mSpeedDialPointer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dials);

        mSpeedDialPointer = (ImageView) findViewById(R.id.speed_dial_pointer);
        mSpeedDialPointer.post(new Runnable() {
            @Override
            public void run() {
                Animation rotateAnimation = AnimationUtils.
                        loadAnimation(DialsActivity.this, R.anim.pointer_rotate);
                if (rotateAnimation != null) {
                    mSpeedDialPointer.startAnimation(rotateAnimation);
                }
            }
        });
    }
}
