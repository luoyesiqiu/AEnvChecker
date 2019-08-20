package com.luoye.envcheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.luoye.envcheck.bean.Pair;
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
        Pair<Integer, Integer> xposedPair = xposedChecker.getResult();
        content.append("> Xposed可疑项 : [" + xposedPair.getFirst() + "/" + xposedPair.getSecond() + "]" + "\n");
        Checker rootChecker = new RootChecker();
        Pair<Integer, Integer> rootPair = rootChecker.getResult();

        content.append("> Root可疑项 : [" + rootPair.getFirst() + "/" + rootPair.getSecond() + "]" + "\n");
        tv.setText(content);
    }
}
