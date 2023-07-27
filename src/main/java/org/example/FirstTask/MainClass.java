package org.example.FirstTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;


public class MainClass {
    private static final String url = "https://jsonplaceholder.typicode.com";


    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
//        System.out.println("***********FirstTask**********");
//        User user = new User("Tom Johnson", "TJ", "tjOfficeal@gmail.com");
//        newUser(user);
//        System.out.println("==========================");
//        User updatedUser = new User(10, "Tom Hanks", "TH", "REAlth@gmail.com");
//        try {
//            upgradeUser(updatedUser);
//        } catch (IOException | InterruptedException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//        System.out.println("==========================");
//        int userIdToDelete = 9;
//        try {
//            deleteUser(userIdToDelete);
//        } catch (IOException | InterruptedException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//        System.out.println("==========================");
//        try {
//            allUsersInfo();
//        } catch (IOException | InterruptedException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//        System.out.println("==========================");
//        int userId = 5;
//        try {
//            infoOnId(userId);
//        } catch (IOException | InterruptedException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//        System.out.println("==========================");
//        String username = "Delphine";
//        try {
//            infoOnUsername(username);
//        } catch (IOException | InterruptedException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//        System.out.println("***********SecondTask**********");
//        int userId = 1;
//
//        try {
//            List<Post> posts = getPostsByUser(userId);
//            if (!posts.isEmpty()) {
////                Post lastPost = posts.get(posts.size() -1);
//                List<Comment> comments = getCommentsForPost(posts.size());
//
//                if (!comments.isEmpty()) {
//                    String fileName = "user-" + userId + "-post-" + posts.size() + "-comments.json";
//                    saveCommentsToJsonFile(comments, fileName);
//                    System.out.println("Comments saved to " + fileName + " successfully.");
//                } else {
//                    System.out.println("No comments found for the last post of user " + userId);
//                }
//            } else {
//                System.out.println("No posts found for user " + userId);
//            }
//        } catch (IOException | URISyntaxException | InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("***********ThirdTask**********");
        int userId = 1; // Замініть це на бажаний id користувача
        try {
            List<Post> posts = getPostsByUser(userId);
            if (!posts.isEmpty()) {
                Post lastPost = posts.get(posts.size() - 1);
                List<Comment> comments = getCommentsForPost(lastPost.getId());

                if (!comments.isEmpty()) {
                    String fileName = "user-" + userId + "-post-" + lastPost.getId() + "-comments.json";
                    saveCommentsToJsonFile(comments, fileName);
                    System.out.println("Comments saved to " + fileName + " successfully.");
                } else {
                    System.out.println("No comments found for the last post of user " + userId);
                }
            } else {
                System.out.println("No posts found for user " + userId);
            }

            List<Todo> openTodos = getOpenTodosForUser(userId);
            if (!openTodos.isEmpty()) {
                System.out.println("Open Todos for user " + userId + ":");
                for (Todo todo : openTodos) {
                    System.out.println("Todo ID: " + todo.getId());
                    System.out.println("Title: " + todo.getTitle());
                    System.out.println("Completed: " + todo.isCompleted());
                    System.out.println("------------------------");
                }
            } else {
                System.out.println("No open Todos found for user " + userId);
            }
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void newUser(User user) throws IOException, InterruptedException, URISyntaxException {

        String newUser = new Gson().toJson(user);
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url + "/users"))
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
        HttpRequest updateRequest = HttpRequest.newBuilder(new URI(url + "/users/" + updatedUser.getId()))
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

        HttpRequest deleteRequest = HttpRequest.newBuilder(new URI(url + "/users/" + userId))
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

        HttpRequest getAllUsers = HttpRequest.newBuilder(new URI(url + "/users"))
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
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url + "/users/" + id))
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

    public static void infoOnUsername(String username) throws IOException, URISyntaxException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url + "/users?username=" + username))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        int responseCode = response.statusCode();
        if (responseCode == 200) {

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
        } else {
            System.err.println("Failed to get user information. Response code: " + responseCode);
        }
    }

    public static List<Post> getPostsByUser(int userId) throws IOException, URISyntaxException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url + "/users/" + userId + "/posts"))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        return gson.fromJson(response.body(), new TypeToken<List<Post>>() {
        }.getType());

    }

    public static List<Comment> getCommentsForPost(int postId) throws IOException, URISyntaxException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url + "/posts/" + postId + "/comments"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        return gson.fromJson(response.body(), new TypeToken<List<Comment>>() {
        }.getType());
    }

    public static void saveCommentsToJsonFile(List<Comment> comments, String fileName) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(comments);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        }
    }


    public static List<Todo> getOpenTodosForUser(int userId) throws IOException, URISyntaxException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();


        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url + "/users/" + userId + "/todos"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        List<Todo> todos = gson.fromJson(response.body(), new TypeToken<List<Todo>>() {
        }.getType());

        // Фільтруємо задачі, у яких completed = false
        return todos.stream()
                .filter(todo -> !todo.isCompleted())
                .collect(Collectors.toList());
    }

    public static class Todo {
        private int id;
        private String title;
        private boolean completed;

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public boolean isCompleted() {
            return completed;
        }
    }
}
