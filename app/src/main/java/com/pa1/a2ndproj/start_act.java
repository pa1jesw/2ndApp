package com.pa1.a2ndproj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class start_act extends AppCompatActivity {
    Animation am;
    ImageView ivbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start_act);

        ivbg = (ImageView) findViewById(R.id.ivbg);
        am= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate);
        ivbg.startAnimation(am);


        new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException ie)
                {
                    ie.getStackTrace();
                }

                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        }).start();



    }
}
