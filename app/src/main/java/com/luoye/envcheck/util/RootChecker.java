package com.luoye.envcheck.util;

import com.luoye.envcheck.interfaces.Checker;

import java.io.File;

public class RootChecker implements Checker {
    private final  String TAG = RootChecker.class.getSimpleName();
    private final String SU_NAME = "su";
    private String[] suBinPaths = {"/system/bin/" + SU_NAME, "/system/xbin/" + SU_NAME};
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
            String val = System.getProperty(key);
            if (val!=null && val.equals("0")) {
                propCount++;
            }
        }
        return propCount !=0;
    }

    @Override
    public String getResult() {
        boolean[] results = {checkSuBin(), checkRootProp()};
        int count = 0;
        for (boolean ret : results) {
            if (ret) {
                count++;
            }
        }


        return String.format("[%d/%d]", count, results.length);
    }
}
