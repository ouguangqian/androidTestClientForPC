package logger;


import context.Context;
import utils.Command;
import utils.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class LogWriter {

    public void logReport(String step, String status){
        String reportPath = Context.reportPath + "/report.log";
        File file = new File(reportPath);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(reportPath, true);
            fileWriter.write(step);
            if (status != null) {
                fileWriter.write("  ------  ");
                fileWriter.write(status);
            }

            fileWriter.write("\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            if (fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }



    public void logReport(String step){
        System.out.println("-----------------------" + step);
        logReport(step, null);
    }

    /**
     * 写入cpu信息
     */
    public void logCpuInfo() {
        String cpuPath =Context.reportPath + "/cpu.log";
        String cmdStr = "adb shell top -n 1";

        String cpuInfo = null;
        try {
            cpuInfo = Command.shell(cmdStr);
            System.out.println("cpuInfo========>" + cpuInfo);
            FileUtil.saveInfoToFile(cpuPath, cpuInfo, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 写入procrank信息
     */
    public void logProcrankInfo() {
        String procrankPath =Context.reportPath + "/procrank.log";
        String cmdStr = "adb shell procrank";
        String procrankInfo = null;
        try {
            procrankInfo = Command.shell(cmdStr);
            System.out.println("procrank========>" + procrankInfo);
            FileUtil.saveInfoToFile(procrankPath, procrankInfo, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入logcat信息
     */
    public void logLogcatInfo() {
        String logcatPath =Context.reportPath + "/logcat.log";
        String cmdStr = "adb logcat -v time -d";
        String logcatInfo = null;
        try {
            logcatInfo = Command.shell(cmdStr);
            FileUtil.saveInfoToFile(logcatPath, logcatInfo, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
