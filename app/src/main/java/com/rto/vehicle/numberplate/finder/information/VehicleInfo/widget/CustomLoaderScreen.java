package com.rto.vehicle.numberplate.finder.information.VehicleInfo.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

import com.rto.vehicle.numberplate.finder.information.R;

public class CustomLoaderScreen extends FrameLayout {

    CircularFillableLoaders circularFillableLoaders;
    Context mContext;
    Handler mHandler;
    Runnable progressTask;
    Runnable repeatTask;
    Runnable startDataFetchingTask;
    private Callback callback;
    private boolean loadingStarted;
    private int progress;


    public CustomLoaderScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.loadingStarted = false;
        this.mHandler = new Handler();
        this.progress = 10;
        this.startDataFetchingTask = () -> {
            CustomLoaderScreen.this.loadingStarted = true;
            if (CustomLoaderScreen.this.callback != null) {
                CustomLoaderScreen.this.callback.start();
            }
        };
        this.mContext = context;
        this.circularFillableLoaders = View.inflate(context, R.layout.view_custom_loader, this).findViewById(R.id.cfl_progress);
        this.progressTask = () -> {
            int i = CustomLoaderScreen.this.progress + 10;
            CustomLoaderScreen.this.progress = i;
            CustomLoaderScreen.this.circularFillableLoaders.setProgress(i);
            CustomLoaderScreen.this.repeatTask.run();
        };
        this.repeatTask = () -> {
            if (CustomLoaderScreen.this.progress != 90) {
                CustomLoaderScreen.this.mHandler.postDelayed(CustomLoaderScreen.this.progressTask, 1000L);
            }
        };
    }

    public void setVisibilityCustomLoaderScreen(final int i) {
        if (i == 0) {
            this.mHandler.postDelayed(this.startDataFetchingTask, 2000L);
            this.progressTask.run();
            super.setVisibility(i);
        } else if (i == 8) {
            this.circularFillableLoaders.setProgress(100);
            this.mHandler.removeCallbacksAndMessages(null);
            restartProgress();
            this.mHandler.postDelayed(() -> setVisibilityCustomLoaderScreen(i), 1000L);
        }
    }

    private void restartProgress() {
        this.mHandler.removeCallbacks(this.startDataFetchingTask);
        this.mHandler.removeCallbacksAndMessages(null);
        this.progress = 0;
        this.loadingStarted = false;
    }

    public void finishLoading() {
        this.mHandler.removeCallbacks(this.startDataFetchingTask);
        this.circularFillableLoaders.setProgress(100);
        this.mHandler.removeCallbacksAndMessages(null);
        restartProgress();
        this.mHandler.postDelayed(() -> finishloadingCustomLoaderScreen(), 1000L);
    }

    public void finishloadingCustomLoaderScreen() {
        super.setVisibility(View.GONE);
        this.circularFillableLoaders.setProgress(0);
    }

    public boolean isLoadingStarted() {
        return this.loadingStarted;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void start();
    }
}
