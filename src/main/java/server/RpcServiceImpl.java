package server;

public class RpcServiceImpl implements IRpcService {
    public String logMsg() {
        System.out.println("No Arguments log");
        return "No Argument";
    }

    public String logMsg(String msg) {
        System.out.println("With Arguments log");
        return "With Argument >>>" + msg;
    }

    public void home() {
        System.out.println("回到主页");
    }

    public void back() {
        System.out.println("返回上一界面");

    }

    public void openNotification() {
        System.out.println("打开通知");
    }


    public void click(int x, int y) {
        System.out.println("点击坐标(" + x + ", " + y + ")");
    }
}
