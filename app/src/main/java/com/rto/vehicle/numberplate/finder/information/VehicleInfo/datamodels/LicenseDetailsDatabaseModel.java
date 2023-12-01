package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;


public class LicenseDetailsDatabaseModel implements Serializable {
    private String data;
    private String dlNo;
    private String dob;
    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getDlNo() {
        return this.dlNo;
    }

    public void setDlNo(String str) {
        this.dlNo = str;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String str) {
        this.dob = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }

    public String toString() {
        return "LicenseDetailsDatabaseModel{id=" + this.id + ", dlNo='" + this.dlNo + "', dob='" + this.dob + "', data='" + this.data + "'}";
    }
}
