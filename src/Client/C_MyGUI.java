package Client;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;

import static java.awt.Font.ITALIC;


//设置页面 添加监视器
public class C_MyGUI extends JFrame {
    JFrame jFrame = new JFrame();
    JTextField textF_IP, textF_port, textF_send;
    JButton btn_Connect, btn_send;
    JTextArea textArea;
    JPanel jPanel01, jPanel02, jPanel03, jPanel;
    //监视器接口
    C_MyCommandSurveillanceForSend myCommandSurveillanceForSend;
    C_MyCommandSurveillanceForConnect myCommandSurveillanceForConnect;
    //一个对象
    Socket client;
    //构造函数完成窗口的主流操作
    public C_MyGUI(){
        client = new Socket();
        init();
        jFrame.setResizable(false);
        jFrame.setBounds(100,100,460,360);
        jFrame.setTitle("客户端");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void init(){
        //服务器设置框
        jPanel = new JPanel();
        jPanel01 = new JPanel();
        jPanel02 = new JPanel();
        jPanel03 = new JPanel();
        textF_IP = new JTextField(10);
        textF_IP.setFont(new Font(null, Font.BOLD, 13));
        textF_port = new JTextField(8);
        textF_port.setFont(new Font(null, Font.BOLD, 13));
        btn_Connect = new JButton("Connect");
        jPanel01.add(new JLabel("Server IP: "));
        jPanel01.add(textF_IP);
        jPanel01.add(new JLabel("Port: "));
        jPanel01.add(textF_port);
        jPanel01.add(btn_Connect);
        jPanel01.setBorder(BorderFactory.createTitledBorder("客户机设置："));
        //中间文本框
        textArea = new JTextArea(9,30);
        textArea.setLineWrap(true);
        textArea.setFont(new Font(null, Font.BOLD+ITALIC, 15));
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setViewportView(textArea);
        jPanel02.add(jScrollPane);
        //send发送信息栏
        textF_send = new JTextField(23);
        textF_send.setFont(new Font(null, Font.BOLD, 13));
        btn_send = new JButton("Send");
        jPanel03.add(new JLabel("Send: "));
        jPanel03.add(textF_send);
        jPanel03.add(btn_send);

        //将上中下三个组件存放如jPanel
        jPanel.add(jPanel01,BorderLayout.NORTH);
        jPanel.add(jPanel02,BorderLayout.CENTER);
        jPanel.add(jPanel03,BorderLayout.SOUTH);
        jFrame.setContentPane(jPanel);
    }

    //设置监视器
    void setMyCommandSurveillanceForConnect(C_MyCommandSurveillanceForConnect myCommandSurveillanceForConnect){
        //利用组合设置对象
        this.myCommandSurveillanceForConnect = myCommandSurveillanceForConnect;
        myCommandSurveillanceForConnect.setJTextField_Port(textF_port);
        myCommandSurveillanceForConnect.setJTextField_IP(textF_IP);
        myCommandSurveillanceForConnect.setJTextArea(textArea);
        myCommandSurveillanceForConnect.setSocket(client);
        myCommandSurveillanceForConnect.setBtn(btn_Connect);
        //为事件源添加监视器  用户点击按钮才能够触发事件
        textF_port.addActionListener(myCommandSurveillanceForConnect);
        textF_IP.addActionListener(myCommandSurveillanceForConnect);
        btn_Connect.addActionListener(myCommandSurveillanceForConnect);
    }
    void setMyCommandSurveillanceForSend(C_MyCommandSurveillanceForSend myCommandSurveillanceForSend){
        this.myCommandSurveillanceForSend = myCommandSurveillanceForSend;
        myCommandSurveillanceForSend.setJTextField(textF_send);
        myCommandSurveillanceForSend.setSocket(client);
        //为事件源添加监视器
        textF_send.addActionListener(myCommandSurveillanceForSend);
        btn_send.addActionListener(myCommandSurveillanceForSend);
    }


}
