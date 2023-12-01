package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.response;

import org.json.JSONObject;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;


public class LicenseDetailsResponseHandler implements TaskHandler.JsonResponseHandler {
    private final TaskHandler.ResponseHandler<JSONObject> responseHandler;

    public LicenseDetailsResponseHandler(TaskHandler.ResponseHandler<JSONObject> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        try {
            this.responseHandler.onResponse(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
