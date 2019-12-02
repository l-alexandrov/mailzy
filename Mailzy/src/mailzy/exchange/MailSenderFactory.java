/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy.exchange;

/**
 *
 * @author lalexandrov
 */
public class MailSenderFactory {
    public static MailSender getMailer(String smtpHostServer, short port){
        return new SimpleMailSender(smtpHostServer, port);
    }
    
    public static MailSender getMailer(String encryption, String smtpServer, short port, String fromMail, String password){
        if(encryption.toUpperCase() == "TLS"){
            return new TLSMailSender(smtpServer, port, fromMail, password);
        } else if (encryption.toUpperCase() == "SSL"){
            return new SSLMailSender(smtpServer, port, fromMail, password);
        }
        else {
            return null;
        }
    }
}
