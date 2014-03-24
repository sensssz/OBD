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
import cn.edu.nju.software.obd.data.DataConfig;
import cn.edu.nju.software.obd.data.DataMap;
import cn.edu.nju.software.obd.data.DataType;

/**
 * Shows the current status of the car
 */
public class DialsActivity extends Activity {
    private static final int ANIMATION_TIME = 600;

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

    private DataMap.OnDataListener onDataListener;

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

        init();
    }

    private void init() {
        updateVoltage();
        updateRotateSpeed();
        updateTemperature();
        updatePressure();
        updateSpeed();
        updateTotalDistance();
        updateAverageFuelConsumption();
        updateTotalFuelConsumption();
    }

    private void updateVoltage() {
        final String voltage = DataMap.getInstance().getData(DataType.VOLTAGE);
        if (voltage != null && voltage.length() > 0) {
            mVoltageDialPointer.post(new Runnable() {
                @Override
                public void run() {
                    updateVoltage(voltage);
                }
            });
        }
    }

    private void updateRotateSpeed() {
        final String rotateSpeed = DataMap.getInstance().getData(DataType.ROTATE_SPEED);
        if (rotateSpeed != null && rotateSpeed.length() > 0) {
            mRotateSpeedDialPointer.post(new Runnable() {
                @Override
                public void run() {
                    updateRotateSpeed(rotateSpeed);
                }
            });
        }
    }

    private void updateTemperature() {
        final String temperature = DataMap.getInstance().getData(DataType.TEMPERATURE);
        if (temperature != null && temperature.length() > 0) {
            mTemperatureDialPointer.post(new Runnable() {
                @Override
                public void run() {
                    updateTemperature(temperature);
                }
            });
        }
    }

    private void updatePressure() {
        final String pressure = DataMap.getInstance().getData(DataType.PRESSURE);
        if (pressure != null && pressure.length() > 0) {
            mPressureDialPointer.post(new Runnable() {
                @Override
                public void run() {
                    updatePressure(pressure);
                }
            });
        }
    }

    private void updateSpeed() {
        final String speed = DataMap.getInstance().getData(DataType.SPEED);
        if (speed != null && speed.length() > 0) {
            mSpeedDialPointer.post(new Runnable() {
                @Override
                public void run() {
                    updateSpeed(speed);
                }
            });
        }
    }

    private void updateTotalDistance() {
        final String totalDistance = DataMap.getInstance().getData(DataType.TOTAL_DISTANCE);
        if (totalDistance != null && totalDistance.length() > 0) {
            mTotalDistanceText.post(new Runnable() {
                @Override
                public void run() {
                    mTotalDistanceText.setText(totalDistance);
                }
            });
        }
    }

    private void updateAverageFuelConsumption() {
        final String averageFuelConsumption = DataMap.getInstance().getData(DataType.AVERAGE_FUEL_CONSUMPTION);
        if (averageFuelConsumption != null && averageFuelConsumption.length() > 0) {
            mAverageFuelConsumptionText.post(new Runnable() {
                @Override
                public void run() {
                    mAverageFuelConsumptionText.setText(averageFuelConsumption);
                }
            });
        }
    }

    private void updateTotalFuelConsumption() {
        final String totalFuelConsumption = DataMap.getInstance().getData(DataType.TOTAL_FUEL_CONSUMPTION);
        if (totalFuelConsumption != null && totalFuelConsumption.length() > 0) {
            mTotalFuelConsumptionText.post(new Runnable() {
                @Override
                public void run() {
                    mTotalFuelConsumptionText.setText(totalFuelConsumption);
                }
            });
        }
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
        rotateAnimation.setDuration(ANIMATION_TIME);
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

    @Override
    public void onResume() {
        super.onResume();
        onDataListener = new DataMap.OnDataListener() {
            @Override
            public void onDataReceived(String dataTypeName, String dataValue) {
                DataType dataType = DataConfig.getTypeByName(dataTypeName);
                if (dataType != null) {
                    switch (dataType) {
                        case VOLTAGE:
                            updateVoltage(dataValue);
                            return;

                        case ROTATE_SPEED:
                            updateRotateSpeed(dataValue);
                            return;

                        case TEMPERATURE:
                            updateTemperature(dataValue);
                            return;

                        case PRESSURE:
                            updatePressure(dataValue);
                            return;

                        case SPEED:
                            updateSpeed(dataValue);
                            return;

                        case TOTAL_DISTANCE:
                            mTotalDistanceText.setText(dataValue);
                            return;

                        case AVERAGE_FUEL_CONSUMPTION:
                            mAverageFuelConsumptionText.setText(dataValue);
                            return;

                        case TOTAL_FUEL_CONSUMPTION:
                            mTotalFuelConsumptionText.setText(dataValue);
                            return;

                        default:
                            break;
                    }
                }
            }
        };
        DataMap.getInstance().addOnDataListener(onDataListener);
    }

    private void updateVoltage(String voltageString) {
        float voltage = DataMap.dataValueToInt(voltageString);
        rotateVoltagePointer(voltage);
        mVoltageText.setText(voltageString);
    }

    private void updateRotateSpeed(String rotateSpeedString) {
        float rotateSpeed = DataMap.dataValueToInt(rotateSpeedString);
        rotateRotateSpeedPointer(rotateSpeed);
        mRotateSpeedText.setText(rotateSpeedString);
    }

    private void updateTemperature(String temperatureString) {
        float temperature = DataMap.dataValueToInt(temperatureString);
        rotateTemperaturePointer(temperature);
        mTemperatureText.setText(temperatureString);
    }

    private void updatePressure(String pressureString) {
        float pressure = DataMap.dataValueToInt(pressureString);
        rotatePressurePointer(pressure);
        mPressureText.setText(pressureString);
    }

    private void updateSpeed(String speedString) {
        float speed = DataMap.dataValueToInt(speedString);
        rotateSpeedPointer(speed);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (onDataListener != null) {
            DataMap.getInstance().removeOnDataListener(onDataListener);
            onDataListener = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (onDataListener != null) {
            DataMap.getInstance().removeOnDataListener(onDataListener);
            onDataListener = null;
        }
    }
}
