package org.example.FirstTask;

public class Geo {
    private static String lat;
    private static String lng;

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        Geo.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        Geo.lng = lng;
    }
}
