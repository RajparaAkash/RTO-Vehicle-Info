package com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers;

import android.app.Activity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.VehicleDetails;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;

import org.json.JSONObject;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.GlobalReferenceEngine;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;

import java.util.HashMap;


public class VehicleDetailsLogger {
    public static void logVehicleDetails(Activity activity, final VehicleDetails vehicleDetails) {
        if (!GlobalReferenceEngine.isLogServerData || !Utils.isNetworkConnected(activity) || vehicleDetails == null || vehicleDetails.isEmptyResponse()) {
            return;
        }
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("registrationNo", vehicleDetails.getRegistrationNo());
            hashMap.put("details", new Gson().toJson(vehicleDetails));
            TaskHandler.newInstance().pushVehicleDetails(activity, hashMap, new TaskHandler.ResponseHandler<JSONObject>() {
                @Override
                public void onError(String str) {
                    Bundle bundle = new Bundle();
                    bundle.putString(GlobalTracker.EVENT_LOG_VEHICLE_DETAILS, "Error in logging vehicle details: " + vehicleDetails.getRegistrationNo());
                }

                @Override
                public void onResponse(JSONObject jSONObject) {
                    Bundle bundle = new Bundle();
                    bundle.putString(GlobalTracker.EVENT_LOG_VEHICLE_DETAILS, "Success in logging vehicle details: " + vehicleDetails.getRegistrationNo());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
