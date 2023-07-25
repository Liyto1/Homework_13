package org.example.FirstTask;

public class Address {
    private static String street;
    private static String suite;
    private static String city;
    private static String zipcode;

    public Address(String street, String suite, String city, String zipcode){
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        Address.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        Address.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        Address.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        Address.zipcode = zipcode;
    }
}
