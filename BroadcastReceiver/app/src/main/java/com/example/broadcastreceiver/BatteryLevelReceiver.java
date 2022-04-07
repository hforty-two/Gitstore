package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

public class BatteryLevelReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);//电池电量
        Log.d("SecondActivity","当前电量为"+level);
        if(level<=20){
                Log.d("SecondActivity","电量不足20%！请尽快充电！");
            }
        else{
                Log.d("SecondActivity","已离开电量低状态!");
            }
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);//电池容量
        //电量百分比
        float batteryPct = level / (float)scale;
    }
}
