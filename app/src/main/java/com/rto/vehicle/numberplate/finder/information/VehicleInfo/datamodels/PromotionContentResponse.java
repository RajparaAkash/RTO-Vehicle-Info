package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;
import java.util.List;


public class PromotionContentResponse implements Serializable {
    private List<PromotionContent> promotions;

    public List<PromotionContent> getPromotions() {
        return this.promotions;
    }

    public String toString() {
        return "PromotionContentResponse{promotions=" + this.promotions + '}';
    }
}
