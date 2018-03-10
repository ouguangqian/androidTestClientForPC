package keyword;


import keywordproxy.KeyWordImpl;
import server.MoveDesk;

import java.util.HashMap;
import java.util.Map;

public class RegisterCenter {
    public static Map<String, Object> OBJ_POOLS = new HashMap<String, Object>();
    public static Map<String, Map<String, Object>> KEYWORD_POOLS = new HashMap<String, Map<String, Object>>();

    static {
        // 对象池
        OBJ_POOLS.put(MoveDesk.class.getName(), new MoveDesk());
        OBJ_POOLS.put(KeyWordImpl.class.getName(), new KeyWordImpl());
        // 关键字池

        KEYWORD_POOLS.put("click_by_coordinate", methodInfo(KeyWordImpl.class.getName(), "clickByCoordinate", new Class[]{String.class, String.class}));
        KEYWORD_POOLS.put("click_by_text", methodInfo(KeyWordImpl.class.getName(), "clickByText", new Class[]{String.class}));
        KEYWORD_POOLS.put("click_by_id", methodInfo(KeyWordImpl.class.getName(), "clickById", new Class[]{String.class}));
        KEYWORD_POOLS.put("click_by_text_contains", methodInfo(KeyWordImpl.class.getName(), "clickByTextContains", new Class[]{String.class}));
        KEYWORD_POOLS.put("verify_text_exists", methodInfo(KeyWordImpl.class.getName(), "verifyTextExists", new Class[]{String.class}));
        KEYWORD_POOLS.put("verify_text_not_exists", methodInfo(KeyWordImpl.class.getName(), "verifyTextNotExists", new Class[]{String.class}));
        KEYWORD_POOLS.put("verify_ele_exists", methodInfo(KeyWordImpl.class.getName(), "verifyEleExists", new Class[]{String.class}));
        KEYWORD_POOLS.put("verify_ele_not_exists", methodInfo(KeyWordImpl.class.getName(), "verifyEleNotExists", new Class[]{String.class}));

        KEYWORD_POOLS.put("sleep", methodInfo(KeyWordImpl.class.getName(), "sleep", new Class[]{String.class}));

        KEYWORD_POOLS.put("home", methodInfo(KeyWordImpl.class.getName(), "home", new Class[]{}));
        KEYWORD_POOLS.put("returnFalse", methodInfo(KeyWordImpl.class.getName(), "returnFalse", new Class[]{}));
        KEYWORD_POOLS.put("returnTrue", methodInfo(KeyWordImpl.class.getName(), "returnTrue", new Class[]{}));
        KEYWORD_POOLS.put("returnString", methodInfo(KeyWordImpl.class.getName(), "returnString", new Class[]{}));


        KEYWORD_POOLS.put("张三", methodInfo(MoveDesk.class.getName(), "setZhangsan",new Class[]{}));
        KEYWORD_POOLS.put("把桌子从A地点搬到B地点", methodInfo(MoveDesk.class.getName(), "moveDeskFromA2B",new Class[]{String.class, String.class}));
    }


    public static Map<String, Object> methodInfo(String className, String methodName, Class<?>... paramTypes){
        Map<String,Object> methodInfo = new HashMap<String, Object>();
        methodInfo.put("class", className);
        methodInfo.put("method", methodName);
        methodInfo.put("paramTypes", paramTypes);
        return methodInfo;
    }
}
