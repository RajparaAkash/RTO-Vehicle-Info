package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.response;

import com.google.gson.Gson;

import org.json.JSONObject;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars.CarBrandsResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;


public class CarBrandsResponseHandler implements TaskHandler.JsonResponseHandler {
    private final TaskHandler.ResponseHandler<CarBrandsResponse> responseHandler;

    public CarBrandsResponseHandler(TaskHandler.ResponseHandler<CarBrandsResponse> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        try {
            this.responseHandler.onResponse(new Gson().fromJson(jSONObject.toString(), CarBrandsResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
