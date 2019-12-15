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
        if("TLS".equals(encryption.toUpperCase()) || "STARTTLS".equals(encryption.toUpperCase())){
            return new TLSMailSender(smtpServer, port, fromMail, password);
        } else if ("SSL".equals(encryption.toUpperCase())){
            return new SSLMailSender(smtpServer, port, fromMail, password);
        }
        else {
            return null;
        }
    }
}
