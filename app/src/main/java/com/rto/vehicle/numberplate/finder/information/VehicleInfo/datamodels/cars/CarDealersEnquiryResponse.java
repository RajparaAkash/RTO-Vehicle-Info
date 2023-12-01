package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars;

import java.io.Serializable;


public class CarDealersEnquiryResponse implements Serializable {
    private int statusCode;
    private String statusMessage;

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public String toString() {
        return "CarDealersEnquiryResponse{statusCode=" + this.statusCode + ", statusMessage='" + this.statusMessage + "'}";
    }
}
