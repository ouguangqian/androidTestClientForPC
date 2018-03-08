package server;

import java.util.Map;

public interface IRpcService {
    String logMsg();
    String logMsg(String msg);
    void home();
    void back();
    void openNotification();
    void click(int x, int y);
}
