package com.example.testing.model;

public class ContactImpl implements Contact {

    private String contactName;
    private String contactNumber;
    private String contactImage;

    @Override
    public String getContactImage() {
        return contactImage;
    }

    @Override
    public void setContactImage(String contactImage) {
        this.contactImage = contactImage;
    }

    @Override
    public String getContactName() {
        return contactName;
    }

    @Override
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
