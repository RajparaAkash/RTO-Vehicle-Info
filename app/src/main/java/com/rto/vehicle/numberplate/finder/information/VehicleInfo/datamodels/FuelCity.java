package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;


public class FuelCity implements Serializable {
    private String cityName;
    private int fuelCityId;
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

    public int getFuelCityId() {
        return this.fuelCityId;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FuelCity) {
            FuelCity fuelCity = (FuelCity) obj;
            if (getId() == fuelCity.getId() && getStateId() == fuelCity.getStateId()) {
                return getCityName().equals(fuelCity.getCityName());
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return (((getId() * 31) + getStateId()) * 31) + getCityName().hashCode();
    }

    public String toString() {
        return "FuelCity{id=" + this.id + ", stateId=" + this.stateId + ", stateName='" + this.stateName + "', cityName='" + this.cityName + "', fuelCityName='" + this.fuelCityId + "'}";
    }
}
