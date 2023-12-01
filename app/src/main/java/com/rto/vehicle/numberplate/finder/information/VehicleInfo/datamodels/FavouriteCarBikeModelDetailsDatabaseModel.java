package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;


public class FavouriteCarBikeModelDetailsDatabaseModel implements Serializable {
    private String data;
    private int id;
    private int modelId;
    private String vehicleType;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(String str) {
        this.vehicleType = str;
    }

    public int getModelId() {
        return this.modelId;
    }

    public void setModelId(int i) {
        this.modelId = i;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public String toString() {
        return "FavouriteCarBikeModelDetailsDatabaseModel{id=" + this.id + ", vehicleType='" + this.vehicleType + "', modelId=" + this.modelId + ", data='" + this.data + "'}";
    }
}
