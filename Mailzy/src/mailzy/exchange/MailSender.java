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

/**
 *
 * @author lalexandrov
 */
public abstract class MailSender {
    public boolean sendEmail(String toEmail, String subject, String body){
        try {
          MimeMessage msg = new MimeMessage(this.session);
          //set message headers
          msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
          msg.addHeader("format", "flowed");
          msg.addHeader("Content-Transfer-Encoding", "8bit");

          msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

          msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

          msg.setSubject(subject, "UTF-8");

          msg.setText(body, "UTF-8");

          msg.setSentDate(new Date());

          msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
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
