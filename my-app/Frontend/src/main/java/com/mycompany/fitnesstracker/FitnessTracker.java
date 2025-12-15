/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fitnesstracker;

/**
 *
 * @author ashle
 */
public class FitnessTracker {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        java.awt.EventQueue.invokeLater(() -> {
            new MainScreen().setVisible(true);
        });
    }
}
