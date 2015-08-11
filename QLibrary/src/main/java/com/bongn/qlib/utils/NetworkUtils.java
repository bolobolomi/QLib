package com.bongn.qlib.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.bongn.qlib.configs.ConfigConstants;
import com.bongn.qlib.exception.HTTPException;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Qiang on 15/7/23.
 */
public class NetworkUtils {
    private static OkHttpClient sClient;
    public static OkHttpClient getClientInstance(){
        if(null == sClient){
            sClient = new OkHttpClient();
            sClient.setConnectTimeout(ConfigConstants.OkHttp.CON_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            sClient.setReadTimeout(ConfigConstants.OkHttp.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            sClient.setWriteTimeout(ConfigConstants.OkHttp.WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        }
        return sClient;
    }

    public static <T>T get(Context context , String url , Class clazz) throws IOException, HTTPException, JSONException {
        T result = null;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = getClientInstance().newCall(request).execute();
        if(response.isSuccessful()){
            String resultStr = response.body().string();
            if (clazz.equals(String.class)) {
                result = (T) resultStr;
            } else if (clazz.equals(JSONObject.class)) {
                result = (T) new JSONObject(resultStr);
            } else if (clazz.equals(JSONArray.class)) {
                result = (T) new JSONArray(resultStr);
            } else {
                throw new IllegalArgumentException("不支持的 clazz 参数");
            }
        }else{
            throw  new HTTPException(response.code(),"错误");
        }
        return result;
    }

    public static <T>T post(Context context , String url , Map<String,Object> params , Class clazz) throws IOException, HTTPException, JSONException {
        T result = null;
        RequestBody formBody = null;
        if(null != params && params.size()>0){
            FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
            for(Map.Entry<String ,Object> entry : params.entrySet()) {
                formEncodingBuilder.add(entry.getKey(),entry.getValue().toString());
            }
            formBody = formEncodingBuilder.build();
        }
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if(null != formBody){
            requestBuilder.post(formBody);
        }
        Request request = requestBuilder.build();
        Response response = getClientInstance().newCall(request).execute();
        if(response.isSuccessful()){
            String resultStr = response.body().string();
            if (clazz.equals(String.class)) {
                result = (T) resultStr;
            } else if (clazz.equals(JSONObject.class)) {
                result = (T) new JSONObject(resultStr);
            } else if (clazz.equals(JSONArray.class)) {
                result = (T) new JSONArray(resultStr);
            } else {
                throw new IllegalArgumentException("不支持的 clazz 参数");
            }
        }else{
            throw  new HTTPException(response.code(),"错误");
        }
        return result;
    }

    public static <T>T put(Context context , String url , String str , Class clazz){
        return null;
    }

    public static <T>T delete(Context context , String url , Class clazz){
        return null;
    }

    public static final int NET_UNKNOWN_VALUE = 0;
    public static final int NET_ETHERNET_VALUE = 1;
    public static final int NET_WIFI_VALUE = 2;
    public static final int NET_2G_VALUE = 3;
    public static final int NET_3G_VALUE = 4;
    public static final int NET_4G_VALUE = 5;

    public static int getNetworkType(Context context) {
        int type = NET_UNKNOWN_VALUE;
        ConnectivityManager connectMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = connectMgr.getActiveNetworkInfo();
        if (null != info) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                type = NET_WIFI_VALUE;
            } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                int sybType = info.getSubtype();
                type = getNetworkClass(sybType);
            }
        }
        return type;
    }

    public static int getNetworkClass(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                // return ConnectivityManager.NETWORK_CLASS_2_G;
                return NET_2G_VALUE;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                // return TelephonyManager.NETWORK_CLASS_3_G;
                return NET_3G_VALUE;
            case TelephonyManager.NETWORK_TYPE_LTE:
                // return NETWORK_CLASS_4_G;
                return NET_4G_VALUE;
            default:
                // return TelephonyManager.NETWORK_CLASS_UNKNOWN;
                return NET_UNKNOWN_VALUE;
        }
    }
}
