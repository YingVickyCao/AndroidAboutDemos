package com.example.hades.androidpo.monitor.memory.leak;

import android.util.Log;

import com.squareup.leakcanary.AnalysisResult;
import com.squareup.leakcanary.DisplayLeakService;
import com.squareup.leakcanary.HeapDump;

public class MemoryLeakUploadService extends DisplayLeakService {
    private final String TAG = MemoryLeakUploadService.class.getSimpleName();

    /**
     * @param heapDump 对内存文件，可以拿成hprof文件，用MAT分析
     * @param result
     * @param leakInfo leak trace 详细信息，除了内存泄漏对象，还有设备信息。
     */

    @Override
    protected void afterDefaultHandling(HeapDump heapDump, AnalysisResult result, String leakInfo) {
        Log.d(TAG, "afterDefaultHandling: ");
        if (!result.leakFound || result.excludedLeak) {
            return;
        }

        uploadLeakToServer(result, leakInfo);
    }

    private void uploadLeakToServer(AnalysisResult result, String leakInfo) {
        Log.d(TAG, "uploadLeakToServer: ");
        directlyUpload(result, leakInfo);
//        translateLeakTrace2FakeException(result.leakTraceAsFakeException(), leakInfo);
    }

    private void directlyUpload(AnalysisResult result, String leakInfo) {

    }

    private void translateLeakTrace2FakeException(RuntimeException fakeException, String leakInfo) {

    }
}
