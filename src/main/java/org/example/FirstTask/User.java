package org.example.FirstTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class User {
    private static final String uri = "https://jsonplaceholder.typicode.com/users";
    private int id;
    private String name;
    private String username;
    private String email;

    public User(String name, String username, String email) throws IOException, URISyntaxException, InterruptedException {
        this.id = allIds() + 1;
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
    public static int  allIds() throws IOException, InterruptedException, URISyntaxException {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest getUsersRequest = HttpRequest.newBuilder(new URI(uri))
                .GET()
                .build();
        HttpResponse<String> usersResponse = httpClient.send(getUsersRequest, HttpResponse.BodyHandlers.ofString());


        User[] users = new Gson().fromJson(usersResponse.body(), User[].class);
        int maxId = 0;
        for (User existingUser : users) {
            if (existingUser.getId() > maxId) {
                maxId = existingUser.getId();
            }
        }
        return maxId;
    }
}

