package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.response;

import com.google.gson.Gson;

import org.json.JSONObject;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.bikes.BikeServiceCentersResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;


public class BikeServiceCentersResponseHandler implements TaskHandler.JsonResponseHandler {

    private final TaskHandler.ResponseHandler<BikeServiceCentersResponse> responseHandler;

    public BikeServiceCentersResponseHandler(TaskHandler.ResponseHandler<BikeServiceCentersResponse> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        try {
            this.responseHandler.onResponse(new Gson().fromJson(jSONObject.toString(), BikeServiceCentersResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
