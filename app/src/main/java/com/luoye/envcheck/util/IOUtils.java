package com.luoye.envcheck.util;

import java.io.BufferedReader;
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
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
