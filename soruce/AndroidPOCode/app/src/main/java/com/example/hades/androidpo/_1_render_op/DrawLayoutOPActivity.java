package com.example.hades.androidpo._1_render_op;

import android.Manifest;
import android.os.Bundle;

import com.example.hades.androidpo.BaseActivity;
import com.example.hades.androidpo.R;
import com.example.hades.androidpo._1_render_op._not_block_ui.UnboundServce.PlayMusic4ServiceFragment;
import com.example.hades.androidpo._1_render_op._not_block_ui.asyncTask.Download4AsyncTaskFragment;
import com.example.hades.androidpo._1_render_op._not_block_ui.thread.Weather4ThreadFragment;
import com.example.hades.androidpo._1_render_op.layout._include.IncludeFragment;
import com.example.hades.androidpo._1_render_op.layout._reduce_hierachy.ReduceHierarchyFragment;
import com.example.hades.androidpo._1_render_op.layout._reduce_hierachy.WindowNoTitleActivity;
import com.example.hades.androidpo._1_render_op.layout._viewstub.ViewStubFragment;
import com.example.hades.androidpo._1_render_op.monitor_jank.UiPerfMoniterFragment;
import com.example.hades.androidpo._1_render_op.overdraw.ReduceOverdrawActivity;

public class DrawLayoutOPActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // SESSION:Reduce overdraw  default = white, need = black
        // Remove unneeded Window default background
//        this.getWindow().setBackgroundDrawable(null);

        setContentView(R.layout.activity_op_layout);
        checkPermission("Request storage permission", Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);

        initViews();

        findViewById(R.id.weather4thread).setOnClickListener(v -> weather4thread());
        findViewById(R.id.downloadFile4AsyncTask).setOnClickListener(v -> downloadFile4AsyncTask());
        findViewById(R.id.playMusic4Service).setOnClickListener(v -> playMusic4Service());

        findViewById(R.id.reduceHierachy).setOnClickListener(v -> reduceHierarchy());
        findViewById(R.id.pageWindowNoTitle2ReduceHierarchy).setOnClickListener(v -> pageWindowNoTitle2ReduceHierarchy());
        findViewById(R.id.viewStub).setOnClickListener(v -> viewStub());
        findViewById(R.id.include).setOnClickListener(v -> include());
        findViewById(R.id.reuseAdapterItemViewInListVieW).setOnClickListener(v -> reuseAdapterItemViewInListVieW());
        findViewById(R.id.reduceOverdraw).setOnClickListener(v -> reduceOverdraw());
        findViewById(R.id.monitorJank).setOnClickListener(v -> monitorJank());
    }

    @Override
    protected boolean isNeedCheckPermission() {
        return true;
    }

    @Override
    protected void showCurrentTest() {
        playMusic4Service();
    }

    private void reduceHierarchy() {
        showFragment(ReduceHierarchyFragment.newInstance());
    }

    private void pageWindowNoTitle2ReduceHierarchy() {
        showActivity(WindowNoTitleActivity.class);
    }

    private void weather4thread() {
        showFragment(new Weather4ThreadFragment());
    }

    private void downloadFile4AsyncTask() {
        showFragment(new Download4AsyncTaskFragment());
    }

    private void playMusic4Service() {
        showFragment(new PlayMusic4ServiceFragment());
    }

    private void viewStub() {
        showFragment(new ViewStubFragment());
    }

    private void include() {
        showFragment(new IncludeFragment());
    }

    private void reduceOverdraw() {
        showActivity(ReduceOverdrawActivity.class);
    }

    private void reuseAdapterItemViewInListVieW() {
        showFragment(new ReuseAdapterItemViewInListVieWFragment());
    }

    private void monitorJank() {
        showFragment(new UiPerfMoniterFragment());
    }
}