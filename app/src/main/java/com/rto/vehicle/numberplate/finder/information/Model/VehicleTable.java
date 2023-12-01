package com.rto.vehicle.numberplate.finder.information.Model;

import android.os.Parcel;
import android.os.Parcelable;


public class VehicleTable implements Parcelable {
    public static final Creator<VehicleTable> CREATOR = new C03701();
    String chassisNo;
    String engineNo;
    String fitUpto;
    String fuelNorms;
    String fuelType;
    String insuranceUpto;
    String makerName;
    String ownerName;
    String regDate;
    String regNo;
    String rtoName;
    String vehicleClass;
    private int id;

    public VehicleTable(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        this.id = i;
        this.regNo = str;
        this.chassisNo = str2;
        this.engineNo = str3;
        this.fitUpto = str4;
        this.fuelType = str5;
        this.insuranceUpto = str6;
        this.makerName = str7;
        this.ownerName = str8;
        this.regDate = str9;
        this.rtoName = str10;
        this.vehicleClass = str11;
        this.fuelNorms = str12;
    }


    public VehicleTable(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        this.regNo = str;
        this.chassisNo = str2;
        this.engineNo = str3;
        this.fitUpto = str4;
        this.fuelType = str5;
        this.insuranceUpto = str6;
        this.makerName = str7;
        this.ownerName = str8;
        this.regDate = str9;
        this.rtoName = str10;
        this.vehicleClass = str11;
        this.fuelNorms = str12;
    }

    protected VehicleTable(Parcel parcel) {
        this.regNo = parcel.readString();
        this.chassisNo = parcel.readString();
        this.engineNo = parcel.readString();
        this.fitUpto = parcel.readString();
        this.fuelType = parcel.readString();
        this.insuranceUpto = parcel.readString();
        this.makerName = parcel.readString();
        this.ownerName = parcel.readString();
        this.regDate = parcel.readString();
        this.rtoName = parcel.readString();
        this.vehicleClass = parcel.readString();
        this.fuelNorms = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getRegNo() {
        return this.regNo;
    }

    public void setRegNo(String str) {
        this.regNo = str;
    }

    public String getChassisNo() {
        return this.chassisNo;
    }

    public void setChassisNo(String str) {
        this.chassisNo = str;
    }

    public String getEngineNo() {
        return this.engineNo;
    }

    public void setEngineNo(String str) {
        this.engineNo = str;
    }

    public String getFitUpto() {
        return this.fitUpto;
    }

    public void setFitUpto(String str) {
        this.fitUpto = str;
    }

    public String getFuelType() {
        return this.fuelType;
    }

    public void setFuelType(String str) {
        this.fuelType = str;
    }

    public String getInsuranceUpto() {
        return this.insuranceUpto;
    }

    public void setInsuranceUpto(String str) {
        this.insuranceUpto = str;
    }

    public String getMakerName() {
        return this.makerName;
    }

    public void setMakerName(String str) {
        this.makerName = str;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public void setOwnerName(String str) {
        this.ownerName = str;
    }

    public String getRegDate() {
        return this.regDate;
    }

    public void setRegDate(String str) {
        this.regDate = str;
    }

    public String getRtoName() {
        return this.rtoName;
    }

    public void setRtoName(String str) {
        this.rtoName = str;
    }

    public String getVehicleClass() {
        return this.vehicleClass;
    }

    public void setVehicleClass(String str) {
        this.vehicleClass = str;
    }

    public String getFuelNorms() {
        return this.fuelNorms;
    }

    public void setFuelNorms(String str) {
        this.fuelNorms = str;
    }

    public String toString() {
        return "VehicleTable{regNo='" + this.regNo + "', chassisNo='" + this.chassisNo + "', engineNo='" + this.engineNo + "', fitUpto='" + this.fitUpto + "', fuelType='" + this.fuelType + "', insuranceUpto='" + this.insuranceUpto + "', makerName='" + this.makerName + "', ownerName='" + this.ownerName + "', regDate='" + this.regDate + "', rtoName='" + this.rtoName + "', vehicleClass='" + this.vehicleClass + "', fuelNorms='" + this.fuelNorms + "'}";
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.regNo);
        parcel.writeString(this.chassisNo);
        parcel.writeString(this.engineNo);
        parcel.writeString(this.fitUpto);
        parcel.writeString(this.fuelType);
        parcel.writeString(this.insuranceUpto);
        parcel.writeString(this.makerName);
        parcel.writeString(this.ownerName);
        parcel.writeString(this.regDate);
        parcel.writeString(this.rtoName);
        parcel.writeString(this.vehicleClass);
        parcel.writeString(this.fuelNorms);
    }

    static class C03701 implements Creator<VehicleTable> {

        @Override
        public VehicleTable createFromParcel(Parcel parcel) {
            return new VehicleTable(parcel);
        }

        @Override
        public VehicleTable[] newArray(int i) {
            return new VehicleTable[i];
        }
    }
}
