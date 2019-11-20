/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy;

import java.sql.SQLException;

import javax.swing.JFrame;
import mailzy.storage.*;

/**
 *
 * @author lalexandrov
 */
public class Mailzy {
    
    public Mailzy(){
        LoginForm loginForm = new LoginForm();
        try{
            Authenticator authenticator = new Authenticator();

            while(!authenticator.isAuthenticated()){
                loginForm.setVisible(true); 
            }
            this.openMainWindow();
        }
        catch(Exception e){
            System.exit(1);
        }
    }
    
    public static void main(String[] args){
        Mailzy mailzy = new Mailzy();    
    }
    
  
    
    private void openMainWindow(){
        JFrame form =  new MainForm();        
    }
    
}
