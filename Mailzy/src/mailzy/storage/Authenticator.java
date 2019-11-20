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

/**
 *
 * @author lalexandrov
 */
public class Authenticator {
    public Authenticator() throws Exception {
        this.username = null;
        this.password = null;
        new File(this.fileName).createNewFile();
        this.readCredentials();
    }
    public void saveCredentials(String username, String password) throws Exception{    
          try {
            File file = new File(this.fileName);
            PrintWriter p = new PrintWriter(this.fileName);
            p.println(this.encrypt(username).toString());
            p.println(this.encrypt(password).toString());
            p.close();
            this.username = username;
            this.password = password;
        } catch (FileNotFoundException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException fileNotFoundException) {
            throw new Exception();
        }
    }
    public void readCredentials() throws Exception {
        try {
            File credentials = new File(this.fileName);
            Scanner scanner = null;
            scanner = new Scanner(credentials);
            scanner.useDelimiter("\n");
            if(scanner.hasNext())
                this.username = scanner.next();
            if(scanner.hasNext())
                this.password = scanner.next();
        } catch (FileNotFoundException fileNotFoundException) {
            throw new Exception();
        }
    }
    private PublicKey getPublicKey(){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(this.base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }
    private PrivateKey getPrivateKey(){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(this.base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }
    private byte[] encrypt(String data) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	cipher.init(Cipher.ENCRYPT_MODE, this.getPublicKey());
	return cipher.doFinal(data.getBytes());
    }
    

    
    private String decrypt(String data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] byteData = Base64.getDecoder().decode(data.getBytes());
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
        return new String(cipher.doFinal(byteData));
    }

    public String getUserName(){
        return this.username;
    }
    
    public String getPassword(){
        return this.password;
    }
    public boolean isAuthenticated(){
        return this.username!= null && this.password != null
                && !this.username.isBlank() && !this.password.isBlank();
            
    }
    
