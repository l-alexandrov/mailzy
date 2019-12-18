package mailzy.exchange;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;
import java.util.List;

import mailzy.MainForm;

public class Notification {

	public void displayTray(String messageTitle, String messageText) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();
        
        
		
		
        
        //If the icon is a file
        //Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        Image image = Toolkit.getDefaultToolkit().getImage("/swing/images/32x32.png");
        Image image1 = Toolkit.getDefaultToolkit().getImage("src/swing/images/32x32.png");
		
        TrayIcon trayIcon = new TrayIcon(image1, "Message information");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("Mailzy super Email client");
        tray.add(trayIcon);

        trayIcon.displayMessage(messageTitle, messageText, MessageType.ERROR);
    }
	
}
