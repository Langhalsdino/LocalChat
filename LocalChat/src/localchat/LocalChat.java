/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 13.11.2014
  * @author Frederic J. Tausch
  */

package localchat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LocalChat extends JFrame {
  // Anfang Attribute
  private JTextArea jTextAreaChat = new JTextArea("");
  private JScrollPane jTextAreaChatScrollPane = new JScrollPane(jTextAreaChat);
  private JTextField jTextFieldChat = new JTextField();
  private JButton jButtonSend = new JButton();
  private JTextArea jTextAreaOnline = new JTextArea("");
  private JScrollPane jTextAreaOnlineScrollPane = new JScrollPane(jTextAreaOnline);
  private JTextField jTextFieldKey = new JTextField();
  private JComboBox jComboBoxTypes = new JComboBox();
  
  private String[] onlineArr = {"localhost","","","","","","","","","","","","","","","","","","","",""};
  // Ende Attribute
  
  public LocalChat(String title) { 
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 623; 
    int frameHeight = 373;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    jTextAreaChatScrollPane.setBounds(8, 8, 481, 289);
    jTextAreaChat.setEditable(false);
    cp.add(jTextAreaChatScrollPane);
    jTextFieldChat.setBounds(8, 304, 481, 33);
    cp.add(jTextFieldChat);
    jButtonSend.setBounds(496, 304, 113, 33);
    jButtonSend.setText("Send");
    jButtonSend.setMargin(new Insets(2, 2, 2, 2));
    jButtonSend.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButtonSend_ActionPerformed(evt);
      }
    });
    cp.add(jButtonSend);
    jTextAreaOnlineScrollPane.setBounds(496, 8, 113, 217);
    jTextAreaOnline.setEditable(true);
    cp.add(jTextAreaOnlineScrollPane);
    jTextFieldKey.setBounds(496, 264, 113, 33);
    jTextFieldKey.setEditable(true);
    jTextFieldKey.setText("a");
    cp.add(jTextFieldKey);
    jComboBoxTypes.setBounds(496, 232, 113, 25);
    cp.add(jComboBoxTypes);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public LocalChat
  
  // Anfang Methoden
  public void jButtonSend_ActionPerformed(ActionEvent evt) {
    // classes
    LocalChatClient client = new LocalChatClient();
    LocalChatDecrypt decryptSend = new LocalChatDecrypt();
    
    // prepare decryption
    String msg = jTextFieldChat.getText();
    decryptSend.changeTypeTo((String) jComboBoxTypes.getSelectedItem());
    String key = jTextFieldKey.getText();
    
    // decryption
    String decryptMsg = decryptSend.decrypt(msg,key);
    
    for (int i=0;i<=20;i++) {
      if (onlineArr[i]!="") {
        client.send(decryptMsg,onlineArr[i]);
      } // end of if
    } // end of for
    jTextFieldChat.setText("");
  } // end of jButtonSend_ActionPerformed
  
  // Ende Methoden
  
  public static void main(String[] args) {
    LocalChat localChat = new LocalChat("LocalChat");
    LocalChatDecrypt decrypt = new LocalChatDecrypt();
    LocalChatEncrypt encrypt = new LocalChatEncrypt();
    
    for (int i=0;i<decrypt.amountOfTypes();i++) {
      localChat.jComboBoxTypes.addItem(decrypt.getTypes()[i]);
    } // end of for
    LocalChatServer server = new LocalChatServer();
    while (true) { 
      // start server, wait in IDLE
      server.startServer();
      
      // preperation for encryption; (Encrypted chat is only stored in the textField!! The LocalChatServer Class has no access to the encrypted Text!!)
      String newHistory = server.returnHistory();
      String oldHistory = localChat.jTextAreaChat.getText();
      encrypt.changeTypeTo((String) localChat.jComboBoxTypes.getSelectedItem());
      String key = localChat.jTextFieldKey.getText();
      int splitAt = (int) oldHistory.lastIndexOf('\n') + 1;
      
      // Encrypt only the new part :)
      String newChat = oldHistory + encrypt.encrypt(newHistory.substring(splitAt, newHistory.lastIndexOf('\n')),key);
      
      // updating the Chat
      localChat.jTextAreaChat.setText(newChat);
    } // end of while
  } // end of main
  
} // end of class localChat
