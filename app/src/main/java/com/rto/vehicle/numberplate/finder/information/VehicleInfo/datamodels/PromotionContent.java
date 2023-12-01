package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;


public class PromotionContent implements Serializable {
    private String promoImage;
    private String promoName;
    private String promoUrl;

    public String getPromoName() {
        return this.promoName;
    }

    public String getPromoImage() {
        return this.promoImage;
    }

    public String getPromoUrl() {
        return this.promoUrl;
    }

    public String toString() {
        return "PromotionContent{promoName='" + this.promoName + "', promoImage='" + this.promoImage + "', promoUrl='" + this.promoUrl + "'}";
    }
}
