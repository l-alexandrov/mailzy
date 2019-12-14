/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy.exchange;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;

import com.sun.mail.imap.IMAPFolder;import com.sun.mail.imap.SortTerm;

import mailzy.Mail;
// import javax.activation.*;
/**
 *
 * @author lalexandrov
 */
public class MailReader
{

  public boolean connect(String[] args)
  {
    Properties props = new Properties();

    String host      = args[0];
    String username  = args[1];
    String password  = args[2];
    //String provider  = "pop3";
    String provider  = "imaps";
    try
    {
      //Connect to the server
      Session session = Session.getDefaultInstance(props, null);
      this.store     = session.getStore(provider);
      this.store.connect(host, username, password);
    } catch (MessagingException me) {
      System.err.println("messaging exception");
      me.printStackTrace();
    }
    return  this.store!=null && this.store.isConnected();
  }
  
    public ArrayList<Mail> getMessages() {

      ArrayList<Mail> mailList = new ArrayList<Mail>();
      try{
          //open the inbox folder
          Folder folder = this.store.getFolder("INBOX");
          IMAPFolder inbox = (IMAPFolder) folder;
          inbox.open(Folder.READ_ONLY);

          // get a list of javamail messages as an array of messages
          Message[] messages = inbox.getMessagesByUID(1, UIDFolder.MAXUID);

          for(int i = messages.length-1; i > messages.length-1-19; i--)
          {
              if(i<0)
                  break;
              String from = getFrom(messages[i]);
              if ( from==null){
                  continue;
              }
              from = removeQuotes(from);
              String subject = messages[i].getSubject();
              Date modifiedAt = messages[i].getReceivedDate();
              String body = getTextFromMessage(messages[i]);
              mailList.add(new Mail(from, subject, modifiedAt, body));
          }

          //close the inbox folder but do not
          //remove the messages from the server
          inbox.close(false);


      }
      catch (NoSuchProviderException nspe){
          System.err.println("invalid provider name");
      } catch (MessagingException me) {
          System.err.println("messaging exception");
          me.printStackTrace();
      } catch (IOException ex) {
          ex.printStackTrace();
      }

        return mailList;
    }
    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart)  throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }
    private static String getFrom(Message javaMailMessage) 
    throws MessagingException
    {
      String from = "";
      Address a[] = javaMailMessage.getFrom();
      if ( a==null ) return null;
      for ( int i=0; i<a.length; i++ )
      {
        Address address = a[i];
        from = from + address.toString();
      }

      return from;
    }

  private static String removeQuotes(String stringToModify)
  {
    int indexOfFind = stringToModify.indexOf(stringToModify);
    if ( indexOfFind < 0 ) return stringToModify;

    StringBuffer oldStringBuffer = new StringBuffer(stringToModify);
    StringBuffer newStringBuffer = new StringBuffer();
    for ( int i=0, length=oldStringBuffer.length(); i<length; i++ )
    {
      char c = oldStringBuffer.charAt(i);
      if ( c == '"' || c == '\'' )
      {
        // do nothing
      }
      else
      {
        newStringBuffer.append(c);
      }

    }
    return new String(newStringBuffer);
  }
  public void finish(){
      try {
          this.store.close();
      } catch (MessagingException ex) {
          ex.printStackTrace();
      }
  }

    private Store store;
}
