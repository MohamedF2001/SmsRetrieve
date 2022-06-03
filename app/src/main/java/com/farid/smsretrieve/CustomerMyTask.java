package com.farid.smsretrieve;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CustomerMyTask extends TimerTask {
    ListView listView;
    private static final int PERMISSION_REQUEST_READ_CONTACTS = 100;
    ArrayList smsList;
    private MediaPlayer mediaPlayer;
    Context ctxObject = null;
    Handler handler;
    /*public CustomerMyTask(Context ctx) {
        ctxObject = ctx;
    } */
    @Override
    public void run() {
        handler = new Handler();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String texte_date = sdf.format(new Date());
        Log.d("hh:mm : ", texte_date);
        System.out.println("Trois secondes");
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mediaPlayer.start();
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,0,2000);
        /*
        if (texte_date.contains("12:35")){
            System.out.println("Il est l heure");
        } */

        /*Intent gpsintent = new Intent(ctxObject, CronService.class);
        ctxObject.startService(gpsintent);*/
        //Intent intent = getIntent();
        /*String str = "";
        if (intent.hasExtra("edittext")){ // vérifie qu'une valeur est associée à la clé “edittext”
            str = intent.getStringExtra("edittext"); // on récupère la valeur associée à la clé
        } */
        //System.out.println(texte_date);
        //MyService s = new MyService();
        //s.onCreate();
    }


}
