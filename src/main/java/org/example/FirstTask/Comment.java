package org.example.FirstTask;

import com.google.gson.annotations.SerializedName;

public class Comment {
    private int id;
    @SerializedName("name")
    private String author;
    private String email;
    @SerializedName("body")
    private String text;

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
