package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Command {

    public static String shell(String cmdStr) throws Exception{
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = null;
        String line = null;
        Process p = null;

        try {
            // read output from logcat
            p = Runtime.getRuntime().exec(cmdStr);
            reader = new BufferedReader(
                    new InputStreamReader(p.getInputStream()));

            stringBuilder.setLength(0);
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }

            System.out.println(stringBuilder.toString());
            Thread.sleep(10000);
            reader.close();

            // read error from logcat
            StringBuilder errorLog = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(
                    p.getErrorStream()));
            errorLog.append("logcat returns error: ");
            while ((line = reader.readLine()) != null) {
                errorLog.append(line);
            }
            reader.close();

            // Exception would be thrown if we get exitValue without waiting for the process
            // to finish
            p.waitFor();

            // if exit value of logcat is non-zero, it means error
            if (p.exitValue() != 0) {
                p.destroy();
                reader.close();
                destroy(p, reader);

                throw new Exception(errorLog.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (p!=null) p.destroy();
        if (reader != null) reader.close();
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    /**
     * Destroys the process and closes the BufferedReader.
     *
     * @param p the process to destroy
     * @param reader the BufferedReader to close
     */

    public static void destroy(Process p, BufferedReader reader){
        p.destroy();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
