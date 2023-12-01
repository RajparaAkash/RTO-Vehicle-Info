package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars;

import java.io.Serializable;


public class CarBrand implements Serializable {
    private String brandName;
    private String brandSlug;
    private int id;
    private String imageUrl;

    public CarBrand() {
    }

    public CarBrand(int i, String str, String str2, String str3) {
        this.id = i;
        this.brandName = str;
        this.brandSlug = str2;
        this.imageUrl = str3;
    }

    public int getId() {
        return this.id;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public String getBrandSlug() {
        return this.brandSlug;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String toString() {
        return "CarBrand{id=" + this.id + ", brandName='" + this.brandName + "', brandSlug='" + this.brandSlug + "', imageUrl='" + this.imageUrl + "'}";
    }
}
