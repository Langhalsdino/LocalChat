/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 16.11.2014
  * @author Frederic J. Tausch
  */

package localchat;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

// Please finish DNS service stuff :)
// Bonjour could be so awesome :)

public class LocalChatJmDNS {
  
  // Anfang Attribute
  private int port = 9876;
  private String varType = "_localChat._tcp.local.";
  // Ende Attribute
  
  // Anfang Methoden
  public void indexServer(String name, String description){
    /*
    createService(varType, name, description);
    */
  }
  
  private void creatService(String type, String name, String description){
    /*
    //creat context
    JmDNS serverJmDNS = JmDNS.creat();
    
    //creat service info
    ServiceInfo info  = ServiceInfo.creat(type, name, port, decription);
    
    // register on network
    serverJmDNS.resterServic(info);
    */
  }
  
  public void indexClients(){
    listenToService();
  }
  
  private void listenToService(){
    /*
    //creat context
    jmDNS clientJmDNS = JmDNS.creat();
    
    // add service listener 
    clientJmDNS.addServiceListener(varType, listener = new ServiceListener(){
    // lisstener concept
    @Override
    public void serviceAdded(ServiceEvent event){}
    
    @Override
    public void serviceResoved(ServiceEvent event){}
    
    @Override
    public void serviceRemoved(ServiceEvent event){}
    }
    );  
    
    */
  }
  // Ende Methoden
}  // end of LocalChatJmDNS