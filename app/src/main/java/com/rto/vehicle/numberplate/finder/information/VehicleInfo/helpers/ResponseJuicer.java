package com.rto.vehicle.numberplate.finder.information.VehicleInfo.helpers;

import com.google.gson.Gson;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.ExternalVehicleDetailsResponse;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.GlobalReferenceEngine;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.utils.Utils;


public class ResponseJuicer {
    public static ExternalVehicleDetailsResponse responseJuice(String str) {
        if (Utils.isNullOrEmpty(str)) {
            return null;
        }
        try {
            String decrypt = EncryptionHandler.decrypt(str, GlobalReferenceEngine.dataAccessKey);
            if (Utils.isNullOrEmpty(decrypt)) {
                return null;
            }
            return new Gson().fromJson(decrypt, ExternalVehicleDetailsResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
