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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class APIclient {
    private static final String BASE_URL = "http://127.0.0.1:4001";
    private final HttpClient client;
    
    public APIclient() {
        this.client = HttpClient.newHttpClient();
    }
    
    // Helper method to build request
    private HttpRequest.Builder buildRequest(String endpoint) {
        return HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + endpoint));
    }
    
    // Helper method to execute request
    private String executeRequest(HttpRequest request) throws Exception {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        int statusCode = response.statusCode();
        if (statusCode < 200 || statusCode >= 300) {
            throw new RuntimeException("Failed: HTTP error code: " + response.statusCode() + 
                    ", Response: " + response.body());
        }
        return response.body();
    }
    
    // ============ USER ROUTES ============
    
    // GET all users
    public String getAllUsers() throws Exception {
        HttpRequest request = buildRequest("/api/users/")
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // GET user by ID
    public String getUserById(int id) throws Exception {
        HttpRequest request = buildRequest("/api/users/" + id)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Create user
    public String createUser(String userJson) throws Exception {
        HttpRequest request = buildRequest("/api/users/create")
                .POST(HttpRequest.BodyPublishers.ofString(userJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Login
    public String login(String credentialsJson) throws Exception {
        HttpRequest request = buildRequest("/api/users/login")
                .POST(HttpRequest.BodyPublishers.ofString(credentialsJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Update password
    public String changePassword(String passwordJson) throws Exception {
        HttpRequest request = buildRequest("/api/users/change_password")
                .POST(HttpRequest.BodyPublishers.ofString(passwordJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Update user profile
    public String updateUser(String userJson) throws Exception {
        HttpRequest request = buildRequest("/api/users/update")
                .PUT(HttpRequest.BodyPublishers.ofString(userJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // ============ MESSAGE ROUTES ============
    
    // Create a new message
    public String createMessage(String messageJson) throws Exception {
        HttpRequest request = buildRequest("/api/messages/create")
                .POST(HttpRequest.BodyPublishers.ofString(messageJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Get all messages
    public String getAllMessages() throws Exception {
        HttpRequest request = buildRequest("/api/messages/")
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get messages sent by a user
    public String getMessagesBySender(String sender) throws Exception {
        String encodedSender = URLEncoder.encode(sender, StandardCharsets.UTF_8);
        HttpRequest request = buildRequest("/api/messages/sender/" + encodedSender)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get messages received by a user
    public String getMessagesByRecipient(String recipient) throws Exception {
        String encodedRecipient = URLEncoder.encode(recipient, StandardCharsets.UTF_8);
        HttpRequest request = buildRequest("/api/messages/recipient/" + encodedRecipient)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get a message by ID
    public String getMessageById(int id) throws Exception {
        HttpRequest request = buildRequest("/api/messages/" + id)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Update a message by ID
    public String updateMessage(int id, String messageJson) throws Exception {
        HttpRequest request = buildRequest("/api/messages/update/" + id)
                .PUT(HttpRequest.BodyPublishers.ofString(messageJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Delete a message by ID
    public String deleteMessage(int id) throws Exception {
        HttpRequest request = buildRequest("/api/messages/delete/" + id)
                .DELETE()
                .build();
        return executeRequest(request);
    }
    
    // ============ FOOD ROUTES ============
    
    // Create a new food
    public String createFood(String foodJson) throws Exception {
        HttpRequest request = buildRequest("/api/foods/create")
                .POST(HttpRequest.BodyPublishers.ofString(foodJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Get all foods
    public String getAllFoods() throws Exception {
        HttpRequest request = buildRequest("/api/foods/")
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get food by ID
    public String getFoodById(int id) throws Exception {
        HttpRequest request = buildRequest("/api/foods/" + id)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get food by name
    public String getFoodByName(String foodName) throws Exception {
        String encodedName = URLEncoder.encode(foodName, StandardCharsets.UTF_8);
        HttpRequest request = buildRequest("/api/foods/name/" + encodedName)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get food by calories
    public String getFoodByCalories(int calories) throws Exception {
        HttpRequest request = buildRequest("/api/foods/calories/" + calories)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Update a food by ID
    public String updateFood(int id, String foodJson) throws Exception {
        HttpRequest request = buildRequest("/api/foods/update/" + id)
                .PUT(HttpRequest.BodyPublishers.ofString(foodJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // ============ FOLLOWER ROUTES ============
    
    // Create a new follower relationship
    public String createFollower(String followerJson) throws Exception {
        HttpRequest request = buildRequest("/api/followers/create")
                .POST(HttpRequest.BodyPublishers.ofString(followerJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Get all followers of a user
    public String getFollowers(int userId) throws Exception {
        HttpRequest request = buildRequest("/api/followers/followers/" + userId)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get all users a user is following
    public String getFollowing(int userId) throws Exception {
        HttpRequest request = buildRequest("/api/followers/following/" + userId)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Delete a follower relationship
    public String deleteFollower(String followerJson) throws Exception {
        HttpRequest request = buildRequest("/api/followers/delete")
                .method("DELETE", HttpRequest.BodyPublishers.ofString(followerJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Get all followers
    public String getAllFollowers() throws Exception {
        HttpRequest request = buildRequest("/api/followers/")
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // ============ EXERCISE GOAL ROUTES ============
    
    // Create exercise goal
    public String createExerciseGoal(String goalJson) throws Exception {
        HttpRequest request = buildRequest("/api/exercise-goals/")
                .POST(HttpRequest.BodyPublishers.ofString(goalJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Get all exercise goals
    public String getAllExerciseGoals() throws Exception {
        HttpRequest request = buildRequest("/api/exercise-goals/")
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get exercise goals by user ID
    public String getExerciseGoalsByUser(int userId) throws Exception {
        HttpRequest request = buildRequest("/api/exercise-goals/user/" + userId)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get exercise goal by ID
    public String getExerciseGoalById(int id) throws Exception {
        HttpRequest request = buildRequest("/api/exercise-goals/" + id)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Update exercise goal
    public String updateExerciseGoal(int id, String goalJson) throws Exception {
        HttpRequest request = buildRequest("/api/exercise-goals/" + id)
                .PUT(HttpRequest.BodyPublishers.ofString(goalJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Delete exercise goal
    public String deleteExerciseGoal(int id) throws Exception {
        HttpRequest request = buildRequest("/api/exercise-goals/" + id)
                .DELETE()
                .build();
        return executeRequest(request);
    }
    
    // ============ EXERCISE ROUTES ============
    
    // Create exercise
    public String createExercise(String exerciseJson) throws Exception {
        HttpRequest request = buildRequest("/api/exercises/")
                .POST(HttpRequest.BodyPublishers.ofString(exerciseJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Get all exercises
    public String getAllExercises() throws Exception {
        HttpRequest request = buildRequest("/api/exercises/")
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get exercises by user ID (already exists, renaming for consistency)
    public String getExercisesByUser(int userId) throws Exception {
        HttpRequest request = buildRequest("/api/exercises/user/" + userId)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get exercise by ID
    public String getExerciseById(int id) throws Exception {
        HttpRequest request = buildRequest("/api/exercises/" + id)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get exercises by type
    public String getExercisesByType(String type) throws Exception {
        String encodedType = URLEncoder.encode(type, StandardCharsets.UTF_8);
        HttpRequest request = buildRequest("/api/exercises/type/" + encodedType)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get exercises by date
    public String getExercisesByDate(String date) throws Exception {
        String encodedDate = URLEncoder.encode(date, StandardCharsets.UTF_8);
        HttpRequest request = buildRequest("/api/exercises/date/" + encodedDate)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Update exercise
    public String updateExercise(int id, String exerciseJson) throws Exception {
        HttpRequest request = buildRequest("/api/exercises/" + id)
                .PUT(HttpRequest.BodyPublishers.ofString(exerciseJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Delete exercise
    public String deleteExercise(int id) throws Exception {
        HttpRequest request = buildRequest("/api/exercises/" + id)
                .DELETE()
                .build();
        return executeRequest(request);
    }
    
    // ============ DIET GOAL ROUTES ============
    
    // Create diet goal
    public String createDietGoal(String dietGoalJson) throws Exception {
        HttpRequest request = buildRequest("/api/diet-goals/")
                .POST(HttpRequest.BodyPublishers.ofString(dietGoalJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Get all diet goals
    public String getAllDietGoals() throws Exception {
        HttpRequest request = buildRequest("/api/diet-goals/")
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get diet goals by user ID
    public String getDietGoalsByUser(int userId) throws Exception {
        HttpRequest request = buildRequest("/api/diet-goals/user/" + userId)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get diet goals by diet ID
    public String getDietGoalsByDiet(int dietId) throws Exception {
        HttpRequest request = buildRequest("/api/diet-goals/diet/" + dietId)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get diet goals by start date
    public String getDietGoalsByStartDate(String startDate) throws Exception {
        String encodedDate = URLEncoder.encode(startDate, StandardCharsets.UTF_8);
        HttpRequest request = buildRequest("/api/diet-goals/start/" + encodedDate)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get diet goals by end date
    public String getDietGoalsByEndDate(String endDate) throws Exception {
        String encodedDate = URLEncoder.encode(endDate, StandardCharsets.UTF_8);
        HttpRequest request = buildRequest("/api/diet-goals/end/" + encodedDate)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Get diet goal by ID
    public String getDietGoalById(int id) throws Exception {
        HttpRequest request = buildRequest("/api/diet-goals/" + id)
                .GET()
                .build();
        return executeRequest(request);
    }
    
    // Update diet goal
    public String updateDietGoal(int id, String dietGoalJson) throws Exception {
        HttpRequest request = buildRequest("/api/diet-goals/" + id)
                .PUT(HttpRequest.BodyPublishers.ofString(dietGoalJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }
    
    // Delete diet goal
    public String deleteDietGoal(int id) throws Exception {
        HttpRequest request = buildRequest("/api/diet-goals/" + id)
                .DELETE()
                .build();
        return executeRequest(request);
    }
    
    // ============ DIET ROUTES ============

    // Create diet
    public String createDiet(String dietJson) throws Exception {
        HttpRequest request = buildRequest("/api/diets/")
                .POST(HttpRequest.BodyPublishers.ofString(dietJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }

    // Get all diets
    public String getAllDiets() throws Exception {
        HttpRequest request = buildRequest("/api/diets/")
                .GET()
                .build();
        return executeRequest(request);
    }

    // Get diet by ID
    public String getDietById(int dietId) throws Exception {
        HttpRequest request = buildRequest("/api/diets/" + dietId)
                .GET()
                .build();
        return executeRequest(request);
    }

    // Get diets by user ID
    public String getDietsByUser(int userId) throws Exception {
        HttpRequest request = buildRequest("/api/diets/user/" + userId)
                .GET()
                .build();
        return executeRequest(request);
    }

    // Get diets by food ID
    public String getDietsByFood(int foodId) throws Exception {
        HttpRequest request = buildRequest("/api/diets/food/" + foodId)
                .GET()
                .build();
        return executeRequest(request);
    }

    // Get diets by date
    public String getDietsByDate(String date) throws Exception {
        String encodedDate = URLEncoder.encode(date, StandardCharsets.UTF_8);
        HttpRequest request = buildRequest("/api/diets/date/" + encodedDate)
                .GET()
                .build();
        return executeRequest(request);
    }

    // Update diet
    public String updateDiet(int dietId, String dietJson) throws Exception {
        HttpRequest request = buildRequest("/api/diets/" + dietId)
                .PUT(HttpRequest.BodyPublishers.ofString(dietJson))
                .header("Content-Type", "application/json")
                .build();
        return executeRequest(request);
    }

    // Delete diet
    public String deleteDiet(int dietId) throws Exception {
        HttpRequest request = buildRequest("/api/diets/" + dietId)
                .DELETE()
                .build();
        return executeRequest(request);
    }
}