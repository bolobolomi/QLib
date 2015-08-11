package com.bongn.testapp.application;

import android.app.Application;

import com.bongn.qlib.configs.ImagePipelineConfigFactory;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Qiang on 15/8/11.
 */
public class ThisApp extends Application {
    @Override
    public void onCreate() {
        Fresco.initialize(this , ImagePipelineConfigFactory.getOkHttpImagePipelineConfig(this));

        super.onCreate();
    }
}
