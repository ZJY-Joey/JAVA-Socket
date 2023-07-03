package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static Server.S_MyGUI.scs;

public class S_MySurveillanceForSend implements S_MyCommandSurveillanceForSend{

    JTextField jTextField;
    JComboBox<String> jComboBox;
    Socket sc;
    DataOutputStream out;

    public void setJTextField(JTextField jTextField){
        this.jTextField = jTextField;
    }
    public void setJComboBox(JComboBox<String> jComboBox){
        this.jComboBox = jComboBox;
    }

    public void actionPerformed(ActionEvent e){
        sc = scs.elementAt(jComboBox.getSelectedIndex());
        try {
            out = new DataOutputStream(sc.getOutputStream());
            out.writeUTF(jTextField.getText());
            jTextField.setText(null);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
