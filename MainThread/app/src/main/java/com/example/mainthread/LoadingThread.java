package com.example.mainthread;

import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingThread extends Thread {
    ProgressBar progressBar;
    TextView textView;

    // Constructor
    public LoadingThread(ProgressBar progressBar, TextView textView) {
        this.progressBar = progressBar;
        this.textView = textView;
    }

    @Override
    public void run() {
        TextViewHandler textViewHandler = new TextViewHandler();
        textViewHandler.textView = textView;
        textViewHandler.message =
                textView.getResources().getString(R.string.progress_loading);
        textView.post(textViewHandler);

        ProgressBarHandler progressHandler = new ProgressBarHandler();
        progressHandler.progressBar = progressBar;
        progressHandler.maxProgress = 250;

        for (int i = 0; i < progressHandler.maxProgress; i++) {
            try {
                progressHandler.progress = i + 1;
                progressBar.post(progressHandler);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        textViewHandler.message =
                textView.getResources().getString(R.string.progress_done);
        textView.post(textViewHandler);

    }

}
