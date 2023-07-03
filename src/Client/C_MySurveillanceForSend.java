package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

//发送信息  ClientTx

public class C_MySurveillanceForSend implements C_MyCommandSurveillanceForSend {
    JTextField jTextField;
    Socket client;
    DataOutputStream out;

    public void setJTextField(JTextField jTextField){
        this.jTextField = jTextField;
    }
    public void setSocket(Socket socket){
        this.client = socket;
    }

    public void actionPerformed(ActionEvent e){
        try {
            out = new DataOutputStream(client.getOutputStream());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            out.writeUTF(jTextField.getText());
            jTextField.setText(null);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
