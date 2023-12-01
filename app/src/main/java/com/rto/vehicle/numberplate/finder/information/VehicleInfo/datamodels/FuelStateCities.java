package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;
import java.util.List;


public class FuelStateCities implements Serializable {
    private final List<FuelCity> cityList;
    private final String name;

    public FuelStateCities(String str, List<FuelCity> list) {
        this.name = str;
        this.cityList = list;
    }

    public String getName() {
        return this.name;
    }

    public List<FuelCity> getCityList() {
        return this.cityList;
    }

    public String toString() {
        return "FuelStateCities{name='" + this.name + "', cityList=" + this.cityList + '}';
    }
}
