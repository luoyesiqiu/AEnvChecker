package com.luoye.envcheck.util;

import com.luoye.envcheck.bean.Pair;
import com.luoye.envcheck.interfaces.Checker;

import java.io.File;

public class XposedChecker implements Checker {
    private static final String TAG = XposedChecker.class.getSimpleName();

    /**
     * 检测内存中是否加载了Xposed
     *
     * @return
     */
    private boolean checkXposedClass() {
        try {
            Class.forName("de.robv.android.xposed.XposedHelpers");
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 检查日志,是否存在Xposed
     *
     * @return
     */
    private boolean checkXposedLog() {
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
     *
     * @return
     */
    private boolean checkXposedBridgeJar() {
        File f = new File("/system/framework/XposedBridge.jar");
        if (f.exists()) {
            return true;
        }
        return false;
    }

    @Override
    public Pair getResult() {
        boolean[] results = {checkXposedClass(), checkXposedBridgeJar(), checkXposedLog()};
        int count = 0;
        for (boolean ret : results) {
            if (ret) {
                count++;
            }
        }

        return new Pair(count, results.length);
    }
}
