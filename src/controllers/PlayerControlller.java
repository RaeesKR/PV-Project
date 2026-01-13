/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import java.sql.PreparedStatement;
import java.sql.Connection;
import model.Player;
import java.sql.ResultSet;

import kyojin_gemu.utils.DatabaseConection;

/**
 *
 * @author tdyir_drw1kcz
 */
public class PlayerControlller {
    private Connection conn = DatabaseConection.getConnection();
    
    public Player getPlayerByUserId(int userId){
        try {
            String sql = "SELECT * FROM player WHERE id_user=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            
            ResultSet rs = ps.executeQuery();
            
            System.out.println(rs);
            if (rs.next()) {
               Player p = new  Player(rs.getInt("id_player"),
                        rs.getString("player_name"),
                        rs.getInt("max_hp"),
                        rs.getInt("base_attack")); 
               p.unlockLevel(rs.getInt("level"));
               return p;
            }
//            
        } catch (Exception e) {
                        System.out.println("Gagal load player: " + e.getMessage());

        }
        return null;
    }
    
    public  Player createNewPlayer(int userId,String playerName){
        try {
             String sql = """
                INSERT INTO player (id_user, player_name, level, max_hp, base_attack)
                VALUES (?, ?, 1, 100, 15)
            """;
            PreparedStatement ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, userId);
            ps.setString(2, playerName);
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if (rs.next()) {
                return new Player(
                        rs.getInt(1),
                        playerName,
                        100,
                        15
                );
            }
        } catch (Exception e) {
            System.out.println("Gagal Membuat Player");
        }
        return null;
    }
    
    public void UpLevelPlayer(int  playerId, int newLevel){
        try {
            String sql = "UPDATE player SET LEVEL=? WHERE id_player=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, newLevel);
            ps.setInt(2, playerId);
            ps.executeUpdate();
            System.out.println("Level player diupdate ke " + newLevel);

        } catch (Exception e) {
            System.out.println("Level tidak naik"+e.getMessage());
        }
    }
    
}
