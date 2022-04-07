package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;

public class PowerConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //是否正在充电
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;//充电中或已充满
        int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);//当前充电方式
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;//USB
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;//充电器
        if (isCharging) {
            Log.d("PowerConnectionReceiver", "正在充电!");
            if(usbCharge){
                Log.d("PowerConnectionReceiver", "正在使用USB充电!" );
            }
            else if(acCharge){
                Log.d("PowerConnectionReceiver", "正在使用U充电器充电！");
            }

        } else {
            Log.d("PowerConnectionReceiver", "充电器未连接！");
        }
    }
}