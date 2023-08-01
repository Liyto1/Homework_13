package org.example.FirstTask;

import com.google.gson.annotations.SerializedName;

public class Address {
    private String street;
    private String suite;
    private String city;
    @SerializedName("zipcode")
    private String zipCode;
    private Geo geo;

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Geo getGeo() {
        return geo;
    }
}
