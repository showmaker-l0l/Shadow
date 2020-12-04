package com.example.shadow.http;

import android.util.Log;

import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    private static final String TAG = "post";
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public static void sendOKHttpRequest(String address, JsonObject json, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();

        Log.d(TAG, "sendOKHttpRequest: "+address);

        RequestBody body = RequestBody.create(String.valueOf(json), JSON);
        Request request = new Request.Builder()
                    .url(address)
                    .post(body)
                    .build();
        Log.d(TAG, "sendOKHttpRequest: "+request);
        client.newCall(request).enqueue(callback);
    }

    public static void getMessage(okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://192.168.42.65:8080/Shadow/message")
                .build();

        client.newCall(request).enqueue(callback);
    }
}
