/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.sql.Connection;
import kyojin_gemu.utils.DatabaseConection;
import kyojin_gemu.utils.UserSession;
import model.Player;

/**
 *
 * @author tdyir
 */
public class AuthControllers {
    private Connection conn = DatabaseConection.getConnection();
    
    public boolean register(String username, String pwd){
        try {
            String query = "INSERT INTO users (username,password) values (?,?)";
            PreparedStatement  ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, pwd);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Register Gagal : "+ e.getMessage());
            return false;
        }
    }
    
    
    public  UserSession login(String username, String pwd){
        try {
           String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pwd);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return new UserSession(
                        rs.getInt("id_user"),
                        rs.getString("username")
                );
            }

          
//            if (username.equals("a") && pwd.equals("a")) {
//                return new Player(1,"mas mas wedang",100,15);
//            }
//            

        } catch (Exception e) {
            System.err.println("Login gagal : " + e.getMessage());
        }   
        return null;
    }
    
}
