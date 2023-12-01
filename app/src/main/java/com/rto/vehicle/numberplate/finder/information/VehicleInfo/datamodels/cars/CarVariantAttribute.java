package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars;

import java.io.Serializable;


public class CarVariantAttribute implements Serializable {
    private String attrName;
    private String attrValue;

    public String getAttrName() {
        return this.attrName;
    }

    public String getAttrValue() {
        return this.attrValue;
    }

    public String toString() {
        return "CarVariantAttribute{attrName='" + this.attrName + "', attrValue='" + this.attrValue + "'}";
    }
}
