package test;

import context.Context;
import runner.Runner;

import java.text.SimpleDateFormat;
import java.util.Date;

public class KeyWordRunner {

    public static void main(String[] args) throws Exception {
        // 设置报告路径
        String timeStr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        Context.reportPath = System.getProperty("user.home") + "/performReport" + "/" + timeStr;

        Runner.Runner("/home/pateo/apps/BDD/androidTestClientForPC-master/src/main/resources/case2.atm");

        System.out.println(Context.CONTEXTMAP);
    }
}
