package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.bikes;

import java.io.Serializable;
import java.util.List;


public class CompareBikeVariantAttributes implements Serializable {
    private List<CompareBikeVariantAttribute> items;
    private String key;

    public String getKey() {
        return this.key;
    }

    public List<CompareBikeVariantAttribute> getItems() {
        return this.items;
    }

    public String toString() {
        return "CompareBikeVariantAttributes{key='" + this.key + "', items=" + this.items + '}';
    }
}
