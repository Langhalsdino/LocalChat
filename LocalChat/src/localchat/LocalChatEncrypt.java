/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 16.11.2014
  * @author Frederic J. Tausch & Lars Wrenger
  */

package localchat;

public class LocalChatEncrypt {
  
  // Anfang Attribute
  private int selectedType = 0;
  private final String[] types = {"Caesar"}; 
  // Ende Attribute
  
  // Anfang Methoden
  public String encrypt (String msg, String key){
    String encryptMsg = "Sorry, encryption did not work :(";
    if (selectedType == 0) {
      encryptMsg = entschluesselnCaeser(msg, key);
    } // end of if
    else {
      System.out.println("Error, could not finde selected decyption type :(");
    } // end of if-else
    return encryptMsg;
  }
  
  // The methode of Lars Wrenger was slidly modified to fit the requirements
  private String entschluesselnCaeser(String text,String key) {              //text=verschl�sselter Text | key=Schl�ssel
    int rot = (int) key.toUpperCase().charAt(0) - 'A';                      //rot = Rotation
    text=text.toUpperCase();
    rot=26-rot;                                                             //r�ckw�rts
    int l=text.length();                                                    //l=Textl�nge
    String code="";                                                         //entschl�sselter String
    for(int z=0;z<l;z++) {                                                  //z=position beim verschl�sselten Text
      char buchstabe=text.charAt(z);                                        //buchstabe aus dem verschl�sselten Text
      if(buchstabe>='A' && buchstabe<='Z') {                                //Nur Buchstaben
        buchstabe=(char)((int)buchstabe+rot);                               //Verschiebung
        if(buchstabe>'Z')buchstabe=(char)((int)buchstabe-26);               //f�gt wieder von A an wenn Buchstabe zu gro�
      }
      code=code.substring(0,z)+buchstabe;                                   //Speichern im code String
    }
    code=code.toLowerCase();
    return code;
  }
  
  public void changeTypeTo(String newType){
    for (int i=0;i<types.length;i++ ) {
      if (types[i]==newType) {
        selectedType = i;
      } // end of if
    } // end of for
  }
  
  public String[] getTypes(){
    return types; 
  }
  
  public int amountOfTypes(){
    return types.length;
  }
  
  // Ende Methoden
} // end of LocalChatEncrypt
