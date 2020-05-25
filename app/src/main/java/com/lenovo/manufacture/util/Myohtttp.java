package com.lenovo.manufacture.util;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Myohtttp  {
    private static final String TAG = "MyOkhttp";
    private static String getjson(String url, FormBody.Builder fb) throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://2392841l9n.wicp.vip/dataInterface/"+url)
                .post(fb.build())
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .addHeader("Postman-Token", "510c1c69-1b9d-4586-8c7a-ba8b6d4a67d0")
                .build();

        Response response = client.newCall(request).execute();
        String string = response.body().string();
        Log.d(TAG, "getjson: "+string);
        return string;
    }

    private static Gson gson = new Gson();
    public static <T> T POST(String url, FormBody.Builder fb, Class<T> tClass) throws Exception {
        String getjson = getjson(url, fb);
        Log.d(TAG, "POST: "+getjson);
        T t = gson.fromJson(getjson, tClass);
        return t;
    }
    public static <T> List<T> POST(String url, Class<T> tClass) throws Exception {
        String getjson = getjson(url, new FormBody.Builder());
        JSONObject jsonObject = new JSONObject(getjson);
        JSONArray data = jsonObject.getJSONArray("data");
        List<T> ts = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            JSONObject jsonObject1 = data.getJSONObject(i);
            String string = jsonObject1.getString(jsonObject1.keys().next());
            T t = gson.fromJson(string, tClass);
            ts.add(t);
        }
        return ts;
    }

    public static JSONArray POST(String url, FormBody.Builder fb) throws Exception {
        String getjson = getjson(url, fb);
        return new JSONObject(getjson).getJSONArray("data");
    }
}
