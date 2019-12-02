/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy.exchange;

import java.util.Properties;
import javax.mail.*;
/**
 *
 * @author lalexandrov
 */
public class SSLMailSender extends MailSender {
    
    public SSLMailSender(String smtpServer, short port, String fromMail, String password){
        this.SMTP = smtpServer;
        this.port = port;
        this.fromMail = fromMail;
        this.password = password;
    }

    @Override
    protected void establishConnection() {
        Properties props = new Properties();
        props.put("mail.smtp.host", this.SMTP); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                        "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", this.port); //SMTP Port

        Authenticator auth = new AuthenticatorImpl(this.fromMail, this.password);

        this.session = Session.getDefaultInstance(props, auth);
    }
    
    private final String fromMail;
    private final String password;
    
}
