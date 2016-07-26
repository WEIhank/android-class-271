package com.example.user.simpleui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
        TextView textView;
        EditText editText;
        RadioGroup radioGroup;

        String sex = "male";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        }

    public void onCheckedChanged(RadioGroup group, int checkedId)() {
                if (checkedId == R.id.maleRadioButton) {
                    sex = "male";
                } else if (checkedId == R.id.femaleRadioButton) {
                    sex = "female";
                }
            });

            public void submit(View view) {
                String text = editText.getText().toString();
                text = text + "sex:" + sex;
                textView.setText(text);
                editText.setText("");

            }
        }




}