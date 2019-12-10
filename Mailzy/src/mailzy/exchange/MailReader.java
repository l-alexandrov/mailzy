/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailzy.exchange;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
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
  
  public ArrayList<Mail> getMessages(int begin, int end) {
    
    ArrayList<Mail> mailList = new ArrayList<Mail>();
    try{
      //open the inbox folder
      Folder inbox = this.store.getFolder("INBOX");
      inbox.open(Folder.READ_ONLY);

      // get a list of javamail messages as an array of messages
      Message[] messages = inbox.getMessages();

      for(int i = begin; i < end; i++)
      {
        if(i>=messages.length)
            break;
        String from = getFrom(messages[i]);
        if ( from==null){
            continue;
        }
        from = removeQuotes(from);
        String subject = messages[i].getSubject();
       Date modifiedAt = messages[i].getReceivedDate();
       String body = messages[i].getContent().toString();
       mailList.add(new Mail(from, subject, modifiedAt, body));
      }
      
      //close the inbox folder but do not
      //remove the messages from the server
      inbox.close(false);


    }
    catch (NoSuchProviderException nspe)
    {
      System.err.println("invalid provider name");
    } catch (MessagingException me) {
      System.err.println("messaging exception");
      me.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    
      return mailList;
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
