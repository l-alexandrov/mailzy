/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy.storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.*;
import java.util.*;
import javax.crypto.*;
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
        loadKeys();
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
    public void loadKeys() {
    	Path path1 = Path.of("RSA/publicKey");
    	Path path2 = Path.of("RSA/privateKey");
    	if(!Files.exists(path1) || !Files.exists(path2)) {
    		this.generateKeys();
    	}

        try {
			this.publicKey = getPublicKey(Files.readAllBytes(path1));
			this.privateKey = getPrivateKey(Files.readAllBytes(path2));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    private void generateKeys() {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
	        keyGen.initialize(1024);
	        KeyPair pair = keyGen.generateKeyPair();
	        this.writeKeyToFile("RSA/publicKey", Base64.getEncoder().encode(pair.getPublic().getEncoded()));
	        this.writeKeyToFile("RSA/privateKey", Base64.getEncoder().encode(pair.getPrivate().getEncoded()));
	    } catch (NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
    }
    public void writeKeyToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }
    private PublicKey getPublicKey(byte[] publicKeyBytes){
        PublicKey pk = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyBytes));
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

    private PrivateKey getPrivateKey(byte[] privateKeyBytes){
        PrivateKey pk = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyBytes));
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
        cipher.init(Cipher.ENCRYPT_MODE, this.publicKey);
        return cipher.doFinal(data.getBytes());
    }

    private String decrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, this.privateKey);
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
    public void finishCredentials(boolean forceDelete)
    {
        this.mailReader.finish();
        if(forceDelete || forgetOnExit){            
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
    private PublicKey publicKey = null;
    private PrivateKey privateKey = null;
    private final HashMap<String, MailProvider> mailProviders;
    private String username;
    private String password;
    private final MailReader mailReader;
    private MailSender mailSender;
    private final String fileName = System.getProperty("user.dir")+"/credentials.mlzy";
    private boolean forgetOnExit = true;
}