    private final String base64PublicKey = "TUlJQklqQU5CZ2txaGtpRzl3MEJBUUVGQUFPQ0FROEFNSUlCQ2dLQ0FRRUFnZzkvV0VjVGRJd3ZuRkxFYzFDUTk2T1hJRm5vdXpEYlJJSkI4c2tDNCtyL0FzZzZldHlsekFoMG5uUmdzZ3RYSzIvZVEyN1ArVEtOL2lYUTJwa2ZhK0xycW1TZUx4K093djdQamdDa0ZkTVBJOGpuWklLbzdtLzBSS3pQajFVN1JOWjBaQXhhZ1FraUdXSHMwRDZzakRUQXJnTkRnUHFOSXhaWWQ3RnBwWmczZHg3emRsU3dLWW1xZVhWbklCYmxkK3V3MUdhWG5TUlZ6dm45c0ZDelFZTTdyRlpKRTAzTG8zTUxYQ29sNkp4UFpONVo1R3FZWFgvSnQ0dlZBTzR2M2dkNjQ0cWNOcEkxUU1BUGwxanZhTWltbkpESFcwTzh5T2ZOdXZjWW5mQWtuRU5tYTJmVTVXbHVKT3V5bGxuZ1ZGSUNkNmw4S1cyRU9iYVo4cnU0UFFJREFRQUI=";
    private final String base64PrivateKey = "TUlJRXZnSUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2d3Z2dTa0FnRUFBb0lCQVFDQ0QzOVlSeE4wakMrY1VzUnpVSkQzbzVjZ1dlaTdNTnRFZ2tIeXlRTGo2djhDeURwNjNLWE1DSFNlZEdDeUMxY3JiOTVEYnMvNU1vMytKZERhbVI5cjR1dXFaSjR2SDQ3Qy9zK09BS1FWMHc4anlPZGtncWp1Yi9SRXJNK1BWVHRFMW5Sa0RGcUJDU0laWWV6UVBxeU1OTUN1QTBPQStvMGpGbGgzc1dtbG1EZDNIdk4yVkxBcGlhcDVkV2NnRnVWMzY3RFVacGVkSkZYTytmMndVTE5CZ3p1c1Zra1RUY3VqY3d0Y0tpWG9uRTlrM2xua2FwaGRmOG0zaTlVQTdpL2VCM3JqaXB3MmtqVkF3QStYV085b3lLYWNrTWRiUTd6STU4MjY5eGlkOENTY1EyWnJaOVRsYVc0azY3S1dXZUJVVWdKM3FYd3BiWVE1dHBueXU3ZzlBZ01CQUFFQ2dnRUFNQ2trOHI5L0NNMVFaQWFTRUZGcEhRcEswQVA3RmpZRk85MHdKb01ndXQwdUNRMnorZ0x5c2hiL2VCMklJT1hxSlgrTFFsVXFWaERPVzZvTktLVkM2V1JoOS9yQ1NKamErSUg2d1k2ak5DR3ZuWVNyaXFDSGhhT1hSRWJwcFIzZWtLTUkvbk5Fb0R1TmtQQ2VESTdvdmhKYXR4VW1UZ0I1ZXVDdmFjTWNaK0RwZ0s3SXljODV2Y3hUeHF4TGxFd3pzTkhoYmx2YThocFJIWkFlOURvWDYxMGRaLzNjUzFMRmpPbytveC9XWFZlclJIZDZ6eFVtMkU4OWxLd21nOC9rVnR3aDhYVW9ObjlKUEgyQmlDbUhZeFptV0kwQ3loekRjREtaRmlHcHR6QWVRb0RBSy8yZmZKRGplZXVmd2lXTTk5MkticTdtdjM2MjhNWUlpR3lZZ1FLQmdRRFAwVmVJaFY3UXVUUTR2N3ZkV24yczNnOG1RQ0hheTQ3Zy9MeC9RWldxcmVpc3d3Y1pBK3JmQk5Qdzg3VEhRMGFPUTRMU0tEWWsweDdIcWJtV3pSWjhSRC9sYjh5Y0pZeDNYQUhudW9wbWFVaWJlSXZqZCsyUmlWKzgyZVNrQ3JlZDVqbmpVY3FCellxbHlmUGFLVFlsTEloUE96Y25KWksvTTRudXBvaW5XUUtCZ1FDZ053TkhFWjZlQm1hNlQvQ2ZLS0kxZDhBL0laRlBBV0RETGZYTVhTcU4rN3MxblRKSDQxZ2M3ODdKN1VPRmFUb204bEtpZGNmeVI4WEFPdDRta3N5YVFSODNJT0UzUXdlRVRJNlVmNUNPQTBkbTdVeGdWTzU2Y0Y5VmNHeGdKTHZTd3dzMndyY1J3R1VoOHV4dlYwUzh2bkVGNzlrRlV4L2NNZUQwNXlFZmhRS0JnUURDUHZFcmVVWVRvRmp3aGo2Sit4QW5LWFJOazNwUmNSeityUjFmY1NUODRRcnZtR2VrK1NWckNhODB0QWFRamhCLzN4aURhUFNhM1A4VEwxQjZaUDRVYnhLdVdSNk9BUWJYZGhlTnlxMmNaZUpvOTNjQnlkSXp5NHpDRDRTSDFFQ21oVkFzYXdIMjYvRWhYNi9maGMvSFZXV2ZjVUR2QlU2eUN2RnRNUm5nR1FLQmdRQ0duOU5jTzN6Nk1rek56M0JVZk5OWWRFaVJnOFpUQ2NoaUdWT0tKdHNrTlZ1STRJOG9ndXMrWFc5NGs2c0NCbnE3MFRFSW5FbW4xeEZleHRoMnR6Ry9pR3NQYXN4MElCbkgzSUNPWFdaOVBsb1lMZHZZc21VMWN3bFloTUE4UllHaHh3eThOZitZcGY4VStNZ2Rnc29hZ3pmN2toL2M3bzA5L0plWGtrTnQ5UUtCZ0NML1l1SDh5VDdVR2NPdkdRamg1alpCZlA2cHI3N3VYZktPWWpDSERsbEpWemMvMFZPYTVMUGVVWFNBR1Vkamh1TDBIUzNTMEU5a2l6bDZKaDk3YzdhWGs5SGlxV3oyUlZqNUx0Q3dvSzdvQm9DN1Fwb1VrYlNKVmJhSU9iS3JuZ3VIdXEvN2txYTE3T3BaR2hNNzN0eVA0MlgvRnNzeVJaVGoweFBXVW50UA==";
    private String username;
    private String password;
    private final String fileName = System.getProperty("user.dir")+"/credentials.txt";
}
