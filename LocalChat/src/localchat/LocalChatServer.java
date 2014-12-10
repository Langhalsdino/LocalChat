/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 16.11.2014
  * @author Frederic J. Tausch
  */ 

package localchat; 
     
import java.io.*;
import java.net.*;
   
public class LocalChatServer {
  
  // Anfang Attribute
  private int port = 9876;
  private boolean openForBusiness = false;
  private String history = "";
  // Ende Attribute
  
  // Anfang Methoden
  public void startServer() {
    openForBusiness = true;
    try { 
      creatingServer();
    }
    catch (IOException ioe) {
      System.err.println("Server can't open for business!");
      ioe.printStackTrace(System.err);
    }
  }
  
  private void creatingServer()throws IOException{
    ServerSocket sSocket = new ServerSocket(port);
    while (openForBusiness) {
      DataInputStream din = null;
      PrintStream pout = null;
      Socket sSocketCall = null;
      try {
        // wait for a new msg -> sleep while you are waiting
        sSocketCall = sSocket.accept();
        
        // get an input stream
        InputStream in = sSocketCall.getInputStream();
        din = new DataInputStream(in);
        
        // get an output stream 
        OutputStream out = sSocketCall.getOutputStream();
        pout = new PrintStream(out);
        
        // identify the server
        identification();
        
        // receive the msg from client 
        String receivedMsg  = din.readLine();
        
        // Please complet feedback :)
        /*
        // read it back to client
        pout.println("readBack: " + receivedMsg); 
        */
        
        addToHistory(receivedMsg);
        stopServer();
      }
      finally { // ensure close happens
        din.close();
        pout.close();     
        sSocket.close();    
      }
    }     
  }
  
  public void stopServer(){
    delateRegistrationDns();
    openForBusiness = false;
  }
  
  private void registerDns(){
    
  }
  
  private void delateRegistrationDns(){
    
  }
  
  // is not implemented
  private void identification(){
    String myID = "";
    // complet authentication
    /*
    pout.println(identification());
    */
  }
  
  private void addToHistory (String receivedMsg){
    history = history + receivedMsg + '\n';
    System.out.println(history); //delate me later, pls :)
  }
  
  public String returnHistory(){
    return history;
  }
  
  public boolean alive(){
    return openForBusiness;
  }
  // Ende Methoden
} // end of LocalChatServer
  
  
