package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.response;

import com.google.gson.Gson;

import org.json.JSONObject;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.bikes.BikeVariantDetailsResponse;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;

public class BikeVariantDetailsResponseHandler implements TaskHandler.JsonResponseHandler {
    private final TaskHandler.ResponseHandler<BikeVariantDetailsResponse> responseHandler;

    public BikeVariantDetailsResponseHandler(TaskHandler.ResponseHandler<BikeVariantDetailsResponse> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        try {
            this.responseHandler.onResponse(new Gson().fromJson(jSONObject.toString(), BikeVariantDetailsResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
