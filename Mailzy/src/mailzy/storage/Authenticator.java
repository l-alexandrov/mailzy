/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy.storage;

import java.io.*;
import java.security.*;
import java.security.spec.*;
import java.util.*;
import javax.crypto.*;
import javax.mail.*;
import mailzy.exchange.MailProvider;
import mailzy.exchange.MailReader;
import mailzy.exchange.MailSender;
import mailzy.exchange.MailSenderFactory;
/**
 *
 * @author lalexandrov
 */
public class Authenticator {
    public Authenticator(HashMap<String, MailProvider> mailProviders) throws Exception {
        this.username = null;
        this.password = null;
        this.mailProviders = mailProviders;
        new File(this.fileName).createNewFile();
        this.mailReader = new MailReader();
        this.readCredentials();
    }
    public void saveCredentials(String username, String password) throws Exception{    
          try {
            File file = new File(this.fileName);
            PrintWriter p = new PrintWriter(this.fileName);
            p.println(Base64.getEncoder().encodeToString(this.encrypt(username)));
            p.println(Base64.getEncoder().encodeToString(this.encrypt(password)));
            p.close();
            this.username = username;
            this.password = password;
        } catch (FileNotFoundException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            throw new Exception();
        }
    }
    public void readCredentials() throws Exception {
        try {
            File credentials = new File(this.fileName);
            Scanner scanner = null;
            scanner = new Scanner(credentials);
            scanner.useDelimiter(System.lineSeparator());
            if(scanner.hasNext())
                this.username = this.decrypt(scanner.next());
            if(scanner.hasNext())
                this.password = this.decrypt(scanner.next());
            scanner.close();
        } catch (FileNotFoundException fileNotFoundException) {
            throw new Exception();
        }
    }

    private PublicKey getPublicKey(){
        PublicKey pk = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(this.publicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            pk = keyFactory.generatePublic(keySpec);
            return pk;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return pk;
    }

    private PrivateKey getPrivateKey(){
        PrivateKey pk = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(this.privateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            pk = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return pk;
    }

    private byte[] encrypt(String data) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
        return cipher.doFinal(data.getBytes());
    }

    private String decrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        Key privateKey = getPrivateKey();
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes())));
    }
    public String getUserName(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
    public boolean isAuthenticated(){
        return this.username!= null && this.password != null
                && !this.username.isBlank() && !this.password.isBlank() && mailProviderAccountCheck();
    }
    public void setforgetOnExit(boolean forget){
        this.forgetOnExit = forget;
    }
            
    private boolean mailProviderAccountCheck(){
        String provider = this.username.split("@")[1];
        MailProvider  mailProvider =  this.mailProviders.get(provider);
        if(mailProvider == null)
            return false;
        if(!this.mailReader.connect(new String[]{mailProvider.imapHost, this.username, this.password}))
            return false;
        
        this.mailSender = MailSenderFactory.getMailer(mailProvider.smtpEncryption, mailProvider.smtpHost, (short) mailProvider.smtpPort, this.username, this.password);
        return true;
    }
    
    
    public void finishCredentials()
    {
        this.mailReader.finish();
        if(forgetOnExit){            
            File file = new File(fileName);
            file.delete();
        }
    }
    
    public MailReader getMailReader(){
        return this.mailReader;
    }
    
    public MailSender getMailSender(){
        return this.mailSender;
    }
    private final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDbf114O+1t7OkgeM/qX/8Vpzxyz4h15Q9Z3Aqvsnk0wlTIqzzaE7JS0Rvv3RXb8JSFj9aF4wqNvX3xv5f4l4HcTpdJt5DBzjtlbs0kxvJnTLTifXPbFxDBj1lgZ8uBGJl1a3j4kppT+i/3YGLUmk1ifo0mPPwwmeeIHliyNgysowIDAQAB";
    private final String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANt/XXg77W3s6SB4z+pf/xWnPHLPiHXlD1ncCq+yeTTCVMirPNoTslLRG+/dFdvwlIWP1oXjCo29ffG/l/iXgdxOl0m3kMHOO2VuzSTG8mdMtOJ9c9sXEMGPWWBny4EYmXVrePiSmlP6L/dgYtSaTWJ+jSY8/DCZ54geWLI2DKyjAgMBAAECgYAIQligB5E9i6aSBDm+lfIhPHO31jtKRF45gWAdkFejNpS+IENf6VHSb+/fLLB/4COWiv0FeK+S+chdalorjnfiYnKcZTjH/XcWCTw9fyduMnX/3P31BevlLEVSWT4dvj4TpWJ4uQrl5Q19S2t+jFlWuO6X7nc47uuxhhGTw2ifwQJBAPKIAWdUo2UwzSQgfPzB6rnFMlWxLwO+VSLp9FbwJla6tH4sioiIK5M4PO3OkL4IxNQIak2X2p6zHkxH1w9h03ECQQDnr+VLVlx21yfvFW3s+VN5yxzY4dBsFu32qUjrOo0sMTEZgpvEWUaDRLh8DEZeQI9P26OIf5jSPOE6mAlZSY9TAkBg2qeU2FwYQRDraH4Bgn92iKW9SvD3kb72HnARd/4XjKAf8zGvrJGaTU8nuOJcwau48VNigU4xKl7jH51m6y5BAkA2VhBGjOh+jpM1BSeUrhyfsb0AOGVzFCWW9bi+QisdtCO5weHaOL3Kx3Ek1pQiQq3Zor9FofcrR0/jOAjpQdE1AkBmoiE5H1EDxHFh5skBiJtEVan3e+saO02O3OK6hsGuWnjPVMXZuBqm3K5WX+B8PCX0D18e2itoYO8iZoUG/tft";
    private final HashMap<String, MailProvider> mailProviders;
    private String username;
    private String password;
    private final MailReader mailReader;
    private MailSender mailSender;
    private final String fileName = System.getProperty("user.dir")+"/credentials.mlzy";
    private boolean forgetOnExit = false;
}
