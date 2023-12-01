package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class RTODetail implements Serializable {
    private final String address;
    private final String district;
    private final int id;
    private final String phone;
    @SerializedName("rto_code")
    private final String rtoCode;
    private final String state;
    private final String website;

    public RTODetail(int i, String str, String str2, String str3, String str4, String str5, String str6) {
        this.id = i;
        this.rtoCode = str;
        this.district = str2;
        this.state = str3;
        this.address = str4;
        this.phone = str5;
        this.website = str6;
    }

    public int getId() {
        return this.id;
    }

    public String getRtoCode() {
        return this.rtoCode;
    }

    public String getDistrict() {
        return this.district;
    }

    public String getState() {
        return this.state;
    }

    public String getAddress() {
        return this.address;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getWebsite() {
        return this.website;
    }

    public String toString() {
        return "RTODetail{id=" + this.id + ", rtoCode='" + this.rtoCode + "', district='" + this.district + "', state='" + this.state + "', address='" + this.address + "', phone='" + this.phone + "', website='" + this.website + "'}";
    }
}
