package runner;

import context.Context;

import java.util.Arrays;
import java.util.List;

public class RunStep {
    public static void runStep(String step) throws NoSuchMethodException {
        List<String> stepToList = Arrays.asList(step.split("  "));
        System.out.println("执行步骤信息========> " + step);
        // 判断是否条件判断
        if (step.toLowerCase().startsWith("if") || step.toLowerCase().startsWith("unless")){
            runIfUnlessStep(step);
        }else{
            String keyWord = stepToList.get(0);
            if (stepToList.contains("=>")){
                    int retIdx = stepToList.indexOf("=>");
                    String result = stepToList.get(retIdx + 1);
                    List<String> keyWordParams = stepToList.subList(1, retIdx);
                    Object[] paramsArr = (Object[]) keyWordParams.toArray();

                    Context.CONTEXTMAP.put(result, RunKeyWord.runKeyWord(keyWord, paramsArr));
                }else {
                Object[] paramsArr  = null;
                if (stepToList.size() > 1) {
                    List<String> keyWordParams = stepToList.subList(1, stepToList.size());
                    paramsArr = (Object[]) keyWordParams.toArray();
                }
                    RunKeyWord.runKeyWord(keyWord, paramsArr);
                }
        }

    }

    private static void runIfUnlessStep(String step) throws NoSuchMethodException {
        List<String> stepToList = Arrays.asList(step.split("  "));
        String ifKeyWord = stepToList.get(1);
            List<String> ifKeyWordParams = stepToList.subList(2, stepToList.indexOf("runKeyWord"));
            Object[] ifParamsArr = (Object[]) ifKeyWordParams.toArray();
            boolean ret = (Boolean) RunKeyWord.runKeyWord(ifKeyWord, ifParamsArr);

            boolean flag = (step.toLowerCase().contains("if") == ret);
            if (flag){
                int runKeyWordIdx = stepToList.indexOf("runKeyWord");
                if (stepToList.contains("=>")){
                    int retIdx = stepToList.indexOf("=>");
                    String result;
                    result = stepToList.get(retIdx + 1);
                    String keyWord = stepToList.get(runKeyWordIdx + 1);
                    List<String> keyWordParams = stepToList.subList(runKeyWordIdx + 2, retIdx);
                    Object[] paramsArr = (Object[]) keyWordParams.toArray();
                    Context.CONTEXTMAP.put(result, RunKeyWord.runKeyWord(keyWord, paramsArr));
                }else {
                     String keyWord = stepToList.get(runKeyWordIdx + 1);
                    List<String> keyWordParams = stepToList.subList(runKeyWordIdx + 2, stepToList.size());
                    Object[] paramsArr = (Object[]) keyWordParams.toArray();
                    RunKeyWord.runKeyWord(keyWord, paramsArr);
                }
            }
    }


}
