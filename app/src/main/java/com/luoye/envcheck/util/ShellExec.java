package com.luoye.envcheck.util;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ShellExec {
    private static String[] envp = {"/sbin", "/vendor/bin", "/system/sbin", "/system/bin", "/system/xbin"};

    public static String exec(String cmd) {
        StringBuilder sb = new StringBuilder();
        //Please use char buffer
        BufferedReader in = null;
        BufferedReader err = null;
        try {
            Process process = Runtime.getRuntime().exec(cmd, envp);
            in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            err = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line+"\n");
            }
            while ((line = err.readLine()) != null) {
                sb.append(line+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.tryClose(in);
            IOUtils.tryClose(err);
        }
        return sb.toString();
    }
}
