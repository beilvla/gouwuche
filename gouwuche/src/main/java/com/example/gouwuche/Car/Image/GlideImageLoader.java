package com.example.gouwuche.Car.Image;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;


/**
 * author:Created by MingShao on 2018/4/5.
 */

public class GlideImageLoader extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
