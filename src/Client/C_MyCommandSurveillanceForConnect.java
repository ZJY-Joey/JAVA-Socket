package Client;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.Socket;

public interface C_MyCommandSurveillanceForConnect extends ActionListener {
     void setJTextField_IP(JTextField jTextField);
     void setJTextField_Port(JTextField jTextField);
     void setJTextArea(JTextArea jTextArea);
     void setSocket(Socket socket);
     void setBtn(JButton jButton);
}
