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

	public void displayTray(String messageTitle, String messageText, MessageType type) throws AWTException {
        
        SystemTray tray = SystemTray.getSystemTray();
                
        Image image = Toolkit.getDefaultToolkit().getImage("src/swing/images/32x32.png");
		
        TrayIcon trayIcon = new TrayIcon(image, "Message information");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Mailzy super Email client");
        tray.add(trayIcon);

        trayIcon.displayMessage(messageTitle, messageText, type);
    }
	
}
