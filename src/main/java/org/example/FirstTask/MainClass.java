package org.example.FirstTask;

import com.google.gson.Gson;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class MainClass {
    private static final String url = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        User user = new User("Tom Johnson", "TJ", "tjOfficeal@gmail.com");
        newUser(user);
        System.out.println("==========================");
        User updatedUser = new User(10, "Tom Hanks", "TH", "REAlth@gmail.com");
        try {
            upgradeUser(updatedUser);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("==========================");
        int userIdToDelete = 9;
        try {
            deleteUser(userIdToDelete);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("==========================");
        try {
            allUsersInfo();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("==========================");
        int userId = 5;
        try {
            infoOnId(userId);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("==========================");
        String username = "Delphine";
        try {
            infoOnUsername(username);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println("==========================");

    }

    public static void newUser(User user) throws IOException, InterruptedException, URISyntaxException {

        String newUser = new Gson().toJson(user);
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(newUser))
                .build();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("httpResponse.statusCode() = " + httpResponse.statusCode());

    }

    public static void upgradeUser(User updatedUser) throws IOException, URISyntaxException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();


        String updatedUserJson = new Gson().toJson(updatedUser);
        HttpRequest updateRequest = HttpRequest.newBuilder(new URI(url + "/" + updatedUser.getId()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(updatedUserJson))
                .build();
        HttpResponse<String> updateResponse = httpClient.send(updateRequest, HttpResponse.BodyHandlers.ofString());


        int responseCode = updateResponse.statusCode();
        if (responseCode == 200 || responseCode == 201) {
            System.out.println("User updated successfully. Response code: " + responseCode);
            System.out.println("Updated User JSON response: " + updateResponse.body());
        } else {
            System.err.println("Failed to update user. Response code: " + responseCode);
            System.err.println("Response body: " + updateResponse.body());
        }
    }

    public static void deleteUser(int userId) throws IOException, URISyntaxException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest deleteRequest = HttpRequest.newBuilder(new URI(url + "/" + userId))
                .DELETE()
                .build();
        HttpResponse<Void> deleteResponse = httpClient.send(deleteRequest, HttpResponse.BodyHandlers.discarding());

        int responseCode = deleteResponse.statusCode();
        if (responseCode >= 200 && responseCode < 300) {
            System.out.println("User with ID " + userId + " successfully deleted. Response code: " + responseCode);
        } else {
            System.err.println("Failed to delete user with ID " + userId + ". Response code: " + responseCode);
        }
    }

    public static void allUsersInfo() throws IOException, URISyntaxException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest getAllUsers = HttpRequest.newBuilder(new URI(url))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(getAllUsers, HttpResponse.BodyHandlers.ofString());


        int responseCode = response.statusCode();
        if (responseCode == 200) {

            Gson gson = new Gson();
            User[] users = gson.fromJson(response.body(), User[].class);


            for (User user : users) {
                System.out.println("User ID: " + user.getId());
                System.out.println("Name: " + user.getName());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());

                User.Address address = user.getAddress();
                System.out.println("Street: " + address.getStreet());
                System.out.println("Suite: " + address.getSuite());
                System.out.println("City: " + address.getCity());
                System.out.println("Zip Code: " + address.getZipCode());

                User.Geo geo = address.getGeo();
                System.out.println("Latitude: " + geo.getLat());
                System.out.println("Longitude: " + geo.getLng());

                System.out.println("Phone: " + user.getPhone());
                System.out.println("Website: " + user.getWebsite());
                User.Company company = user.getCompany();
                System.out.println("Company Name: " + company.getName());
                System.out.println("Catch Phrase: " + company.getCatchPhrase());
                System.out.println("Business: " + company.getBs());

                System.out.println("------------------------");
            }
        } else {
            System.err.println("Failed to get users information. Response code: " + responseCode);
        }

    }

    public static void infoOnId(int id) throws IOException, URISyntaxException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url + "/" + id))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        int responseCode = response.statusCode();
        if (responseCode == 200) {

            Gson gson = new Gson();
            User user = gson.fromJson(response.body(), User.class);


            System.out.println("User ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Email: " + user.getEmail());
        } else {
            System.err.println("Failed to get user information. Response code: " + responseCode);
        }

    }

    public static  void infoOnUsername(String username) throws  IOException, URISyntaxException, InterruptedException{
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url + "?username=" + username))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest,HttpResponse.BodyHandlers.ofString());

        int responseCode = response.statusCode();
        if(responseCode == 200){

            Gson gson = new Gson();
            User[] users = gson.fromJson(response.body(), User[].class);

            if (users.length > 0) {

                User user = users[0];
                System.out.println("User ID: " + user.getId());
                System.out.println("Name: " + user.getName());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
            } else {
                System.err.println("User with username \"" + username + "\" not found.");
            }
        }else{
            System.err.println("Failed to get user information. Response code: " + responseCode);
        }
    }
}