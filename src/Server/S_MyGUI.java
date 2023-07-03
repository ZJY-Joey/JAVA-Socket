package Server;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;
import java.util.Vector;
import static java.awt.Font.ITALIC;

//设置GUI界面 添加监视器 + 建立通信
public class S_MyGUI extends JFrame {
    //组件
    static Vector<String> socket_name;
    static Vector<Socket> scs;
    JFrame jFrame = new JFrame();
    JTextField textF_port, textF_send;
    JButton btn_start, btn_send;
    JTextArea textArea;
    JPanel jPanel01;
    JPanel jPanel02;
    JPanel jPanel03;
    JPanel jPanel;

    JComboBox<String> comboBox;
    //监视器接口
    S_MyCommandSurveillanceForStart myCommandSurveillanceForStart;
    S_MyCommandSurveillanceForSend myCommandSurveillanceForSend;
    //对象

    //构造函数完成窗口的主流操作
    public S_MyGUI(){
        socket_name = new Vector<>();
        scs = new Vector<>();
        socket_name.add("null");
        init();
        jFrame.setResizable(false);
        jFrame.setBounds(100,100,460,360);
        jFrame.setTitle("服务器");
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    void init(){
        //服务器设置框
        jPanel = new JPanel();
        jPanel01 = new JPanel();
        jPanel02 = new JPanel();
        jPanel03 = new JPanel();
        textF_port = new JTextField(23);
        textF_port.setFont(new Font(null, Font.BOLD, 13));
        btn_start = new JButton("Start");
        jPanel01.add(new JLabel("Port: "));
        jPanel01.add(textF_port);
        jPanel01.add(btn_start);
        jPanel01.setBorder(BorderFactory.createTitledBorder("服务器设置："));
        //中间文本框
        textArea = new JTextArea(9,30);
        textArea.setLineWrap(true);
        textArea.setFont(new Font(null, Font.BOLD+ITALIC, 15));
        JScrollPane jScrollPane = new JScrollPane();
        JScrollBar jscrollBar = jScrollPane.getVerticalScrollBar();
        if (jscrollBar != null) {
            // 必须先获取一次jScrollBar.getMaximum()，否则滚动不到最底部, swing bug
            jscrollBar.setValue(jscrollBar.getMaximum());
            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        }
        jScrollPane.setViewportView(textArea);
        jPanel02.add(jScrollPane);
        //send发送信息栏
        textF_send = new JTextField(18);
        comboBox = new JComboBox<>(socket_name);
        textF_send.setFont(new Font(null, Font.BOLD, 13));
        btn_send = new JButton("Send");
        jPanel03.add(new JLabel("SendTo: "));
        jPanel03.add(comboBox);
        jPanel03.add(textF_send);
        jPanel03.add(btn_send);

        //将上中下三个组件存放如jPanel
        jPanel.add(jPanel01,BorderLayout.NORTH);
        jPanel.add(jPanel02,BorderLayout.CENTER);
        jPanel.add(jPanel03,BorderLayout.SOUTH);
        jFrame.setContentPane(jPanel);

    }

    //设置监视器
    void setMyCommandSurveillanceForStart(S_MyCommandSurveillanceForStart myCommandSurveillanceForStart){
        //利用组合设置对象
        this.myCommandSurveillanceForStart = myCommandSurveillanceForStart;
        myCommandSurveillanceForStart.setJTextField(textF_port);
        myCommandSurveillanceForStart.setJTextArea(textArea);
        myCommandSurveillanceForStart.setJBtn(btn_start);
        //为事件源添加监视器  用户按回车键、按钮都能够触发事件
        textF_port.addActionListener(myCommandSurveillanceForStart);
        btn_start.addActionListener(myCommandSurveillanceForStart);
    }
    void setMyCommandSurveillanceForSend(S_MyCommandSurveillanceForSend myCommandSurveillanceForSend){
        this.myCommandSurveillanceForSend = myCommandSurveillanceForSend;
        myCommandSurveillanceForSend.setJTextField(textF_send);
        myCommandSurveillanceForSend.setJComboBox(comboBox);
        //为事件源添加监视器
        textF_send.addActionListener(myCommandSurveillanceForSend);
        btn_send.addActionListener(myCommandSurveillanceForSend);
    }

}
