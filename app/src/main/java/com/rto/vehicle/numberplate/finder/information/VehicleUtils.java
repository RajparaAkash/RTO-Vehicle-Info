package com.rto.vehicle.numberplate.finder.information;

import android.app.Activity;
import android.widget.Toast;

public class VehicleUtils {

    public static final String CONNECT = "Please check internet connection.";
    public static final String REG_NOT_EXIT = "Registration No. does not exist!!!";
    public static final int SERVER_TIMEOUT = 120000;
    public static final String SUB_URL = "https://parivahan.gov.in/rcdlstatus/vahan/rcDlHome.xhtml";

    public static void showSnake(String str, Activity activity) {
        Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
    }

}
