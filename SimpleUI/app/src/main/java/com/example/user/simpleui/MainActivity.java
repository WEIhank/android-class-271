package com.example.user.simpleui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
        TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    textView = (TextView)findViewById(R.id.textView);

    }
    public void submit(View view)
    {
        textView.setText("Hellow android");
    }

    }
