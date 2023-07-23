package org.example.FirstTask;

public class User {

    private int id;
    private String name;
    private String username;
    private String email;

    public User(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }
    public User(int id, String name, String username, String email){
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }
}

