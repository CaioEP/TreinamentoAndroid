package com.example.administrador.myapplication.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {
    private String street;
    private String city;
    private String state;

    public Address(){
        super();
    }

    public Address(Parcel in){
        super();
        readToParcel(in);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city == null ? "" : city);
        dest.writeString(state == null ? "" : state);
        dest.writeString(street == null ? "" : street);
    }

    private void readToParcel(Parcel in) {
        city = in.readString();
        state = in.readString();
        street = in.readString();
    }

    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>(){

        @Override
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
