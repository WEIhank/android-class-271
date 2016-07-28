package com.example.user.simpleui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
        TextView textView;
        EditText editText;
        RadioGroup radioGroup;
        ListView listView;
        Spinner spinner;

        String drink = "black tea";

        List<Order> orders = new ArrayList<>();
        List<String> store = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.storeinfoTextView);
        editText = (EditText)findViewById(R.id.editText);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        listView = (ListView)findViewById(R.id.listView);
        spinner = (Spinner)findViewById(R.id.spinner);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                drink = radioButton.getText().toString();
            }
        });
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    submit(v);
                    return true;
                }
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Order order = (Order) parent.getAdapter().getItem(position);
                // Toast.makeText(MainActivity.this,"YOU Click on" + order.note, Toast.LENGTH_SHORT).show();
                Snackbar.make(parent, "You click on" + order.note, Snackbar.LENGTH_INDEFINITE).show();
            }
        });
        setupListView();
        setupSpinner();

        Log.d("debug","MainActivty OnCreate");

    }

    private void setupSpinner() {
        String[] data = getResources().getStringArray(R.array.storeInfos);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,data);
        spinner.setAdapter(adapter);
    }

    private void setupListView()

    {
//        String[] data = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13","14","15"};
//        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, orders);
        OrderAdapter adapter = new OrderAdapter(this, orders);
        listView.setAdapter(adapter);
    }



            public void submit(View view) {
                String text = editText.getText().toString();
                String result = text + "order:" + drink;
                textView.setText(result);
                editText.setText("");

                Order order = new Order();
                order.note = text;
                order.drink = drink;
                order.storeInfo = (String)spinner.getSelectedItem();

                orders.add(order);
                setupListView();

            }
    public  void goToMenu(View view)
    {
        Intent intent = new Intent();
        intent.setClass(this,DrinkMenuActivty.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug","MainActivty onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "MainActivty onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "MainActivty onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "MainActivty onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("debug", "MainActivty onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug", "MainActivty onDestory");
    }
}




