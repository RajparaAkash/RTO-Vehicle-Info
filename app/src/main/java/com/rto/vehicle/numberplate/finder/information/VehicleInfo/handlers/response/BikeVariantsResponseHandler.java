package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.response;

import com.google.gson.Gson;

import org.json.JSONObject;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.bikes.BikeVariantsResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;


public class BikeVariantsResponseHandler implements TaskHandler.JsonResponseHandler {
    private final TaskHandler.ResponseHandler<BikeVariantsResponse> responseHandler;

    public BikeVariantsResponseHandler(TaskHandler.ResponseHandler<BikeVariantsResponse> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        try {
            this.responseHandler.onResponse(new Gson().fromJson(jSONObject.toString(), BikeVariantsResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
