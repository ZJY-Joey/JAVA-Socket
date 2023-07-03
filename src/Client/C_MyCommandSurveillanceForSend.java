package Client;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.Socket;

public interface C_MyCommandSurveillanceForSend extends ActionListener {
    void setJTextField(JTextField jTextField);
    void setSocket(Socket socket);
}
