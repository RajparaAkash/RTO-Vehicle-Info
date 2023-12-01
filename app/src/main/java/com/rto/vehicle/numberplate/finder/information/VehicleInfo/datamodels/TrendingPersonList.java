package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;
import java.util.ArrayList;


public class TrendingPersonList implements Serializable {
    private ArrayList<TrendingPerson> data;
    private String description;
    private String tag;

    public String getTag() {
        return this.tag;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<TrendingPerson> getData() {
        return this.data;
    }

    public String toString() {
        return "TrendingPersonList{tag='" + this.tag + "', description='" + this.description + "', data=" + this.data + '}';
    }
}
