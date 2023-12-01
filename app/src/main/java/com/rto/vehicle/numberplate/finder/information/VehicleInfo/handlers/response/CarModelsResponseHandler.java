package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.response;

import com.google.gson.Gson;

import org.json.JSONObject;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars.CarModelsResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;


public class CarModelsResponseHandler implements TaskHandler.JsonResponseHandler {
    private final TaskHandler.ResponseHandler<CarModelsResponse> responseHandler;

    public CarModelsResponseHandler(TaskHandler.ResponseHandler<CarModelsResponse> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        try {
            this.responseHandler.onResponse(new Gson().fromJson(jSONObject.toString(), CarModelsResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
