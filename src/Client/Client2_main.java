package Client;

public class Client2_main {
    public static void main(String[] args){
        //设置GUI界面
        C_MyGUI myGui2 = new C_MyGUI();
        //设置监视器
        C_MySurveillanceForConnect MySurveillanceForConnect2 = new C_MySurveillanceForConnect();
        C_MySurveillanceForSend mySurveillanceForSend2 = new C_MySurveillanceForSend();
        myGui2.setMyCommandSurveillanceForConnect(MySurveillanceForConnect2);
        myGui2.setMyCommandSurveillanceForSend(mySurveillanceForSend2);

    }
}
