package keywordproxy;

import client.RpcImporter;
import server.IRpcService;
import server.RpcServiceImpl;

import java.net.InetSocketAddress;

public class KeyWordImpl {
     // 创建客户端服务代理类
     final static  RpcImporter<IRpcService> importer = new RpcImporter<IRpcService>();
     final static    IRpcService iRpcService = importer.importer(RpcServiceImpl.class, new InetSocketAddress("localhost", 8889));

     public String logMsg() {
         iRpcService.logMsg();
        return "No Argument";
    }

    public String logMsg(String msg) {
         iRpcService.logMsg(msg);
        System.out.println("With Arguments log");
        return "With Argument >>>" + msg;
    }

    public void home() {
         iRpcService.home();
        System.out.println("回到主页");
    }

    public void back() {
         iRpcService.back();
        System.out.println("返回上一界面");

    }

    public void openNotification() {
         iRpcService.openNotification();
        System.out.println("打开通知");
    }


    public void click(String x, String y) {
         iRpcService.click(Integer.parseInt(String.valueOf(x)), Integer.parseInt(String.valueOf(y)));
        System.out.println("点击坐标(" + x + ", " + y + ")");
    }

    public boolean returnTrue(){
         return true;
    }
    public boolean returnFalse(){
         return false;
    }
    public String returnString(){
         return "我是返回值";
    }

}
