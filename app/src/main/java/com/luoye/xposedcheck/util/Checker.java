package com.luoye.xposedcheck.util;

import android.util.Log;

import java.io.File;

public class Checker {
    private static final String TAG = Checker.class.getSimpleName();

    /**
     * 检测内存中是否加载了Xposed
     * @return
     */
    public boolean checkXposedClass() {
        try {
            Class.forName("de.robv.android.xposed.XposedHelpers");
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 检查日志,是否存在Xposed
     * @return
     */
    public boolean checkXposedLog() {
        final String XposedBridgeClassName = "de.robv.android.xposed.XposedBridge";
        final String XposedBridgeMainMethodName = "main";
        Throwable throwable = new Throwable();
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            if (stackTraceElement.getClassName().equals(XposedBridgeClassName) && stackTraceElement.getMethodName().equals(XposedBridgeMainMethodName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否存在XposedBridge.jar
     * @return
     */
    public boolean checkXposedBridgeJar() {
        File f = new File("/system/framework/XposedBridge.jar");
        if(f.exists()){
            return  true;
        }
        return false;
    }
}
