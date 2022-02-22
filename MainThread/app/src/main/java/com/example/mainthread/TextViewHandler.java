package com.example.mainthread;

import android.widget.TextView;

public class TextViewHandler implements Runnable {
    public TextView textView;
    public String message;

    public TextViewHandler() {
        super();
    }

    @Override
    public void run() {
        textView.setText(message);
    }
}
