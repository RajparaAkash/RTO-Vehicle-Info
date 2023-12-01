package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.response;

import com.google.gson.Gson;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.RTOInformationResponse;

import org.json.JSONObject;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;


public class RTOInformationResponseHandler implements TaskHandler.JsonResponseHandler {
    private final TaskHandler.ResponseHandler<RTOInformationResponse> responseHandler;

    public RTOInformationResponseHandler(TaskHandler.ResponseHandler<RTOInformationResponse> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        try {
            this.responseHandler.onResponse(new Gson().fromJson(jSONObject.toString(), RTOInformationResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
