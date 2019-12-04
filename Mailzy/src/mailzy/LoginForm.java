/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy;


import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;


/**
 *
 * @author hrist
 */
public class LoginForm extends javax.swing.JDialog {
    
    /**
     * Creates new form LoginForm
     */
    public LoginForm(JFrame frame) {
        super(frame, true);
        this.setDefaultCloseOperation(0);
        initComponents();
        this.setResizable(false);
        passwordVisible.setVisible(false);
        account.setText("Enter your account");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        loginIcon = new javax.swing.JLabel();
        passwordIcon = new javax.swing.JLabel();
        emailIcon1 = new javax.swing.JLabel();
        account = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        passwordVisible = new javax.swing.JLabel();
        loginButton1 = new javax.swing.JButton();
        gmailLabel = new javax.swing.JLabel();
        abvLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rememberMe = new javax.swing.JCheckBox();
        outlookLabel = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        
        account.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		if("Enter your account".equals(account.getText()))
        	        account.setText("");
        	}
        	@Override
        	public void focusLost(FocusEvent e) {
        		if(account.getText().isBlank()){
                    account.setText("Enter your account");
                }
        	}
        });
        
        password = new javax.swing.JPasswordField();
        password.addFocusListener(new FocusAdapter() {
        	public void focusGained(FocusEvent e) {
        		if("Enter your password".equals(password.getText())) {
        			password.setText("");
        			passwordVisibleBtn();
        		}
        	}
        	@Override
        	public void focusLost(FocusEvent e) {
        		if(password.getText().isBlank() || "Enter your password".equals(password.getText())) {
        			password.setText("Enter your password");
        		}
        	}
        });

        setTitle("Login");
        setBackground(new java.awt.Color(255, 255, 255));
        setFocusCycleRoot(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 727));

        jPanel2.setBackground(new java.awt.Color(29, 44, 99));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mailzy");
        jLabel1.setMaximumSize(new java.awt.Dimension(150, 50));
        jLabel1.setMinimumSize(new java.awt.Dimension(150, 50));
        jLabel1.setName("LOGO"); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 50));

        loginIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/icons8_login_127px_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                .addContainerGap())
        );

        passwordIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/icons8_password_80px.png"))); // NOI18N

        emailIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/icons8_send_hot_list_80px.png"))); // NOI18N

        account.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        account.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        account.setText("Enter you account");
        account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accountMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accountMouseExited(evt);
            }
        });

        password.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        password.setText("Enter your password");
        password.setPreferredSize(new java.awt.Dimension(93, 28));
        password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordMouseClicked(evt);
            }
        });

        passwordVisible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/icons8_invisible_40px.png"))); // NOI18N
        passwordVisible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                passwordVisibleMouseClicked(evt);
            }
        });

        loginButton1.setBackground(new java.awt.Color(29, 44, 99));
        loginButton1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        loginButton1.setForeground(new java.awt.Color(255, 255, 255));
        loginButton1.setText("Login");
        loginButton1.setToolTipText("");
        loginButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButton1ActionPerformed(evt);
            }
        });

        gmailLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/icons8_gmail_35px_1.png"))); // NOI18N
        gmailLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gmailLabelMouseClicked(evt);
            }
        });

        abvLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        abvLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/abv-logo.png"))); // NOI18N
        abvLabel.setMaximumSize(new java.awt.Dimension(20, 20));
        abvLabel.setMinimumSize(new java.awt.Dimension(20, 20));
        abvLabel.setPreferredSize(new java.awt.Dimension(20, 20));
        abvLabel.setRequestFocusEnabled(false);
        abvLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abvLabelMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Register");

        rememberMe.setText("Remember me");

        outlookLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/swing/images/icons8_microsoft_outlook_35px_1.png"))); // NOI18N
        outlookLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                outlookLabelMouseClicked(evt);
            }
        });

        errorLabel.setForeground(new java.awt.Color(255, 51, 51));
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(emailIcon1)
                        .addGap(18, 18, 18)
                        .addComponent(account, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(passwordIcon)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loginButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(passwordVisible, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rememberMe)
                                    .addGap(40, 40, 40))))))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gmailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outlookLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(abvLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(130, 130, 130))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(account, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(emailIcon1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(48, 48, 48)
                            .addComponent(passwordVisible))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(passwordIcon)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(loginButton1)
                .addGap(16, 16, 16)
                .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rememberMe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(outlookLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(abvLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
        ImageIcon iconVisible = new ImageIcon(getClass().getResource("..//swing//images//icons8_eye_40px.png"));
        //ImageIcon iconInvisible = new ImageIcon(getClass().getResource("icons8_invisible_40px.png")); // another way but must png file exist in mailzy folder
        ImageIcon iconInvisible = new ImageIcon(getClass().getResource("..//swing//images//icons8_invisible_40px.png"));
    private void passwordVisibleMouseClicked(java.awt.event.MouseEvent evt) {                                                     
    	passwordIconChange();
    }                                            
    private void passwordIconChange() { //Change text to * or * to text on click and change icons
    	if(passwdVisibleClicked%2==0){
            password.setEchoChar((char)0);
            passwdVisibleClicked++;
            passwordVisible.setIcon(iconVisible);
        }
        else{
            password.setEchoChar('*');
            passwdVisibleClicked++;
            passwordVisible.setIcon(iconInvisible);
        }
    }
    private void passwordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_passwordMouseClicked
    	passwordVisibleBtn();
    }//GEN-LAST:event_passwordMouseClicked
private void passwordVisibleBtn() { 
	if(passwdClicked==0){
        password.setText("");
        passwdClicked++;
    }
    passwordVisible.setVisible(true);
}

    private void accountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountMouseExited
        // TODO add your handling code here:
        if(account.getText().isBlank()){
            account.setText("Enter your account");
        }
    }//GEN-LAST:event_accountMouseExited

    private void accountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountMouseEntered
        // TODO add your handling code here:
        if("Enter your account".equals(account.getText()))
        account.setText("");
    }//GEN-LAST:event_accountMouseEntered

    private void gmailLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gmailLabelMouseClicked
        // TODO add your handling code here:
        String url = "https://accounts.google.com/signup/v2/webcreateaccount?service=accountsettings&continue=https%3A%2F%2Fmyaccount.google.com%2Fb%2F110810760468130681345%2F%3Futm_source%3Dsign_in_no_continue%26nlr%3D1%26pageId%3Dnone&dsh=S-1710728375%3A1574237796118791&gmb=exp&biz=false&flowName=GlifWebSignIn&flowEntry=SignUp";
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    
    }//GEN-LAST:event_gmailLabelMouseClicked

    private void abvLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_abvLabelMouseClicked
        // TODO add your handling code here:
        String url = "https://passport.abv.bg/app/profiles/registration";
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_abvLabelMouseClicked

    private void outlookLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_outlookLabelMouseClicked
        // TODO add your handling code here:
        String url = "https://signup.live.com/?lic=1";
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_outlookLabelMouseClicked

    private void loginButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButton1ActionPerformed
        // TODO add your handling code here:
        if( account.getText().isBlank() || password.getText().isBlank() || "Enter your account".equals(account.getText())){
            errorLabel.setText("Plese enter email and password!");
        }
        else{
             this.setVisible(false);
        }
        String fullAccount=account.getText();
        String mailSystem=fullAccount.substring(fullAccount.lastIndexOf("@")+1);
        System.out.println(mailSystem); //print only text after @
        
    }//GEN-LAST:event_loginButton1ActionPerformed
        int passwdClicked=0;
        int passwdVisibleClicked=2;
        
    private ArrayList<String> test() throws FileNotFoundException, IOException, URISyntaxException{
        URL url = getClass().getResource("..//mailzy//storage//test.txt");
        File file = new File(url.toURI());
        System.out.println(file);
        ///BufferedReader br = new BufferedReader(new FileReader(file));
        ///String line= br.readLine();
        ArrayList<String> arr = new ArrayList<String>();
        String path = file.toString();
        Scanner sc = new Scanner(new File(path));
        while(sc.hasNextLine()){
            arr.add(sc.nextLine());
        } 
        for(String column: arr ){
            System.out.println(column); //list all data
        }
        return arr;
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//               
//                
//                //f.setResizable(false);
//            }
//        });
//        
//        
//        
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel abvLabel;
    private javax.swing.JTextField account;
    private javax.swing.JLabel emailIcon1;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel gmailLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton loginButton1;
    private javax.swing.JLabel loginIcon;
    private javax.swing.JLabel outlookLabel;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel passwordIcon;
    private javax.swing.JLabel passwordVisible;
    private javax.swing.JCheckBox rememberMe;
    // End of variables declaration//GEN-END:variables
}
