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
public class TLSMailSender extends MailSender {
    public TLSMailSender(String smtpServer, short port, String fromMail, String password){
        this.SMTP = smtpServer;
        this.port = port;
        this.fromMail = fromMail;
        this.password = password;
        establishConnection();
    }

    @Override
    protected void establishConnection() {
        Properties props = new Properties();
        props.put("mail.smtp.host", this.SMTP); //SMTP Host
        props.put("mail.smtp.port", this.port); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        Authenticator auth = new AuthenticatorImpl(this.fromMail, this.password);

        this.session = Session.getInstance(props, auth);
    }
    
    private final String fromMail;
    private final String password;

    
}
