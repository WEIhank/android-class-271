package com.example.user.simpleui;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Handler;

import java.lang.ref.WeakReference;
import java.util.logging.LogRecord;

public class OrderDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Order order = getIntent().getParcelableExtra("order");

        TextView noteTextView = (TextView)findViewById(R.id.noteTextView);
        TextView orderResultTextView = (TextView)findViewById(R.id.orderResultsTextView);
        TextView storeInfoTextView= (TextView)findViewById(R.id.storeinfoTextView);


        noteTextView.setText(order.getNote());
        storeInfoTextView.setText(order.getStoreInfo());

        String orderResultsTextView = "";
        for (DrinkOrder drinkOrder: order.getDrinkOrders())
        {
            String mNumber = String.valueOf(drinkOrder.getmNumber());
            String lNumber = String.valueOf(drinkOrder.getlNumber());
            String drinkName = drinkOrder.getDrink().getName();
            orderResultsTextView += drinkName + "  M: " + mNumber + "  L:  " + lNumber + "\n";
        }

        orderResultTextView .setText(orderResultsTextView);



        ImageView staticMapImageView = (ImageView)findViewById(R.id.staticMapImageView);

        (new GeoCodingTask(staticMapImageView)).execute("台北市北投區洲美街196巷106號");

        Log.e("Main Thread ID", Long.toString(Thread.currentThread().getId()));


    }

    public static class GeoCodingTask extends AsyncTask<String,Void,Bitmap>
    {

        WeakReference<ImageView> imageViewWeakReference;


        @Override
        protected Bitmap doInBackground(String... params) {
            double[] latlng = Utils.getLatLngFromAddress(params[0]);
            if(latlng != null)
            {
                return Utils.getStaticMapFromLatLng(latlng);
            }
            return null;
        }

        @Override
        protected void omPostExcute(Bitmap bitmap){
            super.onPostExecute(bitmap);
            if (bitmap!=null)
            {
                if (imageViewWeakReference.get()!=null)
                {
                    ImageView imageView = imageViewWeakReference.get();
                    imageView.setImageBitmap(bitmap);
                }
            }


        }
        public GeoCodingTask(ImageView imageView)
        {
            imageViewWeakReference = new WeakReference<ImageView>(imageView);
        }
    }
}

