package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars;

import java.io.Serializable;


public class CarColor implements Serializable {
    private String backgroundName;
    private String colorName;
    private int id;

    public int getId() {
        return this.id;
    }

    public String getColorName() {
        return this.colorName;
    }

    public String getBackgroundName() {
        return this.backgroundName;
    }

    public String toString() {
        return "CarColor{id=" + this.id + ", colorName='" + this.colorName + "', backgroundName='" + this.backgroundName + "'}";
    }
}
