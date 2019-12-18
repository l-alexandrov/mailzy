/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy.exchange;
import java.util.Date;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import mailzy.NewMailDialog;

/**
 *
 * @author lalexandrov
 */
public abstract class MailSender {
    public boolean sendEmail(String toEmail,String fromEmail, String subject, String body){
        try {
          MimeMessage msg = new MimeMessage(this.session);
          //set message headers
          msg.addHeader("Content-Type", "text/html; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");
          msg.setFrom(new InternetAddress(fromEmail, fromEmail));

          msg.setReplyTo(InternetAddress.parse(fromEmail, false));

          msg.setSubject(subject, "UTF-8");
          
          msg.setContent(body, "text/html; charset=utf-8");
          msg.setSentDate(new Date());

          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
          System.out.println("Message is ready");
          Transport.send(msg);
          
          System.out.println("EMail Sent Successfully!!");
          
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
    }
    
    protected abstract void establishConnection();
    
    protected String SMTP;
    protected short port;
    
    protected Session session;
}
