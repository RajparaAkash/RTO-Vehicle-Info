package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.cars.CarModel;
import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.bikes.BikeModel;

import java.io.Serializable;
import java.util.List;


public class PopularCarBike implements Serializable {
    private List<BikeModel> bikes;
    private List<CarModel> cars;

    public List<CarModel> getCars() {
        return this.cars;
    }

    public List<BikeModel> getBikes() {
        return this.bikes;
    }

    public String toString() {
        return "PopularCarBike{cars=" + this.cars + ", bikes=" + this.bikes + '}';
    }
}
