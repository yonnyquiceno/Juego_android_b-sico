package com.edu4java.android.killthemall.killthemall_trainingv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }
}
