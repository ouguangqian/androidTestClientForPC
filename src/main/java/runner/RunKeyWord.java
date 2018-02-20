package runner;


import keyword.RegisterCenter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class RunKeyWord {
    public static Object runKeyWord(String keyWord, Object... args) throws NoSuchMethodException {

        Map<String, Object> keyMethod = RegisterCenter.KEYWORD_POOLS.get(keyWord);
        String className = (String) keyMethod.get("class");
        String methodName = (String) keyMethod.get("method");
        Object paramTypes = keyMethod.get("paramTypes");

        Object object = RegisterCenter.OBJ_POOLS.get(className);

        Method method = null;
        if (paramTypes == null){
            method = object.getClass().getMethod(methodName);
        }else {
            method = object.getClass().getMethod(methodName, (Class[])paramTypes);
        }
        try {
            if (args != null) {
               return method.invoke(object, args);
            }else {
               return method.invoke(object);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static Method getMethod(String methodName, Object object){
        Method[] methods = object.getClass().getMethods();
        for (Method method: methods){
            if (method.getName().equals(methodName)){
                return method;
            }
        }
        return null;
    }
}
