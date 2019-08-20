package com.luoye.envcheck.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {
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
