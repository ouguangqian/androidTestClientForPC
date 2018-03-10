package server;

import java.util.Map;

public class RpcServiceImpl implements IRpcService {


    public void home() {
        System.out.println("回到主页");
    }

    public void back() {
        System.out.println("返回上一界面");

    }

    public void openNotification() {
        System.out.println("打开通知");
    }

    public void clickByCoordinate(int x, int y) throws Exception {
        System.out.println("点击坐标 => " + x + ", " + y);

    }

    public void clickById(String resourceId) throws Exception {
        System.out.println("点击ID => " + resourceId);

    }

    public void clickByText(String text) throws Exception {
        System.out.println("点击文本 => " + text);

    }

    public void clickByTextContains(String text) throws Exception {
        System.out.println("点击包含文本 => " + text);

    }

    public void verifyTextExists(String text) throws Exception {
        System.out.println("验证文本存在 => " + text);

    }

    public void verifyTextNotExists(String text) throws Exception {
        System.out.println("验证文本不存在 => " + text);

    }

    public void verifyEleExists(String resourceId) throws Exception {
        System.out.println("验证元素存在 => " + resourceId);

    }

    public void verifyEleNotExists(String resourceId) throws Exception {
         System.out.println("验证元素不存在 => " + resourceId);

    }


}
