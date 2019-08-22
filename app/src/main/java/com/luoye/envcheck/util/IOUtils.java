package com.luoye.envcheck.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

    public static String readFile(String file){
        FileInputStream fileInputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buf =new byte[1024];
            int len = -1;
            while ((len = fileInputStream.read(buf)) != -1) {
                stringBuilder.append(new String(buf,0,len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            tryClose(fileInputStream);
        }
        return stringBuilder.toString();
    }

    public static List<String> stringToArray(String text){
        BufferedReader bufferedReader = null;
        ArrayList<String> list =new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new StringReader(text));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            tryClose(bufferedReader);
        }
        return list;
    }

    public static void tryClose(Closeable closeable){
        if(closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
