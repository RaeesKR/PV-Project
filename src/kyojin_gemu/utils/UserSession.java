/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kyojin_gemu.utils;

/**
 *
 * @author tdyir_drw1kcz
 */
public class UserSession {
    private int userId;
    private String userName;
    
    public UserSession(int userId,String userName){
        this.userId = userId;
        this.userName = userName;
    }
    
    public int  getUserId(){
        return userId;
    }
    
    public  String getUserName(){
        return userName;
    }
    
}
