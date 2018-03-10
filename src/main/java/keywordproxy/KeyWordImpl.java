package keywordproxy;

import client.RpcImporter;
import server.IRpcService;
import server.RpcServiceImpl;

import java.net.InetSocketAddress;

public class KeyWordImpl {
     // 创建客户端服务代理类
     final static  RpcImporter<IRpcService> importer = new RpcImporter<IRpcService>();
     final static    IRpcService iRpcService = importer.importer(RpcServiceImpl.class, new InetSocketAddress("localhost", 9000));



    public void home() throws Exception {
         iRpcService.home();
        System.out.println("回到主页");
    }

    public void back() throws Exception {
         iRpcService.back();
        System.out.println("返回上一界面");

    }

    public void openNotification() {
         iRpcService.openNotification();
        System.out.println("打开通知");
    }

    public void clickByCoordinate(String x, String y) throws Exception {
        iRpcService.clickByCoordinate(Integer.parseInt(x), Integer.parseInt(y));

    }

    public void clickById(String resourceId) throws Exception {
        iRpcService.clickById(resourceId);

    }

    public void clickByText(String text) throws Exception {
        iRpcService.clickByText(text);

    }

    public void clickByTextContains(String text) throws Exception {
        iRpcService.clickByTextContains(text);

    }

    public void verifyTextExists(String text) throws Exception {
        iRpcService.verifyTextExists(text);

    }

    public void verifyTextNotExists(String text) throws Exception {
        iRpcService.verifyTextNotExists(text);
    }

    public void verifyEleExists(String resourceId) throws Exception {
        iRpcService.verifyEleExists(resourceId);

    }

    public void verifyEleNotExists(String resourceId) throws Exception {
        iRpcService.verifyEleNotExists(resourceId);
    }

    public void sleep(String time)throws Exception{
        Thread.sleep(Integer.parseInt(time) * 1000);
        System.out.println("延时 => "  + time + "秒");
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
