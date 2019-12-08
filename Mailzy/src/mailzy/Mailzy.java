/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy;

import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JFrame;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import mailzy.storage.*;

/**
 *
 * @author lalexandrov
 */
public class Mailzy {
    public Mailzy(){

        try{
            Authenticator authenticator = new Authenticator();
            while(!authenticator.isAuthenticated())
            {
                LoginForm loginForm = new LoginForm(this.mainApp, authenticator);
                loginForm.setVisible(true); 
            }
            
            this.openMainWindow();
            authenticator.finishCredentials();
        }
        catch(Exception e){
            System.exit(1);
        }
    }
    
    public static void main(String[] args){
        Mailzy mailzy = new Mailzy();    
    }
    
  
    
    private void openMainWindow(){
        this.mainApp =  new MainForm();        
    }
    
    private JFrame mainApp = null;
}
