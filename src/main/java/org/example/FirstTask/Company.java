package org.example.FirstTask;

import com.google.gson.annotations.SerializedName;

public class Company {
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
