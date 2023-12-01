package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.response;

import com.google.gson.Gson;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars.CarVariantsResponse;

import org.json.JSONObject;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;


public class CarVariantsResponseHandler implements TaskHandler.JsonResponseHandler {
    private final TaskHandler.ResponseHandler<CarVariantsResponse> responseHandler;

    public CarVariantsResponseHandler(TaskHandler.ResponseHandler<CarVariantsResponse> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        try {
            this.responseHandler.onResponse(new Gson().fromJson(jSONObject.toString(), CarVariantsResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
