/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy.exchange;

import java.util.Properties;
import javax.mail.Session;

/**
 *
 * @author lalexandrov
 */
public class SimpleMailSender extends MailSender {
    public SimpleMailSender(String smtpHostServer, short port){
        this.SMTP = smtpHostServer;
        this.port = port;
        establishConnection();
    }

    @Override
    protected final void establishConnection() {

        Properties props = System.getProperties();

        props.put("mail.smtp.host", this.SMTP);
        props.put("mail.smtp.port", this.port);

        this.session = Session.getInstance(props, null);
    }
    
}
