package com.farid.smsretrieve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Anime extends AppCompatActivity {
    ImageView imageView;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        imageView = findViewById(R.id.imageview);

        handler = new Handler();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // To add rotate animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_animation);
                imageView.startAnimation(animation);
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,0,12000);

        execution();
    }
    private void execution() {
        Intent myIntent = new Intent(Anime.this, MyService.class);
        this.startService(myIntent);
    }
}