package com.example.user.simpleui;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import java.util.PriorityQueue;

/**
 * Created by user on 2016/8/2.
 */
public class SimpleApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();

        ParseObject.registerSubclass(Order.class);
        ParseObject.registerSubclass(DrinkOrder.class);
        ParseObject.registerSubclass(Drink.class);



        Parse.initialize(new Parse.Configuration.Builder(this)
                        .applicationId("76ee57f8e5f8bd628cc9586e93d428d5")
                        .server("http://parseserver-ps662-env.us-east-1.elasticbeanstalk.com/parse/")
                        .clientKey("76ee57f8e5f8bd628cc9586e93d428d5")
                .enableLocalDataStore()
                .build());
    }
}
