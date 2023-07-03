package Server;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import static Server.S_MyGUI.scs;
import static Server.S_MyGUI.socket_name;


//服务器输入流监听线程 作用：进行输入流的监听
public class ServerListenThread extends Thread{
    Socket sc;
    JTextArea jTextArea;
    DataInputStream in;
    String str;

    ServerListenThread(Socket socket, JTextArea jTextArea, String string){
        super(string);
        this.sc = socket;
        this.jTextArea = jTextArea;
        try {
            in = new DataInputStream(sc.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //后台死循环进行监听
    public void run(){
        while(true){
            try {
                str = in.readUTF();
                jTextArea.append(getName() + ": " + str + "\n");
                jTextArea.paintImmediately(jTextArea.getBounds());
            } catch (IOException e) {
                int index = scs.indexOf(sc);
                jTextArea.append(socket_name.get(index) + "disconnected...\n");
                scs.remove(index);
                socket_name.remove(index);
                if(socket_name.isEmpty()){
                    socket_name.add("null");
                }
                break;
            }

        }
    }

}
