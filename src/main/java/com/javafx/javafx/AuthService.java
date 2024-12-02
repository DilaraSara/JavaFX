/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javafx.javafx;

public class AuthService {
    public boolean authenticate(String username, String password) {
        
        return "MESSTAJER".equals(username) && "12345".equals(password);
    }
}
