package com.luoye.xposedcheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.luoye.xposedcheck.util.Checker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tv_msg);
        Checker checker = new Checker();
        StringBuilder content = new StringBuilder();
        content.append("> Xposed加载类 : " + (checker.checkXposedClass() ? "存在" : "不存在") + "\n");
        content.append("> Xposed日志 : " + (checker.checkXposedLog() ? "存在" : "不存在") + "\n");
        content.append("> XposedBridge.jar : " + (checker.checkXposedBridgeJar() ? "存在" : "不存在") + "\n");
        tv.setText(content);
    }
}
