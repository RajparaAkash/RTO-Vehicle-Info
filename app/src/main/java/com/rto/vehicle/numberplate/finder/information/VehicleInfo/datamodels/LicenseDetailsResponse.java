package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;


public class LicenseDetailsResponse implements Serializable {
    private final LicenseDetails details;
    private final int statusCode;
    private final String statusMessage;

    public LicenseDetailsResponse(int i, String str, LicenseDetails licenseDetails) {
        this.statusCode = i;
        this.statusMessage = str;
        this.details = licenseDetails;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public LicenseDetails getDetails() {
        return this.details;
    }

    public String toString() {
        return "LicenseDetails{statusCode=" + this.statusCode + ", statusMessage='" + this.statusMessage + "', details=" + this.details + '}';
    }
}
