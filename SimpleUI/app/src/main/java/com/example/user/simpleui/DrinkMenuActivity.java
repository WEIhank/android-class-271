package com.example.user.simpleui;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class DrinkMenuActivity extends AppCompatActivity implements DrinkOrderDialog.OnDrinkOrderListener{

    ListView drinkMenuListView;
    TextView totalTextView;

    String[] drinkNames = new  String[]{"White Gourd Tea","Black Tea","Peal Black Tea","Mike Black Tea"};
    int[]lPrices = new int[]{25,35,35,25};
    int[]mPrices = new int[]{15,25,25,15};
    int[]images = new int[]{R.drawable.drink1 , R.drawable.drink4, R.drawable.drink3, R.drawable.drink2};

    List<Drink> drinkList = new ArrayList<>();
    List<Drink> drinkOrderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_menu_activty);

        drinkMenuListView = (ListView)findViewById(R.id.drinkMenuListView);
        totalTextView = (TextView)findViewById(R.id.totalTextView);

        setData();

        setupDrinkMenuListView();

        Log.d("Debug", "DrinkMenuActivity OnCreate");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("debug", "DrinkMainActivity onStart");
    }
    public  void setData(){
        for(int i =0; i<4;i++)
        {
            Drink drink = new Drink();
            drink.name = drinkNames[i];
            drink.lPrices = lPrices[i];
            drink.mPrices = mPrices[i];
            drink.imagesId = images[i];
            drinkList.add(drink);
        }
    }
    public void setupDrinkMenuListView(){
        DrinkAdapter adapter = new DrinkAdapter(this, drinkList);
        drinkMenuListView.setAdapter(adapter);

        drinkMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Drink drink =(Drink)parent.getAdapter().getItem(position);
                showDrinkOrderDialog(drink);
                setupTotalTextView();
            }
        });
    }

    public void  setupTotalTextView(){
        int total = 0;
        for(Drink drink :drinkOrderList){
            total += drink.mPrices;
        }
        totalTextView.setText(String.valueOf(total));
    }
    public void done(View view){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
    public void CANCEL(View view){
        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }
    private void showDrinkOrderDialog(Drink drink)
    {
        FragmentManager fragmentManager = getFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        DrinkOrderDialog dialog = DrinkOrderDialog.newInstance(drink);
        dialog.show(ft,"DrinkOrderDialog");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "DrinkMainActivity onResume");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("debug", "DrinkMainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("debug", "DrinkMainActivity onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("debug", "DrinkMainActivity onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("debug", "DrinkMainActivity onDestory");
    }

    @Override
    public void OnDrinkOrderFinished() {

    }
}
