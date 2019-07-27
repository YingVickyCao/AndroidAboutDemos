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
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);  // ①
    }

    @Override
    protected void onResume() {
        super.onResume();
//        registerListener_TYPE_ACCELEROMETER();    // 加速度传感器
//        registerListener_TYPE_ORIENTATION();      // 方位传感器
//        registerListener_TYPE_ORIENTATION2();     // 方位传感器
//        registerListener_TYPE_GYROSCOPE();        // 螺仪传感器
//        registerListener_TYPE_MAGNETIC_FIELD();   // 磁场传感器
        registerListener_TYPE_GRAVITY();            // 重力传感器
//        // 为系统的线性加速度传感器注册监听器
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_GAME);
//        // 为系统的温度传感器注册监听器
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE), SensorManager.SENSOR_DELAY_GAME);
//        // 为系统的光传感器注册监听器
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_GAME);
//        // 为系统的压力传感器注册监听器
//        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE), SensorManager.SENSOR_DELAY_GAME);
    }

    private void registerListener_TYPE_ACCELEROMETER() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL); // ②
    }

    private void registerListener_TYPE_ORIENTATION() {
        // 为系统的方向传感器注册监听器
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
    }

    // handle Sensor.TYPE_ORIENTATION depressed,START
    private void registerListener_TYPE_ORIENTATION2() {
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
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_GAME);
    }


    private void registerListener_TYPE_GRAVITY() {
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_GAME);
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
                sb = new StringBuilder();
                sb.append("X轴方向上的线性加速度：");
                sb.append(values[0]);
                sb.append("\nY轴方向上的线性加速度：");
                sb.append(values[1]);
                sb.append("\nZ轴方向上的线性加速度：");
                sb.append(values[2]);
                etLinearAcc.setText(sb.toString());
                break;
            // 温度传感器
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                sb = new StringBuilder();
                sb.append("当前温度为：");
                sb.append(values[0]);
                etTemerature.setText(sb.toString());
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
        StringBuilder sb = new StringBuilder();
        sb.append("绕Z轴转过的角度：");
        sb.append(values[0]);
        sb.append("\n绕X轴转过的角度：");
        sb.append(values[1]);
        sb.append("\n绕Y轴转过的角度：");
        sb.append(values[2]);
        tv.setText(sb.toString());
    }

    private void onSensorChanged_TYPE_GYROSCOPE(SensorEvent event) {
        float[] values = event.values;
        StringBuilder sb = new StringBuilder();
        sb.append("绕X轴旋转的角速度：");
        sb.append(values[0]);
        sb.append("\n绕Y轴旋转的角速度：");
        sb.append(values[1]);
        sb.append("\n绕Z轴旋转的角速度：");
        sb.append(values[2]);
        etGyro.setText(sb.toString());
    }

    private void onSensorChanged_TYPE_MAGNETIC_FIELD(SensorEvent event) {
        float[] values = event.values;
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append("X轴方向上的磁场强度：");
        sb.append(values[0]);
        sb.append("\nY轴方向上的磁场强度：");
        sb.append(values[1]);
        sb.append("\nZ轴方向上的磁场强度：");
        sb.append(values[2]);
        etMagnetic.setText(sb.toString());
    }

    private void onSensorChanged_TYPE_GRAVITY(SensorEvent event) {
        float[] values = event.values;
        StringBuilder sb = new StringBuilder();
        sb.append("X轴方向上的重力：");
        sb.append(values[0]);
        sb.append("\nY轴方向上的重力：");
        sb.append(values[1]);
        sb.append("\nZ方向上的重力：");
        sb.append(values[2]);
        etGravity.setText(sb.toString());
    }

    public void onAccuracyChanged4Accelerometer(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged: accuracy=" + accuracy);
    }
}