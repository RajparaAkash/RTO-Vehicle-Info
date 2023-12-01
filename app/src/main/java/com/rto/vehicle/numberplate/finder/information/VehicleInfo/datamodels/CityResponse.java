package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;
import java.util.List;


public class CityResponse implements Serializable {
    private List<City> data;
    private int statusCode;
    private String statusMessage;

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public List<City> getData() {
        return this.data;
    }

    public String toString() {
        return "CityResponse{statusCode=" + this.statusCode + ", statusMessage='" + this.statusMessage + "', data=" + this.data + '}';
    }
}
