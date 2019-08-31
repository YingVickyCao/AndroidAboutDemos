package com.hades.example.android.media.audio.audio_effect;

import android.Manifest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AutomaticGainControl;
import android.media.audiofx.BassBoost;
import android.media.audiofx.Equalizer;
import android.media.audiofx.NoiseSuppressor;
import android.media.audiofx.PresetReverb;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.hades.example.android.R;
import com.hades.example.android.lib.base.PermissionActivity;

import java.util.ArrayList;
import java.util.List;

public class TestAudioEffectActivity extends PermissionActivity {
    private MediaPlayer mPlayer;

    // 示波器
    private Visualizer mVisualizer;
    // 均衡器
    private Equalizer mEqualizer;
    // 重低音
    private BassBoost mBass;

    // 预设的音场
    private PresetReverb mPresetReverb;
    private List<Short> reverbNames = new ArrayList<>();
    private List<String> reverbVals = new ArrayList<>();

    // 回声消除
    private AcousticEchoCanceler mAcousticEchoCanceler;

    // 自动增益控制
    private AutomaticGainControl mAutomaticGainControl;

    // 噪音压制
    private NoiseSuppressor mNoiseSuppressor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_effect_layout);

        initViews();

        // 设置控制音乐声音
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mPlayer = MediaPlayer.create(this, R.raw.mp3_2);

        setupVisualizer();
        setupEqualizer();
        setupBassBoost();
        setupPresetReverb();
        setAcousticEchoCanceler(); //On AudioRecord session
        setAutomaticGainControl();
        setNoiseSuppressor();

        mPlayer.start();
    }

    @Override
    protected void requestPermission() {
        checkPermission("Request permission for Audio Visualizer", Manifest.permission.RECORD_AUDIO, Manifest.permission.MODIFY_AUDIO_SETTINGS);
    }

    private void setupVisualizer() {
        // 创建MyVisualizerView组件，用于显示波形图
        final VisualizerView mVisualizerView = findViewById(R.id.visualizerView);
        // 以MediaPlayer的AudioSessionId创建Visualizer
        // 相当于设置Visualizer负责显示该MediaPlayer的音频数据

        /*
            ERROR:Cannot initialize Visualizer engine, error: -3
            XML permission:<uses-permission android:name="android.permission.RECORD_AUDIO"/>
            When android >= 6.0, runtime permission.
         */
        mVisualizer = new Visualizer(mPlayer.getAudioSessionId());
        mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
        // 为mVisualizer设置监听器
        mVisualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener() {
            @Override
            public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate) {
            }

            @Override
            public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate) {
                // 用waveform波形数据更新mVisualizerView组件
                mVisualizerView.updateVisualizer(waveform);
            }
        }, Visualizer.getMaxCaptureRate() / 2, true, false);
        mVisualizer.setEnabled(true);
    }

    // 均衡控制器
    private void setupEqualizer() {
        mEqualizer = new Equalizer(0, mPlayer.getAudioSessionId()); // 以MediaPlayer的AudioSessionId创建Equalizer, 相当于设置Equalizer负责控制该MediaPlayer
        mEqualizer.setEnabled(true);

        final short minEQLevel = mEqualizer.getBandLevelRange()[0]; // 获取均衡控制器支持最小值和最大值
        final short maxEQLevel = mEqualizer.getBandLevelRange()[1];
        short brands = mEqualizer.getNumberOfBands(); // 获取均衡控制器支持的所有频率
        for (short i = 0; i < brands; i++) {
            LinearLayout tmpLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.audio_effect_equalizer, null);
            ((TextView) tmpLayout.findViewById(R.id.centerFreq)).setText(String.valueOf(mEqualizer.getCenterFreq(i) / 1000));
            ((TextView) tmpLayout.findViewById(R.id.minOfBindLevel)).setText(String.valueOf(minEQLevel / 100));
            ((TextView) tmpLayout.findViewById(R.id.maxOfBindLevel)).setText(String.valueOf(maxEQLevel / 100));
            SeekBar bar = tmpLayout.findViewById(R.id.bandLevelProgress);
            bar.setMax(maxEQLevel - minEQLevel);
            bar.setProgress(mEqualizer.getBandLevel(i));
            final short brand = i;
            bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    // 设置该频率的均衡值
                    mEqualizer.setBandLevel(brand, (short) (progress + minEQLevel));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
            ViewGroup equalizerPart = findViewById(R.id.equalizerPart);
            equalizerPart.addView(tmpLayout);
        }
    }

    // 重低音控制器
    private void setupBassBoost() {
        // 以MediaPlayer的AudioSessionId创建BassBoost
        // 相当于设置BassBoost负责控制该MediaPlayer
        mBass = new BassBoost(0, mPlayer.getAudioSessionId());
        // 设置启用重低音效果
        mBass.setEnabled(true);

        SeekBar bar = findViewById(R.id.bassBoostSeekBar);
        bar.setMax(1000);
        bar.setProgress(0);
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 设置重低音的强度
                mBass.setStrength((short) progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    // 初始化预设音场控制器
    private void setupPresetReverb() {
        // 以MediaPlayer的AudioSessionId创建PresetReverb
        mPresetReverb = new PresetReverb(0, mPlayer.getAudioSessionId());
        // 设置启用预设音场控制
        mPresetReverb.setEnabled(true);

        // 获取系统支持的所有预设音场
        for (short i = 0; i < mEqualizer.getNumberOfPresets(); i++) {
            reverbNames.add(i);
            reverbVals.add(mEqualizer.getPresetName(i));
        }
        Spinner sp = findViewById(R.id.presetReverbSpinner);
        sp.setAdapter(new ArrayAdapter<String>(TestAudioEffectActivity.this, android.R.layout.simple_spinner_item, reverbVals));
        sp.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                short preset = reverbNames.get(arg2);
                if (preset < PresetReverb.PRESET_NONE || preset > PresetReverb.PRESET_PLATE) {
                    Toast.makeText(TestAudioEffectActivity.this, "Not support Preset " + reverbVals.get(arg2) + "-" + preset, Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresetReverb.setPreset(reverbNames.get(arg2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    private void setAcousticEchoCanceler() {
        toggleAcousticEchoCanceler(true);
        ((Switch) findViewById(R.id.toggleAcousticEchoCanceler)).setOnCheckedChangeListener((buttonView, isChecked) -> toggleAcousticEchoCanceler(isChecked));
    }

    /*
     ERROR:
     E/AudioEffect: set(): AudioFlinger could not create effect 7b491460-8d4d-11e0-bd61-0002a5d5c51b / ec7178ec-e5e1-4432-a3f4-4657e6795210, status: -22
     E/AudioEffects-JNI: Error setting AudioEffect
     E/AudioEffect-JAVA: Error code -19 when initializing AudioEffect.

     -22 = BAD_VALUE
     */
    private void toggleAcousticEchoCanceler(boolean enable) {
        if (!AcousticEchoCanceler.isAvailable()) {
            return;
        }

        if (null == mAcousticEchoCanceler) {
            mAcousticEchoCanceler = AcousticEchoCanceler.create(mPlayer.getAudioSessionId());
        }
        if (null != mAcousticEchoCanceler) {
            mAcousticEchoCanceler.setEnabled(enable);
        }
    }

    private void setAutomaticGainControl() {
        toggleAutomaticGainControl(true);
        ((Switch) findViewById(R.id.toggleAutomaticGainControl)).setOnCheckedChangeListener((buttonView, isChecked) -> toggleAutomaticGainControl(isChecked));
    }

    private void toggleAutomaticGainControl(boolean enable) {
        if (!AutomaticGainControl.isAvailable()) {
            return;
        }

        if (null == mAutomaticGainControl) {
            mAutomaticGainControl = AutomaticGainControl.create(mPlayer.getAudioSessionId());
        }

        if (null != mAutomaticGainControl) {
            mAutomaticGainControl.setEnabled(enable);
        }
    }

    private void setNoiseSuppressor() {
        toggleAutomaticGainControl(true);
        ((Switch) findViewById(R.id.toggleAutomaticGainControl)).setOnCheckedChangeListener((buttonView, isChecked) -> toggleNoiseSuppressor(isChecked));
    }

    private void toggleNoiseSuppressor(boolean enable) {
        if (!NoiseSuppressor.isAvailable()) {
            return;
        }

        if (null == mNoiseSuppressor) {
            mNoiseSuppressor = NoiseSuppressor.create(mPlayer.getAudioSessionId());
        }

        if (null != mNoiseSuppressor) {
            mNoiseSuppressor.setEnabled(enable);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing() && mPlayer != null) {
            // 释放所有对象
            mVisualizer.release();
            mEqualizer.release();
            mPresetReverb.release();
            mBass.release();
            mPlayer.release();
            mPlayer = null;
        }
    }
}