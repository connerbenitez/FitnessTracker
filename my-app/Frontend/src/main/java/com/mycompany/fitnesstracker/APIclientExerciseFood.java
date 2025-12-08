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
/**
 *
 * @author ashle
 */
public class APIclientExerciseFood {
//    public static void main(String[] args) {
//        // 1. Create the Client
//        HttpClient client = HttpClient.newHttpClient();
//
//        // 2. Build the Request (Add http:// protocol!)
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("http://127.0.0.1:4001/api/users/"))
//                .GET() // GET is default, but good to be explicit
//                .build();
//
//        try {
//            // 3. Send the request and receive response
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//
//            // 4. Print results
//            System.out.println("Status Code: " + response.statusCode());
//            System.out.println("Response Body: " + response.body());
//            
//            
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
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
    
    
    public String getExerciseByUser(int user_id) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:4001/api/exercises/user/" + Integer.toString(user_id)))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed: HTTP error code : " + response.statusCode());
        }

        return response.body(); // JSON object
    }
}
