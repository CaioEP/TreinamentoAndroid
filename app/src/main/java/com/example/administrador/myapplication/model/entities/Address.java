package com.example.administrador.myapplication.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address implements Parcelable {
    @JsonProperty("logradouro")
    private String street;
    @JsonProperty("cidade")
    private String city;
    @JsonProperty("estado")
    private String state;
    @JsonProperty("cep")
    private String zipCode;
    @JsonProperty("tipoDeLogradouro")
    private String streetType;
    @JsonProperty("bairro")
    private String neighborhood;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

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
        dest.writeString(zipCode == null ? "" : zipCode);
        dest.writeString(streetType == null ? "" : streetType);
        dest.writeString(neighborhood == null ? "" : neighborhood);
    }

    private void readToParcel(Parcel in) {
        city = in.readString();
        state = in.readString();
        street = in.readString();
        zipCode = in.readString();
        streetType = in.readString();
        neighborhood = in.readString();
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
