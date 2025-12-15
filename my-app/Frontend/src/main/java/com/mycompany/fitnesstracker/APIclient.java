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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


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
