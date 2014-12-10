/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 16.11.2014
  * @author Frederic J. Tausch & Lars Wrenger
  */

package localchat;

public class LocalChatDecrypt {
  
  // Anfang Attribute
  private int selectedType = 0;
  private final String[] types = {"Caesar"}; 
  // Ende Attribute
  
  // Anfang Methoden
  public String decrypt (String msg, String key){
    String decryptMsg = "Sorry, decryption did not work :(";
    if (selectedType == 0) {
      decryptMsg = verschluesselnCaesar(msg, key);
    } // end of if
    else {
      System.out.println("Error, could not finde selected decyption type :(");
    } // end of if-else
    return decryptMsg;
  }
  
  // The methode of Lars Wrenger was slidly modified to fit the requirements
  private String verschluesselnCaesar (String text, String key) {           //text=Klartext | key=Schl�ssel
    int rot = (int) key.toUpperCase().charAt(0) - 'A';                      //rot = Rotation 
    text=text.toUpperCase();
    int l=text.length();                                                    //l=Textl�nge
    String code="";                                                         //verschl�sselter String
    int s=0;                                                                //s=position beim verschl�sselten Text
    for(int z=0;z<l;z++) {                                                  //z=position beim Klartext
      char buchstabe=text.charAt(z);                                        //buchstabe aus Klartext
      if(buchstabe>='A' && buchstabe<='Z') {                                //Aussortierung anderer Zeichen
        buchstabe=(char)((int)buchstabe+rot);                               //Verschiebung
        if(buchstabe>'Z')buchstabe=(char)((int)buchstabe-26);               //f�gt wieder von A an wenn Buchstabe zu gro�
        code=code.substring(0,s)+buchstabe;                                 //Speichern im code String
        s++;
      }
    }
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
} // end of LocalChatDecrypt
