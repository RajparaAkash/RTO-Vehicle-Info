package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.response;

import com.google.gson.Gson;

import org.json.JSONObject;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars.CarServiceCentersResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;


public class CarServiceCentersResponseHandler implements TaskHandler.JsonResponseHandler {
    private final TaskHandler.ResponseHandler<CarServiceCentersResponse> responseHandler;

    public CarServiceCentersResponseHandler(TaskHandler.ResponseHandler<CarServiceCentersResponse> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        try {
            this.responseHandler.onResponse(new Gson().fromJson(jSONObject.toString(), CarServiceCentersResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
