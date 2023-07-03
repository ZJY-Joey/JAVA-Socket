package Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;


//根据输入的IP地址与端口号进行连接、监听信息线程 Client、ClientListen
public class C_MySurveillanceForConnect extends Thread implements C_MyCommandSurveillanceForConnect{
    JTextField jTextField_IP;
    JTextField jTextField_Port;
    JTextArea jTextArea;
    JButton btn;
    Socket client;
    DataInputStream in;
    String str;
    public void setJTextField_IP(JTextField jTextField){
        this.jTextField_IP = jTextField;
    }
    public void setJTextField_Port(JTextField jTextField){
        this.jTextField_Port = jTextField;
    }
    public void setBtn(JButton jButton){
        this.btn = jButton;
    }
    public void setJTextArea(JTextArea jTextArea){
        this.jTextArea = jTextArea;
    }
    public void setSocket(Socket socket){
        this.client = socket;
    }

    public void actionPerformed(ActionEvent e){
        btn.setEnabled(false);
        //判断IP与Port都填写完整，进行连接
        String IP, Port;
        if((Port = jTextField_Port.getText()) != null){
            InetAddress address;
            //开始进行连接
            try {
                if((IP = jTextField_IP.getText()) == null){
                    address = InetAddress.getLocalHost();
                }
                else{
                    address = InetAddress.getByName(IP);
                }
            }
            catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            }
            InetSocketAddress socketAddress = new InetSocketAddress(address, Integer.parseInt(Port));

            try {
                client.connect(socketAddress);
                jTextArea.append("Connecting to server...\n");
                jTextArea.paintImmediately(jTextArea.getBounds());
                in = new DataInputStream(client.getInputStream());

                Thread thread = this;
                thread.start();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
        else{
            jTextArea.append("Please enter right IP and Port...\n");
            jTextArea.paintImmediately(jTextArea.getBounds());
        }

    }
    public void run(){

        while(true){
            try {
                str = in.readUTF();
                jTextArea.append("From server: " + str + "\n");
            } catch (IOException ex) {
                jTextArea.append("Disconnected to server...");
                jTextArea.paintImmediately(jTextArea.getBounds());
                break;
            }
        }
    }
}
