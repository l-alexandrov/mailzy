/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import mailzy.storage.SQLiteConnector;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.time.format.DateTimeFormatter;
import mailzy.storage.Authenticator;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.text.StringEscapeUtils;

import javax.swing.event.ListSelectionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author hrist
 */
public class MainForm extends javax.swing.JFrame {

	private static final String String = null;
	/**
	 * Creates new form MainForm
         * @param authenticator
	 */
	public MainForm(Authenticator authenticator) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				finishApp();
			}


		});
		initComponents();
		this.setTitle("Mailzy");
		this.detailsElementsVisible(false);
		JLabel loading = new JLabel("");
		loading.setIcon(new ImageIcon(MainForm.class.getResource("/swing/images/loading.gif")));

		loading.setSize(256, 256);
		loading.setHorizontalAlignment(SwingConstants.CENTER);
		this.detailsPanel.add(loading, GroupLayout.Alignment.CENTER);
		this.setVisible(true);
		this.connection = null;
		this.mailListDetails = new ArrayList<Mail>();
		this.mailList.setModel(new DefaultListModel<String>());
		this.authenticator = authenticator;
		establishConnection();
        refreshDB();
        populateList();               
		
		this.detailsElementsVisible(true);
	}
	private void finishApp(boolean forceDelete) {
		this.authenticator.finishCredentials(forceDelete);
		
	}
	private void finishApp() {
		this.authenticator.finishCredentials();
		
	}

	private void populateList() {
		try {
            this.connection.query("SELECT * FROM mails WHERE reciever = '" + this.authenticator.getUserName() + "'");
            ResultSet rs = this.connection.fetch();
            while (rs.next()) {
                this.mailListDetails.add(new Mail( StringEscapeUtils.unescapeHtml4(rs.getString("sender")), StringEscapeUtils.unescapeHtml4(rs.getString("subject")), rs.getDate("recieved_at") ,StringEscapeUtils.unescapeHtml4(rs.getString("mail")) ));
            }
            for(int i=0;i< this.mailListDetails.size();i++){
                ((DefaultListModel<String>) this.mailList.getModel()).addElement(this.mailListDetails.get(i).subject);
            }
		} catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "A Database query error occured!");
            System.exit(1);
		}
		//System.out.println(this.mailListDetails.get(0));
		
	}

	private void establishConnection() {
		try {
            this.connection = new SQLiteConnector();
		} catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "A Database connection error occured!");
            System.exit(1);
		}
		
	}

	private void detailsElementsVisible(boolean b) {
		this.fromLabel.setVisible(b);
		this.fromInput.setVisible(b);
		this.mailTextPane.setVisible(b);
		this.fromInput.setVisible(b);
		this.fromLabel.setVisible(b);
		this.toInput.setVisible(b);
		this.toLabel.setVisible(b);
		this.subjectInput.setVisible(b);
		this.subjectLabel.setVisible(b);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		detailsPanel = new javax.swing.JPanel();
		fromLabel = new javax.swing.JLabel();
		toLabel = new javax.swing.JLabel();
		subjectLabel = new javax.swing.JLabel();
		mailTextPane = new javax.swing.JScrollPane();
		mailText = new javax.swing.JEditorPane();
		mailText.setEditable(false);
		toInput = new javax.swing.JTextField();
		toInput.setEditable(false);
		fromInput = new javax.swing.JTextField();
		fromInput.setEditable(false);
		subjectInput = new javax.swing.JTextField();
		subjectInput.setEditable(false);
		speechPanel = new javax.swing.JPanel();
		newMailBtn = new javax.swing.JButton();
		newMailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		speechBtn = new javax.swing.JToggleButton();
		mainMenuBar = new javax.swing.JMenuBar();
		mailMenu = new javax.swing.JMenu();
		newMailItem = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		fromLabel.setText("From:");

		toLabel.setText("To:");

		subjectLabel.setText("Subject:");

		mailText.setContentType("text/html");
		mailText.setEditorKit(null);
		mailTextPane.setViewportView(mailText);
		mailText.getAccessibleContext().setAccessibleDescription("");

		javax.swing.GroupLayout detailsPanelLayout = new javax.swing.GroupLayout(detailsPanel);
		detailsPanelLayout.setHorizontalGroup(
			detailsPanelLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(detailsPanelLayout.createSequentialGroup()
					.addGroup(detailsPanelLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(detailsPanelLayout.createSequentialGroup()
							.addGroup(detailsPanelLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(subjectLabel)
								.addComponent(toLabel, Alignment.TRAILING)
								.addComponent(fromLabel, Alignment.TRAILING))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(detailsPanelLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(toInput, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
								.addComponent(fromInput, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
								.addComponent(subjectInput, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)))
						.addComponent(mailTextPane, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
					.addGap(12))
		);
		detailsPanelLayout.setVerticalGroup(
			detailsPanelLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(detailsPanelLayout.createSequentialGroup()
					.addGap(1)
					.addGroup(detailsPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(fromInput, GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE)
						.addComponent(fromLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(detailsPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(toLabel)
						.addComponent(toInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(detailsPanelLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(subjectInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(subjectLabel))
					.addGap(18)
					.addComponent(mailTextPane, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
		);
		detailsPanel.setLayout(detailsPanelLayout);

		newMailBtn.setText("New mail");

		javax.swing.GroupLayout speechPanelLayout = new javax.swing.GroupLayout(speechPanel);
		speechPanelLayout.setHorizontalGroup(speechPanelLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(newMailBtn, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE));
		speechPanelLayout.setVerticalGroup(speechPanelLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(newMailBtn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE));
		speechPanel.setLayout(speechPanelLayout);

		speechBtn.setText("Speech");

		mailMenu.setText("New Mail");
		mailMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				mailMenuActionPerformed(evt);
			}
		});

		newMailItem.setAccelerator(
				javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
		newMailItem.setText("New");
		newMailItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				newMailItemActionPerformed(evt);
			}
		});
		mailMenu.add(newMailItem);

		mainMenuBar.add(mailMenu);

		setJMenuBar(mainMenuBar);

		mailList = new JList();
		mailList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int index = mailList.getSelectedIndex();
				if(index < 1)
					return;
				Mail mail  = mailListDetails.get(index);
				fromInput.setText(mail.from);
				subjectInput.setText(mail.subject);
				mailText.setText(mail.body);
				changeTitle(mail.subject);
			}
		});

		JPanel jPanelMenu = new JPanel();
		jPanelMenu.setForeground(new Color(0, 0, 0));
		jPanelMenu.setBackground(new java.awt.Color(29, 44, 99));
		

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
							.addComponent(speechPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(speechBtn))
						.addGroup(layout.createSequentialGroup()
							.addComponent(jPanelMenu, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(mailList, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(detailsPanel, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)))
					.addContainerGap())
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(detailsPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
						.addComponent(mailList, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
						.addComponent(jPanelMenu, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(speechPanel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(speechBtn, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
		);
		JLabel menuLogo = new JLabel("Mailzy");
		JLabel lblMenu = new JLabel();  
		
		lblMenu.setToolTipText("Menu");
		lblMenu.setIcon(new ImageIcon(MainForm.class.getResource("/swing/images/icons8_menu_35px_3.png")));
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);

		lblMenu.addMouseListener(new MouseAdapter() {
			//boolean sideBarOpen = false;

			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 
				 * if (sideBarOpen == false) {
				 * 
				 * // System.out.println( jPanelMenu.getLocationOnScreen().getY() - //
				 * getContentPane().getLocationOnScreen().getY()); //
				 * System.out.println(jPanelMenu.getSize().height);
				 * 
				 * // jPanelMenu.setSize(jPanelMenu.getSize().w,jPanelMenu.getSize().height);
				 * Thread th = new Thread() {
				 * 
				 * @Override public void run() { for (int i = 45; i <= 150; i++) { try {
				 * Thread.sleep(2); } catch (InterruptedException ex) {
				 * 
				 * } jPanelMenu.setSize(i, jPanelMenu.getSize().height); lblMenu.setLocation(i -
				 * 37, 10); } // System.out.println(lblMenu.getLocation());
				 * 
				 * 
				 * }
				 * 
				 * }; th.start(); //menuLogo.setLocation(113, 10); //menuLogo.setVisible(true);
				 * 
				 * sideBarOpen = true; }
				 * 
				 * else if (sideBarOpen == true) { //menuLogo.setVisible(false); Thread th = new
				 * Thread() {
				 * 
				 * @Override public void run() { for (int i = 150; i >= 45; i--) { try {
				 * Thread.sleep(2); } catch (InterruptedException ex) {
				 * 
				 * } jPanelMenu.setSize(i, jPanelMenu.getSize().height); lblMenu.setLocation(i -
				 * 37, 10); } // System.out.println(lblMenu.getLocation());
				 * 
				 * }
				 * 
				 * }; th.start();
				 * 
				 * sideBarOpen = false;
				 * 
				 * // System.out.println("DONE"); }
				 */
			showMenu(jPanelMenu, lblMenu);	
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			lblMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));	
			}
		});
		
		JPanel menuNewMailPanel = new JPanel();
		menuNewMailPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			menuNewMailPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));	
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				showNewMailForm();
			}
		});
		menuNewMailPanel.setBackground(new java.awt.Color(29, 44, 99));
		menuSendMailPanel = new JPanel();
		menuSendMailPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuSendMailPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));	
			}
		});
		menuSendMailPanel.setBackground(new java.awt.Color(29, 44, 99));
		SendMailIcon = new JLabel("");
		SendMailIcon.setIcon(new ImageIcon(MainForm.class.getResource("/swing/images/icons8_send_email_20px.png")));
		SendMailIcon.setToolTipText("Recieved");
		SendMailIcon.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblRecievedMails = new JLabel();
		lblRecievedMails.setForeground(Color.WHITE);
		lblRecievedMails.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecievedMails.setText("Recieved");
		lblRecievedMails.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_menuSendMailPanel = new GroupLayout(menuSendMailPanel);
		gl_menuSendMailPanel.setHorizontalGroup(
			gl_menuSendMailPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuSendMailPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(SendMailIcon, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRecievedMails, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_menuSendMailPanel.setVerticalGroup(
			gl_menuSendMailPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuSendMailPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_menuSendMailPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(SendMailIcon)
						.addComponent(lblRecievedMails, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		menuSendMailPanel.setLayout(gl_menuSendMailPanel);
		//menuSendMailPanel.setToolTipText("Send");
		menuRefreshPanel = new JPanel();
		menuRefreshPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			menuRefreshPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));	
			}
		});
		menuRefreshPanel.setBackground(new java.awt.Color(29, 44, 99));
		refreshIcon = new JLabel("");
		refreshIcon.setIcon(new ImageIcon(MainForm.class.getResource("/swing/images/icons8_refresh_20px.png")));
		refreshIcon.setToolTipText("Refresh");
		refreshIcon.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblRefresh = new JLabel();
		lblRefresh.setForeground(Color.WHITE);
		lblRefresh.setText("Refresh");
		lblRefresh.setHorizontalAlignment(SwingConstants.CENTER);
		lblRefresh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_menuRefreshPanel = new GroupLayout(menuRefreshPanel);
		gl_menuRefreshPanel.setHorizontalGroup(
			gl_menuRefreshPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuRefreshPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(refreshIcon, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRefresh, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_menuRefreshPanel.setVerticalGroup(
			gl_menuRefreshPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuRefreshPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_menuRefreshPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(refreshIcon, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRefresh, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		menuRefreshPanel.setLayout(gl_menuRefreshPanel);
		
		menuUserProfilePanel = new JPanel();
		menuUserProfilePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				finishApp(true);
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			menuUserProfilePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		menuUserProfilePanel.setBackground(new java.awt.Color(29, 44, 99));
		userProfileIcon = new JLabel("");
		userProfileIcon.setIcon(new ImageIcon(MainForm.class.getResource("/swing/images/icons8_customer_20px.png")));
		userProfileIcon.setToolTipText("Profile");
		userProfileIcon.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblProfile = new JLabel();
		lblProfile.setForeground(Color.WHITE);
		lblProfile.setText("Log out");
		lblProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_menuUserProfilePanel = new GroupLayout(menuUserProfilePanel);
		gl_menuUserProfilePanel.setHorizontalGroup(
			gl_menuUserProfilePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuUserProfilePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(userProfileIcon, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblProfile, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_menuUserProfilePanel.setVerticalGroup(
			gl_menuUserProfilePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuUserProfilePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_menuUserProfilePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(userProfileIcon)
						.addComponent(lblProfile, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		menuUserProfilePanel.setLayout(gl_menuUserProfilePanel);
		
		
		menuLogo.setVisible(true);
		menuLogo.setHorizontalAlignment(SwingConstants.CENTER);
		menuLogo.setForeground(Color.WHITE);
		menuLogo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JSeparator separator = new JSeparator();
		GroupLayout gl_jPanelMenu = new GroupLayout(jPanelMenu);
		gl_jPanelMenu.setHorizontalGroup(
			gl_jPanelMenu.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jPanelMenu.createSequentialGroup()
					.addGroup(gl_jPanelMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanelMenu.createSequentialGroup()
							.addGap(6)
							.addComponent(lblMenu)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(menuLogo, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addComponent(menuUserProfilePanel, GroupLayout.PREFERRED_SIZE, 150, Short.MAX_VALUE)
						.addGroup(gl_jPanelMenu.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(menuRefreshPanel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(menuSendMailPanel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
							.addComponent(menuNewMailPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_jPanelMenu.setVerticalGroup(
			gl_jPanelMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jPanelMenu.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_jPanelMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_jPanelMenu.createParallelGroup(Alignment.LEADING, false)
							.addComponent(menuLogo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblMenu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(menuNewMailPanel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(menuSendMailPanel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(menuRefreshPanel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(menuUserProfilePanel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(194, Short.MAX_VALUE))
		);
		
		newMailIcon = new JLabel("");
		newMailIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showNewMailForm();
			}
		});
		newMailIcon.setIcon(new ImageIcon(MainForm.class.getResource("/swing/images/icons8_new_message_20px.png")));
		newMailIcon.setToolTipText("New Mail");
		newMailIcon.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewMail = new JLabel();
		lblNewMail.setForeground(Color.WHITE);
		lblNewMail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewMail.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewMail.setText("New Mail");
		GroupLayout gl_menuNewMailPanel = new GroupLayout(menuNewMailPanel);
		gl_menuNewMailPanel.setHorizontalGroup(
			gl_menuNewMailPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuNewMailPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(newMailIcon, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewMail, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
		);
		gl_menuNewMailPanel.setVerticalGroup(
			gl_menuNewMailPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuNewMailPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_menuNewMailPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(newMailIcon)
						.addComponent(lblNewMail, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
					.addContainerGap())
		);
		menuNewMailPanel.setLayout(gl_menuNewMailPanel);
		jPanelMenu.setLayout(gl_jPanelMenu);
		getContentPane().setLayout(layout);
		
		
		
		pack();
		
		
	}// </editor-fold>//GEN-END:initComponents

	private void mailMenuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mailMenuActionPerformed

	}// GEN-LAST:event_mailMenuActionPerformed
	
	private void newMailItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_newMailItemActionPerformed
		showNewMailForm();
		
	}// GEN-LAST:event_newMailItemActionPerformed
        
        private void refreshDB() {
            ArrayList<Mail> mailArrayList = this.authenticator.getMailReader().getMessages();
            if(mailArrayList.size() > 0){
                boolean success = true;
                this.connection.beginTransaction();
                if(!this.connection.deleteWhere("mails"," reciever = '" + this.authenticator.getUserName() + "'")){
                    this.connection.rollBack();
                    JOptionPane.showMessageDialog(this, "A Database query error occured!");
                    return;
                }

                String[] columns = {"sender", "reciever", "recieved_at", "subject", "mail", "created_at"};
                ArrayList<String[]> insertData = new ArrayList<String[]>();
                DateTimeFormatter dbDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                for(Mail mail: mailArrayList){
                    insertData.add(new String[]{
                    		StringEscapeUtils.escapeHtml4(mail.from).replaceAll("'", "''"),
                    		this.authenticator.getUserName(),
                    		String.valueOf(mail.lastModified.getTime()),
                    		StringEscapeUtils.escapeHtml4(mail.subject).replaceAll("'", "''"),
                    		StringEscapeUtils.escapeHtml4(mail.body).replaceAll("'", "''"),
                    		String.valueOf(System.currentTimeMillis())
                    		});
                }
                if(!this.connection.multipleInsert("mails", columns, insertData)){
                    this.connection.rollBack();
                    JOptionPane.showMessageDialog(this, "A Database query error occured!");
                    return;
                }
                this.connection.commit();
            }
        }
	private void changeTitle(String newTitle) {
		this.setTitle("Mailzy"+" - "+newTitle);
	}
	private void showMenu(JPanel panelName, JLabel labelname) {
		
		if (isSideBarOpen == false) {
			//TODO sideBar function
				Thread th = new Thread() {
					@Override
					public void run() {
						for (int i = 45; i <= 150; i++) {
							try {
								Thread.sleep(2);
							} catch (InterruptedException ex) {

							}
							panelName.setSize(i, panelName.getSize().height);
							labelname.setLocation(i - 37, 10);
						}
						// System.out.println(lblMenu.getLocation());
						

					}

				};
				th.start();
				//menuLogo.setLocation(113, 10);
				//menuLogo.setVisible(true);

				isSideBarOpen = true;
			}

			else if (isSideBarOpen == true) {
				//menuLogo.setVisible(false);
				Thread th = new Thread() {
					public void run() {
						for (int i = 150; i >= 45; i--) {
							try {
								Thread.sleep(2);
							} catch (InterruptedException ex) {

							}
							panelName.setSize(i, panelName.getSize().height);
							labelname.setLocation(i - 37, 10);
						}
						// System.out.println(lblMenu.getLocation());

					}

				};
				th.start();

				isSideBarOpen = false;

				// System.out.println("DONE");
			}
		
		
	}
	
	
	private void showNewMailForm() {
            Mail mail = new Mail();

            new NewMailDialog(this, true, mail);
            
            //authenticator.mailSender.sendEmail(toEmail, mail.from, mail.subject, mail.body);
            //TODO: Send it via this.authenticator.mailWriter and save it in the db
            
            
		//frame.getContentPane().add(detailsPanel,BorderLayout.CENTER); 
		//detailsPanel.add(c4Panel,BorderLayout.CENTER); 
	}
        boolean isSideBarOpen = false;
	private ArrayList<Mail> mailListDetails;
	private SQLiteConnector connection;
        private final Authenticator authenticator;
	// Variables declaration - do not modify                     
	private javax.swing.JPanel detailsPanel;
	private javax.swing.JTextField fromInput;
	private javax.swing.JLabel fromLabel;
	private javax.swing.JMenu mailMenu;
	private javax.swing.JEditorPane mailText;
	private javax.swing.JScrollPane mailTextPane;
	private javax.swing.JMenuBar mainMenuBar;
	private javax.swing.JButton newMailBtn;
	private javax.swing.JMenuItem newMailItem;
	private javax.swing.JToggleButton speechBtn;
	private javax.swing.JPanel speechPanel;
	private javax.swing.JTextField subjectInput;
	private javax.swing.JLabel subjectLabel;
	private javax.swing.JTextField toInput;
	private javax.swing.JLabel toLabel;
	private JList mailList;
	private JLabel newMailIcon;
	private JLabel SendMailIcon;
	private JLabel refreshIcon;
	private JLabel userProfileIcon;
	private JPanel menuSendMailPanel;
	private JPanel menuRefreshPanel;
	private JPanel menuUserProfilePanel;
	private JLabel lblNewMail;
	private JLabel lblRecievedMails;
	private JLabel lblRefresh;
	private JLabel lblProfile;
}
