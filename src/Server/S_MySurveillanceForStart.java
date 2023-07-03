package Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

import static Server.S_MyGUI.socket_name;
import static Server.S_MyGUI.scs;


//accept工作线程 作用：基于GUI，读取port框内内容进行连接操作
//为客户取名，并且为每一位用户开启监听线程

public class S_MySurveillanceForStart extends Thread implements S_MyCommandSurveillanceForStart{
    JTextField Port;
    JTextArea jTextArea;
    JButton jButton;
    ServerSocket serverSocket;
    Socket sc;
    String client = "Client";
    String Client_name = null;
    static int client_num = 1;
    public void setJTextField(JTextField jTextField){
        this.Port = jTextField;
    }
    public void setJTextArea(JTextArea TextArea){
        this.jTextArea = TextArea;
    }
    public void setJBtn(JButton jButton){ this.jButton = jButton;}

    public void actionPerformed(ActionEvent e){
        Thread thread = this;
        thread.start();
    }
    public void run(){
        while(true){
            try{
                if(Port.getText() != null){
                    serverSocket = new ServerSocket(Integer.parseInt(Port.getText()));
                }
                else{
                    jTextArea.append("Please enter right Port...");
                    jTextArea.paintImmediately(jTextArea.getBounds());
                    break;
                }
            }
            catch (IOException exception){
                jTextArea.append("Server listening..."+ "\n");
                jTextArea.paintImmediately(jTextArea.getBounds());
            }
            try {
                if(client_num == 1){
                    jTextArea.append("Server starting..."+ "\n");
                    jTextArea.paintImmediately(jTextArea.getBounds());
                }
                sc = serverSocket.accept();
                scs.add(sc);
                if(Objects.equals(socket_name.get(0), "null")){
                    socket_name.remove(0);
                }
                Client_name = client + client_num;
                jTextArea.append(Client_name+" connected..."+ "\n");
                socket_name.add(Client_name);
                jTextArea.paintImmediately(jTextArea.getBounds());
            } catch (IOException even) {
                jTextArea.append("Server waiting..."+ "\n");
                jTextArea.paintImmediately(jTextArea.getBounds());
            }
            if(sc != null){
                client_num++;
                new ServerListenThread(sc, jTextArea, Client_name).start();
            }
        }
    }

}
