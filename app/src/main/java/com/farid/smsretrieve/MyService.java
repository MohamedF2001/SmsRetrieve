package com.farid.smsretrieve;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyService extends Service {
    ListView listView;
    private static final int PERMISSION_REQUEST_READ_CONTACTS = 100;
    ArrayList smsList;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate(){
        super.onCreate();
        Intent i = new Intent(getApplicationContext(), CronService.class);
        startService(i);
        // Create MediaPlayer object, to play your song.
        //showContacts();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        // Play song.

        return START_STICKY;
    }

    // Destroy
    @Override
    public void onDestroy() {
        // Release the resources

    }
}