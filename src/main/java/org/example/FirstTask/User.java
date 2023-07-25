package org.example.FirstTask;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;


    public User(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public User(int id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }


    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public Address getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public Company getCompany() {
        return company;
    }
    public static class Address {
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

    public static class Geo {
        private String lat;
        private String lng;

        public String getLat() {
            return lat;
        }

        public String getLng() {
            return lng;
        }
    }

    public static class Company {
        private String name;
        @SerializedName("catchPhrase")
        private String catchPhrase;
        private String bs;

        public String getName() {
            return name;
        }

        public String getCatchPhrase() {
            return catchPhrase;
        }

        public String getBs() {
            return bs;
        }
    }
}

