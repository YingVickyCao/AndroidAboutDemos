package com.hades.example.android.sensor;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hades.example.android.R;

import java.util.List;

public class TestSensorActivity extends Activity implements SensorEventListener {
    private static final String TAG = TestSensorActivity.class.getSimpleName();

    // 定义Sensor管理器
    private SensorManager mSensorManager;

    TextView mAccelerometerTv;
    TextView mOrientationTv;
    // handle Sensor.TYPE_ORIENTATION depressed,START
    float[] r = new float[9];
    float[] I = null;
    float[] gravity = new float[3];
    float[] geomagnetic = new float[3];
    float[] values = new float[3];
    // handle Sensor.TYPE_ORIENTATION depressed,end
    TextView mOrientationTv2;
    TextView etGyro;
    TextView etMagnetic;
    TextView etGravity;
    TextView etLinearAcc;
    TextView etTemerature;
    TextView etLight;
    TextView etPressure;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor);

        // 获取界面上的TextView组件
        mAccelerometerTv = findViewById(R.id.accelerometer);
        mOrientationTv = findViewById(R.id.etOrientation);
        mOrientationTv = findViewById(R.id.etOrientation2);
        etGyro = findViewById(R.id.etGyro);
        etMagnetic = findViewById(R.id.etMagnetic);
        etGravity = findViewById(R.id.etGravity);
        etLinearAcc = findViewById(R.id.etLinearAcc);
        etTemerature = findViewById(R.id.etTemerature);
        etLight = findViewById(R.id.etLight);
        etPressure = findViewById(R.id.etPressure);

        // 获取传感器管理服务
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        listAllSensor();
        checkIfHaveOneSensor();
    }

    /**
     * Samsung GM-G9730 = 52 sensors
     * <p>
     * {Sensor name="Rotation Vector  Non-wakeup", vendor="qualcomm", version=1, type=11, maxRange=1.0, resolution=0.01, power=1.415, minDelay=5000}
     * {Sensor name="AK09918 Magnetometer", vendor="akm", version=20034, type=2, maxRange=4912.0, resolution=0.15, power=1.1, minDelay=10000}
     * {Sensor name="step_detector  Non-wakeup", vendor="Samsung", version=1, type=18, maxRange=1.0, resolution=1.0, power=0.001, minDelay=0}
     * {Sensor name="SensorHub type", vendor="SAMSUNG", version=1, type=65586, maxRange=1.0, resolution=0.0, power=0.001, minDelay=1}
     * {Sensor name="LSM6DSO Gyroscope-Uncalibrated", vendor="STMicro", version=15932, type=16, maxRange=17.452778, resolution=6.1084726E-4, power=0.55, minDelay=2404}
     * {Sensor name="LSM6DSO Gyroscope", vendor="STMicro", version=15932, type=4, maxRange=17.452778, resolution=6.1084726E-4, power=0.55, minDelay=2404}
     * {Sensor name="Move Detector  Wakeup", vendor="Samsung", version=1, type=65593, maxRange=2.0, resolution=1.0, power=0.001, minDelay=-1}
     * {Sensor name="step_counter  Non-wakeup", vendor="Samsung", version=1, type=19, maxRange=4.2949673E9, resolution=1.0, power=0.001, minDelay=0}
     * {Sensor name="motion_detect", vendor="qualcomm", version=1, type=30, maxRange=1.0, resolution=1.0, power=0.025, minDelay=-1}
     * {Sensor name="TMD4910 Proximity Proximity Sensor Wakeup", vendor="TMD", version=384, type=65592, maxRange=9.0, resolution=9.0, power=0.1, minDelay=0}
     * {Sensor name="Game Rotation Vector  Non-wakeup", vendor="qualcomm", version=1, type=15, maxRange=1.0, resolution=0.01, power=0.515, minDelay=5000}
     * {Sensor name="LSM6DSO Accelerometer-Uncalibrated", vendor="STMicro", version=15932, type=35, maxRange=78.4532, resolution=0.0023928226, power=0.17, minDelay=2404}
     * {Sensor name="gravity  Non-wakeup", vendor="qualcomm", version=1, type=9, maxRange=156.99008, resolution=0.1, power=0.515, minDelay=5000}
     * {Sensor name="Pocket mode  Wakeup", vendor="Samsung", version=1, type=65605, maxRange=1.0, resolution=0.1, power=0.001, minDelay=-1}
     * {Sensor name="smd  Wakeup", vendor="Samsung", version=1, type=17, maxRange=1.0, resolution=0.1, power=0.001, minDelay=-1}
     * {Sensor name="Proximity strm", vendor="TMD", version=384, type=65582, maxRange=1.0, resolution=1.0, power=0.1, minDelay=50000}
     * {Sensor name="linear_acceleration", vendor="qualcomm", version=1, type=10, maxRange=156.99008, resolution=0.1, power=0.515, minDelay=5000}
     * {Sensor name="Led Cover Event  Wakeup", vendor="Samsung", version=1, type=65606, maxRange=2.0, resolution=1.0, power=0.001, minDelay=-1}
     * {Sensor name="TCS3407 Light Physical Light Sensor Non-wakeup", vendor="AMS", version=384, type=65598, maxRange=32657.0, resolution=1.0, power=0.1, minDelay=0}
     * {Sensor name="Wake Up Motion  Wakeup", vendor="Samsung", version=1, type=65590, maxRange=2.0, resolution=1.0, power=0.001, minDelay=-1}
     * {Sensor name="Pick Up Gesture  Wakeup", vendor="Samsung", version=1, type=25, maxRange=1.0, resolution=0.1, power=0.001, minDelay=-1}
     * {Sensor name="TCS3407 Light Ambient Light Sensor Non-wakeup", vendor="AMS", version=384, type=5, maxRange=32657.0, resolution=1.0, power=0.1, minDelay=0}
     * {Sensor name="WideIR ALS", vendor="AMS", version=384, type=65578, maxRange=32657.0, resolution=1.0, power=0.1, minDelay=50000}
     * {Sensor name="Tilt Detector  Wakeup", vendor="Samsung", version=1, type=22, maxRange=1.0, resolution=1.0, power=0.001, minDelay=0}
     * {Sensor name="TCS3407 Light CCT  Non-wakeup", vendor="AMS", version=384, type=65587, maxRange=32657.0, resolution=1.0, power=0.1, minDelay=50000}
     * {Sensor name="call_gesture  Wakeup", vendor="Samsung", version=1, type=65594, maxRange=1.0, resolution=0.1, power=0.001, minDelay=-1}
     * {Sensor name="stationary_detect", vendor="qualcomm", version=1, type=29, maxRange=1.0, resolution=1.0, power=0.025, minDelay=-1}
     * {Sensor name="LPS22HH Barometer", vendor="STMicro", version=514, type=6, maxRange=1260.0, resolution=2.0E-4, power=0.275, minDelay=40000}
     * {Sensor name="AK09918 Magnetometer-Uncalibrated", vendor="akm", version=20034, type=14, maxRange=4912.0, resolution=0.15, power=1.1, minDelay=10000}
     * {Sensor name="interrupt_gyro  Non-wakeup", vendor="Samsung", version=1, type=65579, maxRange=1.0, resolution=0.1, power=0.001, minDelay=0}
     * {Sensor name="LSM6DSO Accelerometer", vendor="STMicro", version=15932, type=1, maxRange=78.4532, resolution=0.0023928226, power=0.17, minDelay=2404}
     * {Sensor name="A96T3X6 Grip sensor", vendor="ABOV", version=14, type=65560, maxRange=5.0, resolution=5.0, power=0.75, minDelay=0}
     * {Sensor name="Touch Proximity Sensor", vendor="Samsung Electronics", version=1, type=65596, maxRange=1.0, resolution=1.0, power=0.75, minDelay=0}
     * {Sensor name="Hall IC", vendor="Samsung Electronics", version=1, type=65600, maxRange=1.0, resolution=1.0, power=0.75, minDelay=0}
     * {Sensor name="SFH7832 Rear ALS", vendor="OSRAM", version=1, type=65577, maxRange=2097151.0, resolution=1.0, power=1.0, minDelay=10000}
     * {Sensor name="SFH7832 Rear PROX", vendor="OSRAM", version=1, type=65580, maxRange=3.0, resolution=1.0, power=1.0, minDelay=0}
     * {Sensor name="SFH7832 IR", vendor="OSRAM", version=1, type=65571, maxRange=2097151.0, resolution=1.0, power=1.0, minDelay=9800}
     * {Sensor name="SFH7832 RED", vendor="OSRAM", version=1, type=65572, maxRange=2097151.0, resolution=1.0, power=1.0, minDelay=9800}
     * {Sensor name="SFH7832 GREEN", vendor="OSRAM", version=1, type=65573, maxRange=2097151.0, resolution=1.0, power=1.0, minDelay=9800}
     * {Sensor name="SFH7832 BLUE", vendor="OSRAM", version=1, type=65574, maxRange=2097151.0, resolution=1.0, power=1.0, minDelay=9800}
     * {Sensor name="SFH7832 HRM RAW", vendor="OSRAM", version=1, type=65561, maxRange=2097151.0, resolution=1.0, power=1.0, minDelay=10000}
     * {Sensor name="SFH7832 REAR SVC LED RED", vendor="OSRAM", version=1, type=65627, maxRange=2097151.0, resolution=1.0, power=1.0, minDelay=10000}
     * {Sensor name="SFH7832 REAR SVC LED GREEN", vendor="OSRAM", version=1, type=65628, maxRange=2097151.0, resolution=1.0, power=1.0, minDelay=10000}
     * {Sensor name="SFH7832 REAR SVC LED BLUE", vendor="OSRAM", version=1, type=65629, maxRange=2097151.0, resolution=1.0, power=1.0, minDelay=10000}
     * {Sensor name="Screen Orientation Sensor", vendor="Samsung Electronics", version=3, type=27, maxRange=255.0, resolution=255.0, power=0.0, minDelay=0}
     * {Sensor name="Palm Proximity Sensor", vendor="Samsung", version=1, type=8, maxRange=5.0, resolution=1.0, power=0.0, minDelay=0}
     * {Sensor name="Ear call-gesture state", vendor="Samsung", version=1, type=65595, maxRange=1.0, resolution=1.0, power=0.0, minDelay=0}
     * {Sensor name="HRM Sensor", vendor="Samsung Electronics", version=1, type=65562, maxRange=200.0, resolution=1.0, power=0.0, minDelay=0}
     * {Sensor name="HeartRate Sensor", vendor="Samsung Electronics", version=1, type=21, maxRange=200.0, resolution=1.0, power=0.0, minDelay=1000000}
     * {Sensor name="Motion Sensor", vendor="Samsung Electronics", version=1, type=65559, maxRange=200.0, resolution=0.0, power=0.0, minDelay=0}
     * {Sensor name="Rear Light Sensor", vendor="Samsung", version=1, type=65601, maxRange=32657.0, resolution=1.0, power=0.1, minDelay=0}
     * {Sensor name="Orientation Sensor", vendor="Samsung Electronics", version=1, type=3, maxRange=360.0, resolution=0.00390625, power=1.415, minDelay=5000}
     */
    private void listAllSensor() {
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor item : sensors) {
            if (null == item) {
                Log.d(TAG, "listAllSensor: null sensor");
            } else {
                Log.d(TAG, "listAllSensor: " + item.toString());
            }

        }
    }

    private void checkIfHaveOneSensor() {
        Sensor sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (null == sensor) {
            Log.d(TAG, "listAllSensor: " + "No have sensor TYPE_GRAVITY");
        } else {
            Log.d(TAG, "listAllSensor: " + "have sensor TYPE_GRAVITY");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        registerListener_TYPE_ACCELEROMETER();    // 加速度传感器
//        registerListener_TYPE_ORIENTATION();      // 方位传感器
//        registerListener_TYPE_ORIENTATION_4_depressed();     // 方位传感器
//        registerListener_TYPE_GYROSCOPE();        // 螺仪传感器
//        registerListener_TYPE_MAGNETIC_FIELD();   // 磁场传感器
//        registerListener_TYPE_GRAVITY();           // 重力传感器
//        registerListener_TYPE_LINEAR_ACCELERATION(); // 线性加速度传感器
        registerListener_TYPE_AMBIENT_TEMPERATURE();    // 温度传感器
//        // 为系统的光传感器注册监听器
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_GAME);
//        // 为系统的压力传感器注册监听器
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE), SensorManager.SENSOR_DELAY_GAME);
    }

    private void registerListener_TYPE_ACCELEROMETER() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void registerListener_TYPE_ORIENTATION() {
        // 为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
    }

    // handle Sensor.TYPE_ORIENTATION depressed,START
    private void registerListener_TYPE_ORIENTATION_4_depressed() {
        // 初始化加速度传感器
        mSensorManager.registerListener(new MySensorEventListener(), mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        // 初始化地磁场传感器
        mSensorManager.registerListener(new MySensorEventListener(), mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
    }

    class MySensorEventListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                gravity = event.values;
            }
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                geomagnetic = event.values;
            }
            calculateOrientation();
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }
    }

    // 计算方向
    private void calculateOrientation() {
        SensorManager.getRotationMatrix(r, I, gravity, geomagnetic);
        SensorManager.getOrientation(r, values);
        values[0] = (float) Math.toDegrees(values[0]);
        values[1] = (float) Math.toDegrees(values[1]);
        values[2] = (float) Math.toDegrees(values[2]);
        values[2] = (-values[2]);
        onSensorChanged_TYPE_ORIENTATION(values, mOrientationTv2);
    }
    // handle Sensor.TYPE_ORIENTATION depressed,END

    private void registerListener_TYPE_GYROSCOPE() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void registerListener_TYPE_MAGNETIC_FIELD() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
    }


    private void registerListener_TYPE_GRAVITY() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void registerListener_TYPE_LINEAR_ACCELERATION() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void registerListener_TYPE_AMBIENT_TEMPERATURE() {
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), SensorManager.SENSOR_DELAY_GAME);
        boolean supported = !mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE), SensorManager.SENSOR_DELAY_GAME);
        Log.d(TAG, "registerListener_TYPE_AMBIENT_TEMPERATURE: " + (supported ? "" : "Not") + "supported and successfully enabled");
    }

    @Override
    protected void onPause() {
        // 程序暂停时取消注册传感器监听器
        mSensorManager.unregisterListener(this);
        super.onPause();
    }

    // 当传感器精度改变时回调该方法
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        switch (sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                onAccuracyChanged4Accelerometer(sensor, accuracy);
                break;
        }
    }

    // 获取触发event的传感器类型
    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        int sensorType = event.sensor.getType();
        StringBuilder sb = null;

        switch (sensorType) {
            // 加速度传感器
            case Sensor.TYPE_ACCELEROMETER:
                onSensorChanged_TYPE_ACCELEROMETER(event);
                break;

            // 方向传感器
            case Sensor.TYPE_ORIENTATION:
                onSensorChanged_TYPE_ORIENTATION(event);
                break;

            // 陀螺仪传感器
            case Sensor.TYPE_GYROSCOPE:
                onSensorChanged_TYPE_GYROSCOPE(event);
                break;

            // 磁场传感器
            case Sensor.TYPE_MAGNETIC_FIELD:
                onSensorChanged_TYPE_MAGNETIC_FIELD(event);
                break;

            // 重力传感器
            case Sensor.TYPE_GRAVITY:
                onSensorChanged_TYPE_GRAVITY(event);
                break;

            // 线性加速度传感器
            case Sensor.TYPE_LINEAR_ACCELERATION:
                onSensorChanged_TYPE_LINEAR_ACCELERATION(event);
                break;

            // 温度传感器
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
            case Sensor.TYPE_TEMPERATURE:
                onSensorChanged_TYPE_AMBIENT_TEMPERATURE(event);
                break;

            // 光传感器
            case Sensor.TYPE_LIGHT:
                sb = new StringBuilder();
                sb.append("当前光的强度为：");
                sb.append(values[0]);
                etLight.setText(sb.toString());
                break;
            // 压力传感器
            case Sensor.TYPE_PRESSURE:
                sb = new StringBuilder();
                sb.append("当前压力为：");
                sb.append(values[0]);
                etPressure.setText(sb.toString());
                break;
        }
    }

    public void onSensorChanged_TYPE_ACCELEROMETER(SensorEvent event) {
        float[] values = event.values;
        StringBuilder sb = new StringBuilder();
        sb.append("(X,Y,Z)方向上的加速度=");
        sb.append("(");
        sb.append(values[0]);
        sb.append(",");
        sb.append(values[1]);
        sb.append(",");
        sb.append(values[2]);
        sb.append(")");
        Log.d(TAG, "onSensorChanged_TYPE_ACCELEROMETER: " + sb.toString());
        mAccelerometerTv.setText(sb.toString());
    }

    private void onSensorChanged_TYPE_ORIENTATION(SensorEvent event) {
        float[] values = event.values;
        onSensorChanged_TYPE_ORIENTATION(values, mOrientationTv);
    }

    private void onSensorChanged_TYPE_ORIENTATION(float[] values, TextView tv) {
        // 获取触发event的传感器类型
        String sb = "绕Z轴转过的角度：" + values[0] +
                "\n绕X轴转过的角度：" + values[1] +
                "\n绕Y轴转过的角度：" + values[2];
        tv.setText(sb);
    }

    private void onSensorChanged_TYPE_GYROSCOPE(SensorEvent event) {
        float[] values = event.values;
        String sb = "绕X轴旋转的角速度：" + values[0] +
                "\n绕Y轴旋转的角速度：" + values[1] +
                "\n绕Z轴旋转的角速度：" + values[2];
        etGyro.setText(sb);
    }

    private void onSensorChanged_TYPE_MAGNETIC_FIELD(SensorEvent event) {
        float[] values = event.values;
        String sb = "X轴方向上的磁场强度：" + values[0] +
                "\nY轴方向上的磁场强度：" + values[1] +
                "\nZ轴方向上的磁场强度：" + values[2];
        etMagnetic.setText(sb);
    }

    private void onSensorChanged_TYPE_GRAVITY(SensorEvent event) {
        float[] values = event.values;
        String sb = "X轴方向上的重力：" + values[0] +
                "\nY轴方向上的重力：" + values[1] +
                "\nZ方向上的重力：" + values[2];
        etGravity.setText(sb);
    }

    private void onSensorChanged_TYPE_LINEAR_ACCELERATION(SensorEvent event) {
        float[] values = event.values;
        String sb = "X轴方向上的线性加速度：" + values[0] +
                "\nY轴方向上的线性加速度：" + values[1] +
                "\nZ轴方向上的线性加速度：" + values[2];
        etLinearAcc.setText(sb);
    }

    private void onSensorChanged_TYPE_AMBIENT_TEMPERATURE(SensorEvent event) {
        float[] values = event.values;
        String sb = "当前温度为：" + values[0];
        etTemerature.setText(sb);
    }

    public void onAccuracyChanged4Accelerometer(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged: accuracy=" + accuracy);
    }
}