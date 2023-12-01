package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;


public class City implements Serializable {
    private String cityName;
    private int id;
    private int stateId;
    private String stateName;

    public int getId() {
        return this.id;
    }

    public int getStateId() {
        return this.stateId;
    }

    public String getStateName() {
        return this.stateName;
    }

    public String getCityName() {
        return this.cityName;
    }

    public String toString() {
        return "City{id=" + this.id + ", stateId=" + this.stateId + ", stateName='" + this.stateName + "', cityName='" + this.cityName + "'}";
    }
}
