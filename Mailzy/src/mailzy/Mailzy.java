/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFrame;

import mailzy.exchange.MailProvider;
import mailzy.storage.*;

/**
 *
 * @author lalexandrov
 */
public class Mailzy {
    public Mailzy(){
        HashMap<String, MailProvider> serviceProviders = this.loadProviders();
        try {
            Authenticator authenticator = new Authenticator(serviceProviders);
            while(!authenticator.isAuthenticated()){
                LoginForm loginForm = new LoginForm(this.mainApp, authenticator);
                loginForm.setVisible(true); 
            }
            this.openMainWindow(authenticator);
            authenticator.finishCredentials();
        } catch(Exception e){
        	e.printStackTrace();
            System.exit(1);
        }
    }
    
    public static void main(String[] args){
        Mailzy mailzy = new Mailzy();    
    }
    
    private HashMap<String, MailProvider> loadProviders () {
        HashMap<String, MailProvider> providers = new HashMap<String, MailProvider>();
        try {
            File credentials = new File(System.getProperty("user.dir")+"/providers.txt");
            Scanner scanner = null;
            scanner = new Scanner(credentials);
            scanner.useDelimiter(System.lineSeparator());
            while(scanner.hasNext()){
                String[] params = scanner.next().split(";");
                String[] smtpParams = params[0].split("\t");
                String[] address = smtpParams[1].split(":");
                for(String domain : smtpParams[0].split(","))
                    providers.put(domain, new MailProvider(address[0], Integer.parseInt(address[1]), smtpParams[2], params[1].split(":")[0]));
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return providers;
    }
  
    
    private void openMainWindow(Authenticator authenticator){
        this.mainApp =  new MainForm(authenticator);        
    }
    
    private JFrame mainApp = null;
}
