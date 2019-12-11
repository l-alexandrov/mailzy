/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author lalexandrov
 */
public class Mail {
    public Mail(String from, String subject, Date lastModified, String body){
        this.from = from;
        this.subject = subject;
        this.lastModified = lastModified;
        this.body = body;
    }
    public Mail(){};
    
    public String from;
    public String subject;
    public Date lastModified; 
    public String body;
}
