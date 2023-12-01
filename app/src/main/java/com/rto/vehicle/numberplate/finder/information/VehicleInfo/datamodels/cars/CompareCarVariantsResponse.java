package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars;

import java.io.Serializable;


public class CompareCarVariantsResponse implements Serializable {
    private CarVariantsComparison data;
    private int statusCode;
    private String statusMessage;

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public CarVariantsComparison getData() {
        return this.data;
    }

    public String toString() {
        return "CompareCarVariantsResponse{statusCode=" + this.statusCode + ", statusMessage='" + this.statusMessage + "', data=" + this.data + '}';
    }
}
