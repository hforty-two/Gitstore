package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver =new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy() ;
        unregisterReceiver(networkChangeReceiver);
    }
    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent){
            ConnectivityManager connectivityManager =
                    (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            boolean isConnected = networkInfo != null &&
                    networkInfo.isConnectedOrConnecting();

            if(isConnected){
                boolean isWiFi = networkInfo.getType()== ConnectivityManager.TYPE_WIFI;
                boolean isM = networkInfo.getType()== ConnectivityManager.TYPE_MOBILE;
                boolean isWiMax = networkInfo.getType() == ConnectivityManager.TYPE_WIMAX;
                boolean isE = networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET;
                Log.d("MainActivity","WIFI是否连接："+isWiFi);
                Log.d("MainActivity","CMNAT网络是否连接："+isM);
                Log.d("MainActivity","WiMax是否连接："+isWiMax);
                Log.d("MainActivity","以太网是否连接："+isE);
            }
            else {
                Log.d("MainActivity","连接断开！");
            }

        }

    }
}