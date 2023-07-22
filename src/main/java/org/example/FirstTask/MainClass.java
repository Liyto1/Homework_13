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
//        HttpClient httpClient = HttpClient.newBuilder()
//                .version(HttpClient.Version.HTTP_1_1)
//                .build();
//
//        HttpRequest getUsersRequest = HttpRequest.newBuilder(new URI(uri))
//                .GET()
//                .build();
//        HttpResponse<String> usersResponse = httpClient.send(getUsersRequest, HttpResponse.BodyHandlers.ofString());
//
//        User[] users = new Gson().fromJson(usersResponse.body(), User[].class);
//        int maxId = 0;
//        for (User existingUser : users) {
//            if (existingUser.getId() > maxId) {
//                maxId = existingUser.getId();
//            }
//        }
//
//        int newUserId = maxId + 1;
//        user.setId(newUserId);
//
//        String newUserJson = new Gson().toJson(user);
//        HttpRequest createNewUserRequest = HttpRequest.newBuilder(new URI(uri))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(newUserJson))
//                .build();
//        HttpResponse<String> createUserResponse = httpClient.send(createNewUserRequest, HttpResponse.BodyHandlers.ofString());
//
//        System.out.println("httpResponse.statusCode() = " + createUserResponse.statusCode());

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





//        URL url = new URL(uri);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setDoOutput(true);
//        OutputStream os = connection.getOutputStream();
//        os.write(Files.readAllBytes(new File("C:\\Users\\Lev\\Desktop\\user.json").toPath()));
//        os.flush();
//        os.close();
//
//        int responseCode = connection.getResponseCode();
//        System.out.println("POST response code: " + responseCode);
//        if (responseCode == HttpURLConnection.HTTP_CREATED) {
//            BufferedReader in =
//                    new BufferedReader(
//                            new InputStreamReader(connection.getInputStream()));
//            StringBuffer response = new StringBuffer();
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                response.append(inputLine);
//            }
//            in.close();
//            System.out.println(response.toString());
//        } else {
//            System.out.println("POST request not worked");
//        }
