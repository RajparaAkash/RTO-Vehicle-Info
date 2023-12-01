package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.bikes;

import java.io.Serializable;
import java.util.List;


public class BikeVariantAttributes implements Serializable {
    private List<BikeVariantAttribute> items;
    private String key;

    public String getKey() {
        return this.key;
    }

    public List<BikeVariantAttribute> getItems() {
        return this.items;
    }

    public String toString() {
        return "BikeVariantAttributes{key='" + this.key + "', items=" + this.items + '}';
    }
}
