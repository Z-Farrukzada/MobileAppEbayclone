package com.zaurfarrukhzada.myebayclone;

public class AddAddressModel {

    private String fullName;
    private String pinCode;
    private String address;
    private Boolean Selected;

    public AddAddressModel(String fullName, String pinCode, String address, Boolean selected) {
        this.fullName = fullName;
        this.pinCode = pinCode;
        this.address = address;
        Selected = selected;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getSelected() {
        return Selected;
    }

    public void setSelected(Boolean selected) {
        Selected = selected;
    }
}
