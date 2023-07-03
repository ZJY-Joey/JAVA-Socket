package Server;


//main类仅仅起一个组织协调的作用

public class Server_main {
    public static void main(String[] args){
        //设置GUI界面
        S_MyGUI myGui = new S_MyGUI();
        //添加监视器，利用组合调用方法
        S_MySurveillanceForStart mySurveillanceForStart = new S_MySurveillanceForStart();
        S_MySurveillanceForSend mySurveillanceForSend = new S_MySurveillanceForSend();
        myGui.setMyCommandSurveillanceForStart(mySurveillanceForStart);
        myGui.setMyCommandSurveillanceForSend(mySurveillanceForSend);

    }
}
