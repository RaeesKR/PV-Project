/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kyojin_gemu.utils;
import java.sql.Connection;
import java.sql.DriverManager;  // ← ini yang wajib
import java.sql.SQLException;


/**
 *
 * @author tdyir
 */
public class DatabaseConection {
    private static  final String URL = "jdbc:mysql://localhost:3306/db_kyojin_gemu";
    private static  final String USER = "root";
    private static  final String PASS ="";
    private static  Connection conn;
    
    public  static Connection getConnection(){
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Koneksi ke database berhasil!");                
                
            }
        } catch (ClassNotFoundException e) {
            System.out.println("driver tidak di temukan:"+e.getMessage());
        }catch(SQLException e){
            System.out.println("koneksi gagal :"+e.getMessage());
        }
        return conn;
    }
    
    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println(" Koneksi database ditutup.");
            }
        } catch (SQLException e) {
            System.out.println(" Gagal menutup koneksi: " + e.getMessage());
        }
    }
   
}
