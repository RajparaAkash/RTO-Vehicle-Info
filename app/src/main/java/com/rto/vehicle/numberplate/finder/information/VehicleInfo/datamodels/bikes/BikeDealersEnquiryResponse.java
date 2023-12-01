package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.bikes;

import java.io.Serializable;


public class BikeDealersEnquiryResponse implements Serializable {
    private int statusCode;
    private String statusMessage;

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public String toString() {
        return "BikeDealersEnquiryResponse{statusCode=" + this.statusCode + ", statusMessage='" + this.statusMessage + "'}";
    }
}
