/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy.exchange;

import javax.mail.*;

/**
 *
 * @author lalexandrov
 */
public class AuthenticatorImpl extends Authenticator {

    public AuthenticatorImpl(String fromMail, String password) {
        super();
        this.fromEmail = fromMail;
        this.password = password;
    }

    //override the getPasswordAuthentication method
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.fromEmail, this.password);
    }

    String fromEmail;
    String password;
}
