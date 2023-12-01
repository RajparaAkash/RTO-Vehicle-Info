package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;


public class CarBikeComparisonHistory implements Serializable {
    private String bikeModelXData;
    private String bikeModelXName;
    private String bikeModelYData;
    private String bikeModelYName;
    private String bikeVariantXData;
    private String bikeVariantXName;
    private String bikeVariantYData;
    private String bikeVariantYName;
    private String carModelXData;
    private String carModelXName;
    private String carModelYData;
    private String carModelYName;
    private String carVariantXData;
    private String carVariantXName;
    private String carVariantYData;
    private String carVariantYName;
    private int id;
    private int searchOrder;
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

    public String getCarModelXName() {
        return this.carModelXName;
    }

    public void setCarModelXName(String str) {
        this.carModelXName = str;
    }

    public String getCarModelYName() {
        return this.carModelYName;
    }

    public void setCarModelYName(String str) {
        this.carModelYName = str;
    }

    public String getCarVariantXName() {
        return this.carVariantXName;
    }

    public void setCarVariantXName(String str) {
        this.carVariantXName = str;
    }

    public String getCarVariantYName() {
        return this.carVariantYName;
    }

    public void setCarVariantYName(String str) {
        this.carVariantYName = str;
    }

    public String getBikeModelXName() {
        return this.bikeModelXName;
    }

    public void setBikeModelXName(String str) {
        this.bikeModelXName = str;
    }

    public String getBikeModelYName() {
        return this.bikeModelYName;
    }

    public void setBikeModelYName(String str) {
        this.bikeModelYName = str;
    }

    public String getBikeVariantXName() {
        return this.bikeVariantXName;
    }

    public void setBikeVariantXName(String str) {
        this.bikeVariantXName = str;
    }

    public String getBikeVariantYName() {
        return this.bikeVariantYName;
    }

    public void setBikeVariantYName(String str) {
        this.bikeVariantYName = str;
    }

    public String getCarModelXData() {
        return this.carModelXData;
    }

    public void setCarModelXData(String str) {
        this.carModelXData = str;
    }

    public String getCarModelYData() {
        return this.carModelYData;
    }

    public void setCarModelYData(String str) {
        this.carModelYData = str;
    }

    public String getCarVariantXData() {
        return this.carVariantXData;
    }

    public void setCarVariantXData(String str) {
        this.carVariantXData = str;
    }

    public String getCarVariantYData() {
        return this.carVariantYData;
    }

    public void setCarVariantYData(String str) {
        this.carVariantYData = str;
    }

    public String getBikeModelXData() {
        return this.bikeModelXData;
    }

    public void setBikeModelXData(String str) {
        this.bikeModelXData = str;
    }

    public String getBikeModelYData() {
        return this.bikeModelYData;
    }

    public void setBikeModelYData(String str) {
        this.bikeModelYData = str;
    }

    public String getBikeVariantXData() {
        return this.bikeVariantXData;
    }

    public void setBikeVariantXData(String str) {
        this.bikeVariantXData = str;
    }

    public String getBikeVariantYData() {
        return this.bikeVariantYData;
    }

    public void setBikeVariantYData(String str) {
        this.bikeVariantYData = str;
    }

    public int getSearchOrder() {
        return this.searchOrder;
    }

    public void setSearchOrder(int i) {
        this.searchOrder = i;
    }

    public String toString() {
        return "CarBikeComparisonHistory{id=" + this.id + ", vehicleType='" + this.vehicleType + "', carModelXName='" + this.carModelXName + "', carModelYName='" + this.carModelYName + "', carVariantXName='" + this.carVariantXName + "', carVariantYName='" + this.carVariantYName + "', bikeModelXName='" + this.bikeModelXName + "', bikeModelYName='" + this.bikeModelYName + "', bikeVariantXName='" + this.bikeVariantXName + "', bikeVariantYName='" + this.bikeVariantYName + "', carModelXData='" + this.carModelXData + "', carModelYData='" + this.carModelYData + "', carVariantXData='" + this.carVariantXData + "', carVariantYData='" + this.carVariantYData + "', bikeModelXData='" + this.bikeModelXData + "', bikeModelYData='" + this.bikeModelYData + "', bikeVariantXData='" + this.bikeVariantXData + "', bikeVariantYData='" + this.bikeVariantYData + "', searchOrder=" + this.searchOrder + '}';
    }
}
