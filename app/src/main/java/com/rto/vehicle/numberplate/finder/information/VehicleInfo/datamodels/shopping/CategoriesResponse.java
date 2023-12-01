package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.shopping;

import java.io.Serializable;
import java.util.List;


public class CategoriesResponse implements Serializable {
    private List<Category> categories;

    public List<Category> getCategories() {
        return this.categories;
    }
}
