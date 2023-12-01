package com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels;

import java.io.Serializable;


public class SearchLicenseHistory implements Serializable {
    private String dlNo;
    private String dob;
    private int id;
    private String name;
    private int searchOrder;

    public SearchLicenseHistory() {
    }

    public SearchLicenseHistory(int i, String str, String str2, String str3, int i2) {
        this.id = i;
        this.dlNo = str;
        this.dob = str2;
        this.name = str3;
        this.searchOrder = i2;
    }

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

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getSearchOrder() {
        return this.searchOrder;
    }

    public void setSearchOrder(int i) {
        this.searchOrder = i;
    }

    public String toString() {
        return "SearchLicenseHistory{id=" + this.id + ", dlNo='" + this.dlNo + "', dob='" + this.dob + "', name='" + this.name + "', searchOrder=" + this.searchOrder + '}';
    }
}
