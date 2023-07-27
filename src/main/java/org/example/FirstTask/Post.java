package org.example.FirstTask;

import com.google.gson.annotations.SerializedName;

public class Post {

    private static int userId;
    private static int id;
    private static String title;
    @SerializedName("body")
    private String text;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

     public String getText() {
        return text;
    }

    public int getUserId() {
        return userId;
    }
}
