package com.beecode.barcode.builder;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by airyuxun on 2016/10/20.
 */

public class BarcodeBuidlerManager extends ReactContextBaseJavaModule {
    public static final String name = "BarcodeBuilder";

    public BarcodeBuidlerManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return name;
    }
    @ReactMethod
    public void buildBase64(final String content,final int size,final Promise promise) {

        try {
            Runnable buildRunnable = new Runnable() {

                @Override
                public void run() {

                    String result = null;
                    try {
                        result = new BarcodeBuilder().buildCodeBase64(content,size);
                    } catch (Exception e) {
                        promise.reject(e);
                    }
                    promise.resolve(result);
                }
            };

            Thread runnable = new Thread(buildRunnable);
            runnable.start();
        } catch(Exception e) {
            promise.reject(e);
        }
    }

    @ReactMethod
    public void buildFile(final String path,final String content,final int size,final Promise promise) {

        try {
            Runnable buildRunnable = new Runnable() {

                @Override
                public void run() {

                    try {
                        String result = new BarcodeBuilder().buildFile(path,content,size);
                    } catch (Exception e) {
                        promise.reject(e);
                    }
                    promise.resolve(path);
                }
            };

            Thread runnable = new Thread(buildRunnable);
            runnable.start();
        } catch(Exception e) {
            promise.reject(e);
        }
    }
}
