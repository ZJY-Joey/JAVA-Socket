package Client;


//192.168.197.121


public class Client_main {
    public static void main(String[] args){
        //设置GUI界面
        C_MyGUI myGui = new C_MyGUI();
        //设置监视器
        C_MySurveillanceForConnect MySurveillanceForConnect = new C_MySurveillanceForConnect();
        C_MySurveillanceForSend mySurveillanceForSend = new C_MySurveillanceForSend();
        myGui.setMyCommandSurveillanceForConnect(MySurveillanceForConnect);
        myGui.setMyCommandSurveillanceForSend(mySurveillanceForSend);
    }
}
