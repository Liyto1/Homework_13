package org.example.FirstTask;

import com.google.gson.Gson;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;;


public class MainClass {
    private static final String uri = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        User user = new User("Tom Johnson", "TJ", "tjOfficeal@gmail.com");
        newUser(user);
        creationTest();
    }

    public static void newUser(User user) throws IOException, InterruptedException, URISyntaxException {

        String newUser = new Gson().toJson(user);
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(uri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(newUser))
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("httpResponse.statusCode() = " + httpResponse.statusCode());

    }

    public static void creationTest() throws IOException, InterruptedException, URISyntaxException {

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
        System.out.println("maxId = " + maxId);
    }
}
