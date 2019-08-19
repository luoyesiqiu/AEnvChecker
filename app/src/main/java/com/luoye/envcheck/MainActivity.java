package com.luoye.envcheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.luoye.envcheck.interfaces.Checker;
import com.luoye.envcheck.util.RootChecker;
import com.luoye.envcheck.util.XposedChecker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tv_msg);
        XposedChecker xposedChecker = new XposedChecker();
        StringBuilder content = new StringBuilder();
        content.append("> Xposed可疑项 : " + xposedChecker.getResult() + "\n");
        Checker rootChecker = new RootChecker();
        content.append("> Root可疑项 : " + rootChecker.getResult() + "\n");
        tv.setText(content);
    }
}
