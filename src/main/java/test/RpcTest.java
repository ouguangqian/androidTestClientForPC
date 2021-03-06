package test;

import client.RpcImporter;
import exporter.RpcExporter;
import server.IRpcService;
import server.RpcServiceImpl;

import java.net.InetSocketAddress;

public class RpcTest {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    RpcExporter.exporter("localhost", 8899);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        // 创建客户端服务代理类
//        RpcImporter<IRpcService> importer = new RpcImporter<IRpcService>();
//        IRpcService iRpcService = importer.importer(RpcServiceImpl.class, new InetSocketAddress("localhost", 8889));
//        System.out.println(iRpcService.logMsg());
//        System.out.println(iRpcService.logMsg("ouguangqian"));
//        iRpcService.home();
//        Thread.sleep(5000);
//        iRpcService.click(550, 560);
//        Thread.sleep(5000);
//        iRpcService.back();
//        Thread.sleep(5000);
//        iRpcService.openNotification();
//        Thread.sleep(5000);
//        iRpcService.home();
//        Thread.sleep(5000);
    }
}
