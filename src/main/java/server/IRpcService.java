package server;

public interface IRpcService {
    void home() throws Exception;
    void back() throws Exception;
    void openNotification();

    void clickByCoordinate(int x, int y) throws Exception;
    void clickById(String resourceId) throws Exception;
    void clickByText(String text) throws Exception;
    void clickByTextContains(String text) throws Exception;

    void verifyTextExists(String text) throws Exception;
    void verifyTextNotExists(String text) throws Exception;

    void verifyEleExists(String resourceId) throws Exception;
    void verifyEleNotExists(String resourceId) throws Exception;

}
