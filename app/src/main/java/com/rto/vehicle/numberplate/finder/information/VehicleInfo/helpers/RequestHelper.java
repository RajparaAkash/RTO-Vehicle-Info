package com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers;

import android.content.Context;
import android.os.Build;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.PreferencesHelper;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;

import java.util.HashMap;
import java.util.Map;


public class RequestHelper {
    public static Map<String, Object> getRequestParams(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("packageName", "com.tradetu.trendingapps.vehicleregistrationdetails");
        hashMap.put("appVersion", "1.0.1.65");
        hashMap.put("appVersionCode", "65");
        hashMap.put("manufacturer", Utils.isNullOrEmpty(Build.MANUFACTURER) ? "" : Build.MANUFACTURER);
        hashMap.put("model", Utils.isNullOrEmpty(Build.MODEL) ? "" : Build.MODEL);
        hashMap.put("osVersion", Integer.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("deviceName", Utils.isNullOrEmpty(Build.DEVICE) ? "" : Build.DEVICE);
        hashMap.put("deviceBrand", Utils.isNullOrEmpty(Build.BRAND) ? "" : Build.BRAND);
        hashMap.put("deviceWidth", Integer.valueOf(Utils.getDeviceWidth(context)));
        hashMap.put("deviceHeight", Integer.valueOf(Utils.getDeviceHeight(context)));
        return hashMap;
    }

    public static Map<String, Object> getRequestHeaders(Context context, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("src", "android_vehicle-details");
        hashMap.put("packageName", Utils.getPackageName(context));
        hashMap.put("appVersion", "" + Utils.getAppVersionName(context));
        hashMap.put("appVersionCode", "" + Utils.getAppVersionCode(context));
        hashMap.put("apiKey", "");
        hashMap.put("clientId", "android_vehicle-details");
        hashMap.put("deviceId", Utils.getAndroidId(context));
        hashMap.put("ts", Utils.getTimeInMilli());
        hashMap.put("manufacturer", Utils.isNullOrEmpty(Build.MANUFACTURER) ? "" : Build.MANUFACTURER);
        hashMap.put("model", Utils.isNullOrEmpty(Build.MODEL) ? "" : Build.MODEL);
        hashMap.put("osVersion", "" + Build.VERSION.SDK_INT);
        hashMap.put("deviceName", Utils.isNullOrEmpty(Build.DEVICE) ? "" : Build.DEVICE);
        hashMap.put("deviceBrand", Utils.isNullOrEmpty(Build.BRAND) ? "" : Build.BRAND);
        String randomNumber = Utils.getRandomNumber();
        hashMap.put("salt", randomNumber);
        hashMap.put("auth", Utils.encryptStr(context, randomNumber, str));
        hashMap.put("deviceHeight", "" + Utils.getDeviceHeight(context));
        hashMap.put("deviceWidth", "" + Utils.getDeviceWidth(context));
        hashMap.put("fcmToken", PreferencesHelper.getKeyToken());
        return hashMap;
    }
}
