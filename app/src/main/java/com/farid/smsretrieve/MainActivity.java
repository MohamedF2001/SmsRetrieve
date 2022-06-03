package com.farid.smsretrieve;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date d = new Date();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(d);
        //Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
        if(s.contains("17:19:00")){
            //Toast.makeText(this, "il est l'heure", Toast.LENGTH_SHORT).show();

        } else {
            //Toast.makeText(this, "pas encore", Toast.LENGTH_SHORT).show();
        }
    }
}