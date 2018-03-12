package runner;



import context.Context;
import keywordproxy.KeyWordImpl;
import logger.LogWriter;
import utils.ImgUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RunStep {

    private final static LogWriter logger = new LogWriter();

    public static void runStep(String step) throws Exception {
        List<String> stepToList = Arrays.asList(step.split("\\s\\s+"));
        System.out.println("执行步骤信息========> " + step);
        // 判断是否条件判断
        try {

            if (step.toLowerCase().startsWith("if") || step.toLowerCase().startsWith("unless")) {
                runIfUnlessStep(step);
            } else {
                String keyWord = stepToList.get(0);
                if (stepToList.contains("=>")) {
                    int retIdx = stepToList.indexOf("=>");
                    String result = stepToList.get(retIdx + 1);
                    List<String> keyWordParams = stepToList.subList(1, retIdx);
                    Object[] paramsArr = (Object[]) keyWordParams.toArray();

                    Context.CONTEXTMAP.put(result, RunKeyWord.runKeyWord(keyWord, paramsArr));
                } else {
                    Object[] paramsArr = null;
                    if (stepToList.size() > 1) {
                        List<String> keyWordParams = stepToList.subList(1, stepToList.size());
                        paramsArr = (Object[]) keyWordParams.toArray();
                    }
                    RunKeyWord.runKeyWord(keyWord, paramsArr);
                }
            }
            logger.logReport("step:" + step, "passed");
        }catch (Exception e){
            e.printStackTrace();
            // 获取logcat日志信息
            try{
                new LogWriter().logLogcatInfo();
            }catch (Exception e1){
                e1.printStackTrace();
            }
            // 获取截图信息
            try {
                String pngName = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date()) + ".png";
                byte[] b = new KeyWordImpl().takeScreenshot(pngName);
                ImgUtils.buff2Image(b, Context.reportPath + "/" + pngName);

                logger.logReport("step:" + step, "failed");
                logger.logReport("error:请参考截图信息" + Context.reportPath + "/" + pngName);

            }catch (Exception e1){
                e1.printStackTrace();
                logger.logReport("step:" + step, "failed");
            }
            throw new Exception(e);
        }

    }

    private static void runIfUnlessStep(String step) throws Exception {
        List<String> stepToList = Arrays.asList(step.split("\\s\\s+"));
        String ifKeyWord = stepToList.get(1);
        List<String> ifKeyWordParams = stepToList.subList(2, stepToList.indexOf("runKeyWord"));
        Object[] ifParamsArr = (Object[]) ifKeyWordParams.toArray();
        boolean ret = (Boolean) RunKeyWord.runKeyWord(ifKeyWord, ifParamsArr);

        boolean flag = (step.toLowerCase().contains("if") == ret);
        if (flag){
            String newStep = step.substring(step.indexOf("runKeyWord") + "runKeyWord".length()).trim();
            runStep(newStep);
        }
    }
    public static void main(String[] args){
        String step = "if  returnTrue  runKeyWord      returnString  =>  ret1";

        String newStep = step.substring(step.indexOf("runKeyWord") + "runKeyWord".length()).trim();
        System.out.println(newStep);

    }

}
