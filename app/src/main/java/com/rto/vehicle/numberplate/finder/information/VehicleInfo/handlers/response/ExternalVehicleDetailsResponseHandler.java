package com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.response;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.handlers.TaskHandler;


public class ExternalVehicleDetailsResponseHandler implements TaskHandler.ResponseHandler<String> {
    private final TaskHandler.ResponseHandler<String> responseHandler;

    public ExternalVehicleDetailsResponseHandler(TaskHandler.ResponseHandler<String> responseHandler) {
        this.responseHandler = responseHandler;
    }

    @Override
    public void onError(String str) {
        this.responseHandler.onError(str);
    }

    @Override
    public void onResponse(String str) {
        try {
            this.responseHandler.onResponse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
