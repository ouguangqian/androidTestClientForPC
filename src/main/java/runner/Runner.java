package runner;

import fileutil.FileUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void Runner(String filePath) throws Exception {

        List<Map<String, List<String>>> caseLists =  FileUtil.fileToCaseMap(filePath);

        for (Map<String, List<String>> clMap: caseLists){
            List<String> cl = clMap.get("case");
            String caseName = String.valueOf(clMap.get("file").get(0));
            System.out.println(caseName);
            for (int i = 0; i < cl.size(); i++){
                if (cl.get(i).startsWith("loop")) {
                    int startIndex = i;
                    int endIndex = cl.lastIndexOf("end");
                    RunLoopStep.runLoopSteps(cl.subList(startIndex+1, endIndex), Integer.parseInt(cl.get(i).split("\\s+")[1]));
                    i = endIndex;
                }else {
                    RunStep.runStep(cl.get(i));
                }

            }
        }
    }

    public static void main(String[] args) throws Exception {
//        Runner.Runner("E:\\IdeaProjects\\KeyWordDriverRunner\\src\\main\\resources");
//        System.out.println(Context.CONTEXTMAP.get("abc"));
//        System.out.println(Context.CONTEXTMAP.get("bbc"));
//        System.out.println(Context.CONTEXTMAP.get("cbc"));
        Runner.Runner("E:\\IdeaProjects\\AndroidRpcForPc\\src\\main\\resources\\case2.atm");
    }
}
