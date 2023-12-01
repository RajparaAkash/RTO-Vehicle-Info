package com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils;

import android.content.Context;


public class GlobalContext {
    private static GlobalContext mInstance;
    private Context context;

    private GlobalContext(Context context) {
        if (this.context == null) {
            this.context = context;
        }
    }

    public static void initialize(Context context) {
        if (mInstance == null) {
            mInstance = new GlobalContext(context);
        }
    }

    public static GlobalContext getInstance() {
        return mInstance;
    }

    public Context getContext() {
        return this.context;
    }
}
