package com.example.user.ppking_android_class08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        GameView gameView = new GameView(this);
//        setContentView(gameView);
        setContentView(R.layout.activity_main);
        myView = (MyView)findViewById(R.id.myView);
    }

    @Override
    public void finish() {
        if(myView.timer!=null) {
            myView.timer.purge();
            myView.timer.cancel();
            myView.timer = null;
        }
        super.finish();
    }
}
