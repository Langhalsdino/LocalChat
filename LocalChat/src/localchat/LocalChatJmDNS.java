/**
  *
  * Beschreibung
  * 
  * Remove method is not finished!!!
  *
  * @version 1.0 vom 16.11.2014
  * @author Frederic J. Tausch
  */

package localchat;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import javax.jmdns.*;

// Please finish DNS service stuff :)
// Bonjour could be so awesome :)

public class LocalChatJmDNS {
  
  // Anfang Attribute
  private final int port = 9876;
  private final String varType = "_localChat._tcp.local.";
  private final String varName = "LocalChat";
  private String myDescription = "It's me Mario";
  
  private JmDNS jmDnsServer;
  
  // list of index arrays
  // ajust max index for a bigger index
  private final int maxIndex = 100;
  private String[] ipIndex = new String[maxIndex];
  private int[] portIndex = new int[maxIndex];
  private String[] descriptionIndex = new String[maxIndex];
  
  private boolean serviceRegistered = false;
  
  // Ende Attribute
  
  // Anfang Methoden
  
  private void registerDNS(String serviceType, String serviceName){
    try{
        if(serviceRegistered!=true){
            String localHost = "" + InetAddress.getLocalHost();
            System.out.println(localHost);
            String[] myIp = localHost.split("/");
            ipIndex[0] = myIp[1];
            portIndex[0] = port;
            descriptionIndex[0] = myDescription;

            jmDnsServer = JmDNS.create(ipIndex[0]);

            // Register a test service.
            ServiceInfo localChat = ServiceInfo.create(serviceType, serviceName, portIndex[0], descriptionIndex[0]);
            
            System.out.println(Arrays.toString(ipIndex));
            
            jmDnsServer.registerService(localChat);

            serviceRegistered = true;
        }
        else{
            jmDnsServer.close();
        }
    }
    catch (IOException ioe){
        System.err.println("jmDNS Service can't be created or registered");
        ioe.printStackTrace(System.err);
    }
    
    System.out.println("Service registered");
  }
  
  private void searchForService(){
      jmDnsServer.addServiceListener(varType, new ServiceListener() {
          
          public void serviceResolved(ServiceEvent event) {
              ServiceInfo serviceInfo = event.getInfo();
              if(serviceInfo.getName().equals(varName)) {
                  // Found new Service :)
                  InetAddress newIp = serviceInfo.getInetAddresses()[0];
                  addToIndex(("" + newIp));
                  
                  int newPort = serviceInfo.getPort();
                  addToPort(newPort);
                  
                  String newDescription = serviceInfo.getPropertyString("key");
                  addToDescription(newDescription);
              }
          }
          public void serviceRemoved(ServiceEvent event) {
              ServiceInfo serviceInfo = event.getInfo();
              if(serviceInfo.getName().equals(varName)) {
                  System.out.println("Lost you " + serviceInfo.getName());
                  // Service got removed :(
              }
          }
          public void serviceAdded(ServiceEvent event) {
          System.out.println("got ya!!!");
          }
      });
      
      System.out.println("search for services started");
  }
  
  private void removeService(){
      serviceRegistered = false;
  }
  
  private void addToIndex(String inetAddr){
      int count = 0;
      for(int i = 0;ipIndexAt(i)!=null;i++){
          count = i;
      }
      ipIndex[count] = inetAddr;
  }
  
  private void addToPort(int newPort){
      int count = 0;
      for(int i = 0;portIndexAt(i)!=0;i++){
          count = i;
      }
      portIndex[count] = newPort;
  }
  
  private void addToDescription(String newdescription){
      int count = 0;
      for(int i = 0;descriptionIndexAt(i)!=null;i++){
          count = i;
      }
      descriptionIndex[count] = newdescription;
  }
          
  public void registerZeroConf(){
      registerDNS(varType, varName);
      startIndex();
  }
  
  public void startIndex(){
      searchForService();
  }
  
  public void endIndex(){
      removeService();
  }
  
  public String ipIndexAt(int position){
      return ipIndex[position];
  }
  
  public int portIndexAt(int position){
      return portIndex[position];
  }
  
  public String descriptionIndexAt(int position){
      return descriptionIndex[position];
  }
  
  public String[] getIpIndex(){
      return ipIndex;
  }
  
  public int[] getPortIndex(){
      return portIndex;
  }
  
  public String[] getDescriptionIndex(){
      return descriptionIndex;
  }
  
  public void changeMyDescription(String newDescription){
      removeService();
      descriptionIndex[0] = newDescription;
      myDescription = newDescription;
      registerZeroConf();
      startIndex();
  }
  
  // Ende Methoden
}  // end of LocalChatJmDNS