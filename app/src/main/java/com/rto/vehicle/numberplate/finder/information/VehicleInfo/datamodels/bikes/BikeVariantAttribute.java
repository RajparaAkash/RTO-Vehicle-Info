package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.bikes;

import java.io.Serializable;


public class BikeVariantAttribute implements Serializable {
    private String attrName;
    private String attrValue;

    public String getAttrName() {
        return this.attrName;
    }

    public String getAttrValue() {
        return this.attrValue;
    }

    public String toString() {
        return "BikeVariantAttribute{attrName='" + this.attrName + "', attrValue='" + this.attrValue + "'}";
    }
}
