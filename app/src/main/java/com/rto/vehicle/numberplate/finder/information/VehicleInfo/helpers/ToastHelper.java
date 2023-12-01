package com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers;

import android.content.Context;
import android.widget.Toast;

public class ToastHelper {
    public static void showToast(Context context, String str, boolean z) {
        Toast.makeText(context, str, !z ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
}
