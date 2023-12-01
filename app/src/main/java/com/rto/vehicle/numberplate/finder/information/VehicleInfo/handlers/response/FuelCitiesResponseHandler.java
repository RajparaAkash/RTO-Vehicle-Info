package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.response;

import com.google.gson.Gson;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.FuelCityResponse;

import org.json.JSONObject;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;


public class FuelCitiesResponseHandler implements TaskHandler.JsonResponseHandler {
    private final TaskHandler.ResponseHandler<FuelCityResponse> responseHandler;

    public FuelCitiesResponseHandler(TaskHandler.ResponseHandler<FuelCityResponse> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        try {
            this.responseHandler.onResponse(new Gson().fromJson(jSONObject.toString(), FuelCityResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
