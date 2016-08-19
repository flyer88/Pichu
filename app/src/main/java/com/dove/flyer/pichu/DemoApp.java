package com.dove.flyer.pichu;

import android.app.Application;

import com.dove.flyer.pichu.core.Pichu;

/**
 * Created by flyer on 16/8/19.
 */
public class DemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Pichu.init(true);
    }
}
