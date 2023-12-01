package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;


public class VehicleDetailsResponse implements Serializable {
    private final VehicleDetails details;
    private boolean extra;
    private int statusCode;
    private final String statusMessage;

    public VehicleDetailsResponse(int i, String str, VehicleDetails vehicleDetails) {
        this.statusCode = i;
        this.statusMessage = str;
        this.details = vehicleDetails;
    }

    public VehicleDetailsResponse(int i, String str, VehicleDetails vehicleDetails, boolean z) {
        this.statusCode = i;
        this.statusMessage = str;
        this.details = vehicleDetails;
        this.extra = z;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public VehicleDetails getDetails() {
        return this.details;
    }

    public boolean isExtra() {
        return this.extra;
    }

    public String toString() {
        return "VehicleDetails{statusCode=" + this.statusCode + ", statusMessage='" + this.statusMessage + "', details=" + this.details + ", extra=" + this.extra + '}';
    }
}
