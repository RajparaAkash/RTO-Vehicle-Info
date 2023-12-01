package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;
import java.util.List;


public class RTOInformation implements Serializable {
    private final String name;
    private final List<RTODetail> rtoList;

    public RTOInformation(String str, List<RTODetail> list) {
        this.name = str;
        this.rtoList = list;
    }

    public String getName() {
        return this.name;
    }

    public List<RTODetail> getRtoList() {
        return this.rtoList;
    }

    public String toString() {
        return "RTOInformation{name='" + this.name + "', rtoList=" + this.rtoList + '}';
    }
}
