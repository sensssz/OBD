package cn.edu.nju.software.obd.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.nju.software.obd.R;

/**
 * Shows the current status of the car
 */
public class DialsActivity extends Activity {
    private static final float VOLTAGE_MIN_VALUE = 9;
    private static final float VOLTAGE_MAX_VALUE = 16;
    private static final float VOLTAGE_MAX_ANGLE = 129.0f;
    private static final float DEGREE_PER_VOLTAGE =
            VOLTAGE_MAX_ANGLE / (VOLTAGE_MAX_VALUE - VOLTAGE_MIN_VALUE);
    private static final float ROTATE_SPEED_MIN_VALUE = 0;
    private static final float ROTATE_SPEED_MAX_VALUE = 10000;
    private static final float ROTATE_SPEED_MAX_ANGLE = 252f;
    private static final float DEGREE_PER_ROTATE_SPEED =
            ROTATE_SPEED_MAX_ANGLE / (ROTATE_SPEED_MAX_VALUE - ROTATE_SPEED_MIN_VALUE);
    private static final float TEMPERATURE_MIN_VALUE = 0;
    private static final float TEMPERATURE_MAX_VALUE = 180;
    private static final float TEMPERATURE_MAX_ANGLE = 126f;
    private static final float DEGREE_PER_TEMPERATURE =
            TEMPERATURE_MAX_ANGLE / (TEMPERATURE_MAX_VALUE - TEMPERATURE_MIN_VALUE);
    private static final float PRESSURE_MIN_VALUE = 0f;
    private static final float PRESSURE_MAX_VALUE = 300f;
    private static final float PRESSURE_MAX_ANGLE = 305f;
    private static final float DEGREE_PER_PRESSURE =
            PRESSURE_MAX_ANGLE / (PRESSURE_MAX_VALUE - PRESSURE_MIN_VALUE);
    private static final float SPEED_MIN_VALUE = 0;
    private static final float SPEED_MAX_VALUE = 120;
    private static final float SPEED_MAX_ANGLE = 246.33f;
    private static final float DEGREE_PER_SPEED =
            SPEED_MAX_ANGLE / (SPEED_MAX_VALUE - SPEED_MIN_VALUE);

    private ImageView mVoltageDialPointer;
    private ImageView mRotateSpeedDialPointer;
    private ImageView mTemperatureDialPointer;
    private ImageView mPressureDialPointer;
    private ImageView mSpeedDialPointer;
    private TextView mVoltageText;
    private TextView mRotateSpeedText;
    private TextView mTemperatureText;
    private TextView mPressureText;
    private TextView mTotalDistanceText;
    private TextView mAverageFuelConsumptionText;
    private TextView mTotalFuelConsumptionText;
    private float mVoltageAngle;
    private float mRotateSpeedAngle;
    private float mTemperatureAngle;
    private float mPressureAngle;
    private float mSpeedAngle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dials);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mVoltageDialPointer = (ImageView) findViewById(R.id.voltage_dial_pointer);
        mRotateSpeedDialPointer = (ImageView) findViewById(R.id.rotate_speed_dial_pointer);
        mTemperatureDialPointer = (ImageView) findViewById(R.id.temperature_dial_pointer);
        mPressureDialPointer = (ImageView) findViewById(R.id.pressure_dial_pointer);
        mSpeedDialPointer = (ImageView) findViewById(R.id.speed_dial_pointer);
        mVoltageText = (TextView) findViewById(R.id.voltage);
        mRotateSpeedText = (TextView) findViewById(R.id.rotate_speed);
        mTemperatureText = (TextView) findViewById(R.id.temperature);
        mPressureText = (TextView) findViewById(R.id.pressure);
        mTotalDistanceText = (TextView) findViewById(R.id.total_distance);
        mAverageFuelConsumptionText = (TextView) findViewById(R.id.average_fuel_consumption);
        mTotalFuelConsumptionText = (TextView) findViewById(R.id.total_fuel_consumption);

        mVoltageDialPointer.post(new Runnable() {
            @Override
            public void run() {
				rotateVoltagePointer(VOLTAGE_MAX_VALUE);
			}
        });
        mRotateSpeedDialPointer.post(new Runnable() {
            @Override
            public void run() {
				rotateRotateSpeedPointer(ROTATE_SPEED_MAX_VALUE);
			}
        });
        mTemperatureDialPointer.post(new Runnable() {
            @Override
            public void run() {
				rotateTemperaturePointer(TEMPERATURE_MAX_VALUE);
			}
        });
        mPressureDialPointer.post(new Runnable() {
            @Override
            public void run() {
				rotatePressurePointer(PRESSURE_MAX_VALUE);
			}
        });
        mSpeedDialPointer.post(new Runnable() {
            @Override
            public void run() {
				rotateSpeedPointer(SPEED_MAX_VALUE);
			}
        });
    }

    private void rotateVoltagePointer(float voltageValue) {
        mVoltageAngle = rotatePointer(mVoltageDialPointer, 1.0f, voltageValue, mVoltageAngle,
                VOLTAGE_MIN_VALUE, VOLTAGE_MAX_VALUE, DEGREE_PER_VOLTAGE);
    }

    private void rotateRotateSpeedPointer(float rotateSpeedValue) {
        mRotateSpeedAngle = rotatePointer(mRotateSpeedDialPointer, 0.5f, rotateSpeedValue, mRotateSpeedAngle,
                ROTATE_SPEED_MIN_VALUE, ROTATE_SPEED_MAX_VALUE, DEGREE_PER_ROTATE_SPEED);
    }

    private void rotateTemperaturePointer(float temperatureValue) {
        mTemperatureAngle = rotatePointer(mTemperatureDialPointer, 1.0f, temperatureValue, mTemperatureAngle,
                TEMPERATURE_MIN_VALUE, TEMPERATURE_MAX_VALUE, DEGREE_PER_TEMPERATURE);
    }

    private void rotatePressurePointer(float pressureValue) {
        mPressureAngle = rotatePointer(mPressureDialPointer, 0.5f, pressureValue, mPressureAngle,
                PRESSURE_MIN_VALUE, PRESSURE_MAX_VALUE, DEGREE_PER_PRESSURE);
    }

    private void rotateSpeedPointer(float speedValue) {
        mSpeedAngle = rotatePointer(mSpeedDialPointer, 0.5f, speedValue, mSpeedAngle,
                SPEED_MIN_VALUE, SPEED_MAX_VALUE, DEGREE_PER_SPEED);
    }

    private float rotatePointer(ImageView pointer, float pivotY, float currentValue,
                                float fromDegree, float minValue, float maxValue,
                                float degreePerValue) {
        if (currentValue > maxValue) {
            currentValue = maxValue;
        } else if (currentValue < minValue) {
            currentValue = minValue;
        }
		currentValue -= minValue;
		float toDegree = currentValue * degreePerValue;
        if (fromDegree != toDegree) {
            pointer.startAnimation(getRotateAnimation(fromDegree, toDegree, pivotY));
        }
        return toDegree;
    }

    private RotateAnimation getRotateAnimation(float fromDegree, float toDegree, float pivotY) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegree, toDegree,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, pivotY);
        rotateAnimation.setInterpolator(DialsActivity.this, android.R.anim.accelerate_decelerate_interpolator);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillEnabled(true);
        rotateAnimation.setFillAfter(true);
        return rotateAnimation;
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
