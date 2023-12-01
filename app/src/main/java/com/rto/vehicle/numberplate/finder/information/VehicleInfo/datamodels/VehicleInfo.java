package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars.CarModel;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.bikes.BikeBrand;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.bikes.BikeModel;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars.CarBrand;

import java.io.Serializable;


public class VehicleInfo implements Serializable {
    private int brandId;
    private String brandName;
    private String exShowroomPrice;
    private int id;
    private String imageUrl;
    private String modelName;
    private String modelSlug;

    public CarBrand getShortCarBrand() {
        return new CarBrand(this.brandId, this.brandName, "", "");
    }

    public CarModel getCarModel() {
        return new CarModel(this.id, this.brandId, this.brandName, this.modelName, this.modelSlug, this.exShowroomPrice, this.imageUrl);
    }

    public BikeBrand getShortBikeBrand() {
        return new BikeBrand(this.brandId, this.brandName, "", "");
    }

    public BikeModel getBikeModel() {
        return new BikeModel(this.id, this.brandId, this.brandName, this.modelName, this.modelSlug, this.exShowroomPrice, this.imageUrl);
    }

    public int getId() {
        return this.id;
    }

    public int getBrandId() {
        return this.brandId;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public String getModelName() {
        return this.modelName;
    }

    public String getModelSlug() {
        return this.modelSlug;
    }

    public String getExShowroomPrice() {
        return this.exShowroomPrice;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String toString() {
        return "VehicleInfo{id=" + this.id + ", brandId='" + this.brandId + "', brandName='" + this.brandName + "', modelName='" + this.modelName + "', modelSlug='" + this.modelSlug + "', exShowroomPrice='" + this.exShowroomPrice + "', imageUrl='" + this.imageUrl + "'}";
    }
}
