package com.luoye.envcheck.util;

import android.util.Log;

import com.luoye.envcheck.bean.Pair;
import com.luoye.envcheck.interfaces.Checker;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class RootChecker implements Checker {
    private final String TAG = RootChecker.class.getSimpleName();
    private final String SU_NAME = "su";
    private String[] binPaths = {"/system/bin/", "/system/xbin/"};
    private String[] suBinPaths = {binPaths[0] + SU_NAME, binPaths[1] + SU_NAME};
    private String[] rootPropKey = {"ro.secure", "ro.adb.secure"};

    private boolean checkSuBin() {
        int suCount = 0;
        for (String suPath : suBinPaths) {
            if (new File(suPath).exists()) {
                suCount++;
            }
        }
        return suCount != 0;
    }

    private boolean checkRootProp() {
        int propCount = 0;
        for (String key : rootPropKey) {
            String val = ShellExec.exec("getprop " + key);
            if (val != null && val.equals("0")) {
                propCount++;
            }
        }
        return propCount != 0;
    }

    private boolean checkFilePerm() {
        for (String binPath : binPaths) {
            String text = ShellExec.exec("ls -l " + binPath);
            List<String> list = IOUtils.stringToArray(text);
            for(String line : list){
                String[] blocks = line.split(" ");
                if(blocks[0].contains("s")){
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public Pair getResult() {
        boolean[] results = {checkSuBin(), checkRootProp(), checkFilePerm()};
        int count = 0;
        for (boolean ret : results) {
            if (ret) {
                count++;
            }
        }


        return new Pair(count, results.length);
    }
}
