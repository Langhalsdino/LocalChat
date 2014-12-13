/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 16.11.2014
  * @author Frederic J. Tausch
  */

package localchat;

import java.net.*;
import java.io.*;

public class LocalChatClient {
  
  // Anfang Attribute
  // private int port = 9876;
  
  // Ende Attribute
  
  // Server is not able to handle feedback and identification!!!!
  
  // Anfang Methoden
  public void send(String msg, String adresse, int port){
    try { 
      startClient(adresse, msg, port);
    }
    catch (IOException ioe) {
      System.err.println("Client can't open for business!");
      ioe.printStackTrace(System.err);
    }
  }
  
  private void startClient(String iP, String msg, int port) throws IOException {
    Socket cSocket = new Socket(iP, port);
    OutputStream out = cSocket.getOutputStream();
    PrintStream pout = new PrintStream(out);
    pout.println(msg);
    pout.close();
    cSocket.close(); 
  }
  // Ende Methoden
} // end of LocalChatClient
