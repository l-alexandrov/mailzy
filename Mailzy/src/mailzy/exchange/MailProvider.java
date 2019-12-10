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
public class MailProvider {
    public MailProvider(String smtpHost, int smtpPort, String smtpEncryption, String imapHost){
        this.smtpHost = smtpHost;
        this.imapHost = imapHost;
        this.smtpPort = smtpPort;
        this.smtpEncryption = smtpEncryption;
    }
    
    public String smtpHost;
    public String imapHost;
    public int smtpPort;
    public String smtpEncryption;
    
}
