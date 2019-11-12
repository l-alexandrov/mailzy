/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy;

import javax.swing.JFrame;

/**
 *
 * @author lalexandrov
 */
public class Mailzy {
    
    public Mailzy(){
        if(!checkIfLogged()){
            //TODO: Show dialog for logging in
        } else {
            JFrame form =  new MainForm();
            form.setVisible(true);
        }
    }
    
    public static void main(String[] args){
        Mailzy mailzy = new Mailzy();    
    }
    
    public final Boolean checkIfLogged(){
        //TODO: Write logic to check if logged
        return true;
    }
    
}
