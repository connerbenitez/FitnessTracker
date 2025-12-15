/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fitnesstracker;

// packages for HTTPS connection
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import com.google.gson.JsonObject;
/**
 *
 * @author ashle
 */
public class APIclient {

    public String getUsers() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:4001/api/users/"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed: HTTP error code : " + response.statusCode());
        }

        return response.body(); // JSON object
    }
    
    public String getUser(String userId) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:4001/api/users/" + userId)) //Still need the user id after slash
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed: HTTP error code : " + response.statusCode());
        }

        return response.body(); // JSON object
    }
    
    public String getUserByUsername(String username) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:4001/api/users/username/" + username))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 300) {
            throw new RuntimeException("Failed to fetch user");
        }

        return response.body();
    }
    
    public void addFollower(int followerUserId, int followingUserId) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        JsonObject body = new JsonObject();
        body.addProperty("follower_user_id", followerUserId);
        body.addProperty("following_user_id", followingUserId);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:4001/api/followers/create"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 300) {
            throw new RuntimeException("Failed to add follower");
        }
    }
    
    public String getFollowers(int userId) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:4001/api/followers/followers/" + userId))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed: HTTP error code " + response.statusCode());
        }

        return response.body();
    }
    
//    public void addMessage(int senderId, int recipientId, String message) throws Exception {
//        HttpClient client = HttpClient.newHttpClient();
//        
//        String json = String.format(
//                    "{ \"sender\": \"%s\", \"recipient\": \"%s\", \"message\": \"%s\" }",
//                    senderId, recipientId, message
//                );
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://127.0.0.1:4001/api/messages"))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(json))
//                .build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//        if (response.statusCode() != 201 && response.statusCode() != 200) {
//            throw new RuntimeException("Failed to send message");
//        }
//
//    }
    
    public void addMessage(int senderId, int recipientId, String message) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        JsonObject body = new JsonObject();
        body.addProperty("sender", senderId);
        body.addProperty("recipient", recipientId);
        body.addProperty("message", message);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:4001/api/messages/create"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 👇 DEBUG OUTPUT
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        if (response.statusCode() != 201 && response.statusCode() != 200) {
            throw new RuntimeException("Failed to send message");
        }
    }
    
    
    public String getGoals(Integer userId) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:4001/api/exercise-goals/user/" + userId)) //Still need the user id after slash
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed: HTTP error code : " + response.statusCode());
        }

        return response.body(); // JSON object
    }
    
    public String getDietGoals(Integer userId) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:4001/api/diet-goals/user/" + userId))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed: HTTP error code : " + response.statusCode());
        }

        return response.body(); // JSON object
    }
    

    private static final String BASE_URL = "http://127.0.0.1:4001/api";
    
    public static boolean addGoal(String userId, String goalType, String goalId, String description, int completion, Date startDate) {
        try {
            String json = "";
            String endpoint = "";

            if (goalType.equals("exercise")) {
                endpoint = "/exercise-goals";

                json = String.format(
                    "{ \"exercise_goal_id\": \"%s\", \"user_id\": \"%s\", \"description\": \"%s\", \"completion\": %d, \"start_date\": \"%s\", \"end_date\": null, \"goal_distance\": null, \"goal_type\": null, \"goal_time\": null }",
                    goalId, userId, description, completion, startDate.toString()
                );

            } else if (goalType.equals("diet")) {
                endpoint = "/diet-goals";

                json = String.format(
                    "{ \"diet_goal_id\": \"%s\", \"user_id\": \"%s\", \"description\": \"%s\", \"completion\": %d, \"start_date\": \"%s\", \"end_date\": null, \"calorie_goal\": null }",
                    goalId, userId, description, completion, startDate.toString()
                );

            } else {
                return false; 
            }

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endpoint))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

            return response.statusCode() == 200 || response.statusCode() == 201;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void updateGoalCompletion(int goalId, int completion, String goalType) throws Exception {

        String sub = "";
        String json = "";
        if (goalType.equals("exercise")) {
            sub = "exercise-goals/";
            json = String.format(
                    "{ \"exercise_goal_id\": \"%s\", \"completion\": \"%s\" }",
                    goalId, completion
                );
        }
        else if (goalType.equals("diet")) {
            sub = "diet-goals/";
            json = String.format(
                    "{ \"diet_goal_id\": \"%s\", \"completion\": \"%s\" }",
                    goalId, completion
                );
        }
        else throw new IllegalArgumentException("Invalid goal type");
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("http://127.0.0.1:4001/api/" + sub + goalId + "/completion"))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(json))
            .build();
        

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed to update goal: HTTP " + response.statusCode());
        }
    }
    
    public String getMessages(Integer userId) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:4001/api/messages/recipient/" + userId)) 
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed: HTTP error code : " + response.statusCode());
        }

        return response.body(); // JSON object
    }
    
}
    
    

