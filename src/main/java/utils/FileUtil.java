package utils;

import java.io.*;
import java.util.*;

public class FileUtil {
    static List<String> fileList = new ArrayList<String>();
    /**
     * 用例文件转用例列表
     * @param filePath
     * @return
     * @throws IOException
     */
    public static List<String > caseToList(String filePath) throws IOException {
        List<String> caseList = new ArrayList<String>();
        File caseFile = new File(filePath);

        // 判断文件是否存在
        if (!caseFile.isFile() || !caseFile.exists()){
            throw new FileNotFoundException("用例文件《 " + filePath + "》不存在！");
        }

        // 读取文件
        BufferedReader bufferedReader = new BufferedReader(new FileReader(caseFile));
        String line = null;
        while ((line = bufferedReader.readLine()) != null){
            caseList.add(line);
        }
        return caseList;
    }

    public static List<String> pathToFileList(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()){
            throw  new FileNotFoundException("文件不存在");
        }


        if (file.isFile()){
            if (!filePath.endsWith(".atm")){
             throw  new Exception("文件格式不正确");
            }
            fileList.add(filePath);
        }else if (file.isDirectory()){
            String[] files = file.list();
            for (String f: files){
                String path = filePath + File.separator + f;
                pathToFileList(path);
            }
        }
        return fileList;
    }

    public static List<Map<String, List<String>>> fileToCaseMap(String filePath) throws Exception {
        List<Map<String, List<String>>> caseLists = new ArrayList<Map<String,List<String>>>();
        List<String> fileList = pathToFileList(filePath);
        for (String file: fileList) {
            List<String> caseList = caseToList(file);
            Map<String, List<String>> map = new HashMap<String, List<String>>();
            map.put("file", Arrays.asList(file));
            map.put("case", caseList);
            caseLists.add(map);
        }
        return caseLists;
    }

    public static boolean saveInfoToFile(String filePath, String info, boolean append){
        FileWriter writer = null;
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            writer = new FileWriter(filePath, append);
            writer.write(info);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        List<String> aa = pathToFileList("E:\\IdeaProjects\\fortest\\src\\main\\resources");
        System.out.println(aa);
    }
}
