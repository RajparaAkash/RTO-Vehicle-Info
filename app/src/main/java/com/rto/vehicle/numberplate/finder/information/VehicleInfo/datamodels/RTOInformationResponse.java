package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;
import java.util.List;


public class RTOInformationResponse implements Serializable {
    private final List<RTOInformation> details;
    private final int statusCode;
    private final String statusMessage;

    public RTOInformationResponse(int i, String str, List<RTOInformation> list) {
        this.statusCode = i;
        this.statusMessage = str;
        this.details = list;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public List<RTOInformation> getDetails() {
        return this.details;
    }

    public String toString() {
        return "RTOInformationResponse{statusCode=" + this.statusCode + ", statusMessage='" + this.statusMessage + "', details=" + this.details + '}';
    }
}
