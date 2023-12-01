package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.shopping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Category implements Serializable {
    private String icon;
    private int id;
    private String name;
    private final List<Store> stores = new ArrayList();

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getIcon() {
        return this.icon;
    }

    public List<Store> getStores() {
        return this.stores;
    }
}
