package com.hades.example.android.media.record.audio;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hades.example.android.R;

import java.io.File;

// Permission: RECORD_AUDIO,READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE
public class TestRecordAudioFragment extends Fragment {
    File soundFile;
    MediaRecorder mRecorder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.media_resord_audio, container, false);
        view.findViewById(R.id.startRecord).setOnClickListener(v -> startRecord());
        view.findViewById(R.id.stopRecord).setOnClickListener(v -> stopRecord());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopRecord();
    }

    private void startRecord() {
        if (!Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            Toast.makeText(getActivity(), "SD not exit.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            soundFile = new File(Environment.getExternalStorageDirectory().getCanonicalFile() + "/sound.amr");
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);            // 声音来源. 一般指定来自 MIC（麦克风）
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);    // 输出格式。before setAudioEncoder().
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);       // 编码格式。after setOutputFormat()
            mRecorder.setAudioEncodingBitRate(50000);                           // 音频编码比特率。after setAudioSource(),after setOutFormat()
            mRecorder.setAudioSamplingRate(8000);                               // 采用率
            mRecorder.setOutputFile(soundFile.getAbsolutePath());
            mRecorder.prepare();
            mRecorder.start();  // // 开始录音
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopRecord(){
        if (soundFile != null && soundFile.exists()) {
            mRecorder.stop();     // 停止录音
            mRecorder.reset();
            mRecorder.release();  // 释放资源
            mRecorder = null;
        }

        if (soundFile != null && soundFile.exists()) {
            soundFile = null;
        }
    }
}