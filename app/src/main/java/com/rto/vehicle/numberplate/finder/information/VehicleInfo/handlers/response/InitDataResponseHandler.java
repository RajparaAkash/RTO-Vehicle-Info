package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.response;

import com.google.gson.Gson;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.InitDataResponse;

import org.json.JSONObject;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;


public class InitDataResponseHandler implements TaskHandler.JsonResponseHandler {
    TaskHandler.ResponseHandler<InitDataResponse> responseHandler;

    public InitDataResponseHandler(TaskHandler.ResponseHandler<InitDataResponse> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(JSONObject jSONObject) {
        try {
            this.responseHandler.onResponse(new Gson().fromJson(jSONObject.toString(), InitDataResponse.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
