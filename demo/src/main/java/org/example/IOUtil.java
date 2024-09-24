package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//读入写出工具
public class IOUtil {
    public static List<String> read(String Path) {
        List<String> txt=new ArrayList<>();
        String strLine;
        // 读入输入路径的文件
        File file = new File(Path);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            //设定编码规范为utf-8
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            //缓冲
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            // 从缓冲区读出
            while ((strLine = bufferedReader.readLine()) != null) {
                txt .add( strLine);
            }
            // 关闭文本
            inputStreamReader.close();
            bufferedReader.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return txt;
    }

    //将结果写到对应文件
    public static void write(List<String> res, String path) throws IOException {
        File file = new File(path);
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(file, true);
        FileWriter finalFileWriter = fileWriter;
        res.forEach(txt->{
            try {
                finalFileWriter.write(txt, 0, txt.length());
                finalFileWriter.write("\r\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fileWriter.close();
    }
}
