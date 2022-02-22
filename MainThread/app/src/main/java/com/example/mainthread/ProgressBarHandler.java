package com.example.mainthread;

import android.widget.ProgressBar;

public class ProgressBarHandler implements Runnable {
    public ProgressBar progressBar;
    public int progress;
    public int maxProgress;

    @Override
    public void run() {
        progressBar.setProgress(progress);
        progressBar.setMax(maxProgress);
    }
}
