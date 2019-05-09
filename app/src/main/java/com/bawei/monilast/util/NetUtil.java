package com.bawei.monilast.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.common.io.CharStreams;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUtil {
    private static final NetUtil ourInstance = new NetUtil();

    public static NetUtil getInstance() {
        return ourInstance;
    }

    private NetUtil() {
    }

    public static boolean getTime(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()){
            return true;
        }else{
            return false;
        }
    }

    public void getAsyncTask(String url, final CallBaskTask callBaskTask){
        new AsyncTask<String,Void,String>(){


            @Override
            protected void onPostExecute(String s) {
                if(TextUtils.isEmpty(s)){
                    callBaskTask.onError(2002,"无数据");
                }else{
                    callBaskTask.onSuccess(s);
                }
            }

            @Override
            protected String doInBackground(String... strings) {
                return NetUtil.getData(strings[0]);
            }
        }.execute(url);
    }

    public interface CallBaskTask{
        void onError(int code,String msg);
        void onSuccess(String s);
    }

    public static String getData(String url){
        try {
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod("GET");
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                return CharStreams.toString(new InputStreamReader(connection.getInputStream()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
