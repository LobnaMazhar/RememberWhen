package com.example.lobna.rememberwhen.Application;

import android.app.Application;

/**
 * Created by Lobna on 07-Jan-17.
 */

public class RememberWhenApplication extends Application {
    public static RememberWhenApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static RememberWhenApplication getApp() {
        return instance;
    }
}
