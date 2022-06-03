package com.farid.smsretrieve;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
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

import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class CronService extends Service {
    private MediaPlayer mediaPlayer;
    ListView listView;
    private static final int PERMISSION_REQUEST_READ_CONTACTS = 100;
    ArrayList smsList;
    Handler handler;
    public CronService() {
        //super();
    }
/*
    @Override
    public void run() {
        Log.d("TAG", "run: true");
    }*/

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        // Create MediaPlayer object, to play your song.
        //showContacts();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.little);
        //Log.d("TAG","Vrai");
    }

    private void showContacts() {
        Uri inboxURI = Uri.parse("content://sms/inbox");
        smsList = new ArrayList();
        ContentResolver cr = getContentResolver();
        Date madate = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String strDate = dateFormat.format(madate);

        DateFormat dateFormatToday = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormatToday.format(madate);

        //Toast.makeText(this, ""+cr, Toast.LENGTH_LONG).show();

        Cursor c = cr.query(inboxURI, null, null, null, null);
        JSONArray array=new JSONArray();
        while (c.moveToNext()) {
            String Number = c.getString(c.getColumnIndexOrThrow("address")).toString();
            String Body = c.getString(c.getColumnIndexOrThrow("body")).toString();
            String Body2 = c.getString(c.getColumnIndexOrThrow("body")).toString();
            final String premierCas = "Vous avez recu un transfert de ";
            final String deuxiemCas = "Vous avez recu ";
            String leCasPrincipal = "Vous avez recu ";
            String leSecond = "un transfert de ";
            int mont = Body.length();
            //String str = Body;
            //String chiffr = getNbr(Body2);
            //smsList.add("Number: " + Number + "\n" + "Body: " + Body);
            /*if (Number.contains("MTN MoMo") && Body.contains("Vous avez recu") && Body.contains("ransaction")) {
                String BodyRetenu = Body;
                String res = "";
                res = BodyRetenu.substring(14,35);
                //Toast.makeText(this, ""+res, Toast.LENGTH_SHORT).show();
                smsList.add("Number: " + Number + "\n" + "" +"\n" +
                        "Chiffre :" + chiffr + "\n" + "" +"\n" +
                        "MONTANT :" + res + "\n" + "" +"\n" +
                        "Body: " + Body + "\n" );
            }*/

            if (Number.contains("MTN MoMo") && Body.contains("ransaction") && Body.contains("solde")
                    && Body.contains(today)) {
                if (Body.contains(leCasPrincipal)){
                    if(Body.contains(leSecond)) {
                        String BodyRetenu = Body;
                        String montant = "";
                        String exp = "";
                        String exxp ="";
                        String date = "";
                        String datee = "";
                        String numero= "";
                        String reference = "";
                        String referencee ="";
                        String transac = "";
                        String transaction = "";
                        String intr = ":";
                    /*String[] arrOfStr = BodyRetenu.split(" ");
                    for (int i =0 ; arrOfStr.length;i++)
                        System.out.println("voila "+arrOfStr[i]); */
                        montant = BodyRetenu.substring(31,BodyRetenu.indexOf("F"));
                        exp = BodyRetenu.substring(40,BodyRetenu.indexOf("("));
                        exxp = exp.substring(exp.indexOf("e"));
                        String mexp = exxp.substring(2);
                        //CharSequence expe = exp.subSequence(41,BodyRetenu.indexOf("("));
                        date = BodyRetenu.substring(59,BodyRetenu.indexOf(". Re"));
                        //date = exxp.substring(Integer.parseInt(exxp),BodyRetenu.indexOf("Re"));
                        datee = date.substring(date.indexOf("2"));
                        String num = date.substring(date.indexOf("l"));
                        String no = num.substring(num.indexOf("2"));
                        String num2 = date.substring(date.indexOf("l"));
                        numero = datee.substring(datee.indexOf("2"),11);
                        //String numez = numero.substring(numero.indexOf("2"),numero.indexOf("l"));
                        reference = BodyRetenu.substring(BodyRetenu.indexOf("ce:"));
                        referencee = reference.substring(3,reference.indexOf(". N"));
                        String ref = referencee.substring(1);
                        transac = BodyRetenu.substring(BodyRetenu.indexOf("action"));
                        transaction = transac.substring(transac.indexOf(":"));
                        String tr = transaction.substring(transaction.indexOf(":"),11);
                        String no2 = no.substring(0,10);
                        String hour = no.substring(11,19);
                        String trr = tr.substring(2);

                        smsList.add("Number: " + Number + "\n" + "" +"\n" +
                                //"Chiffre :" + chiffr + "\n" + "" +"\n" +
                                "MONTANT:" + montant + "\n" + "" +"\n" +
                                "Expéditeur:" + mexp + "\n" + "" +"\n" +
                                "Numéro:" + numero + "\n" + "" +"\n" +
                                "Date:" + no2 + "\n" + "" +"\n" +
                                "Heure:" + hour + "\n" + "" +"\n" +
                                "Référence:" + ref + "\n" + "" +"\n" +
                                "Transaction ID:" + trr + "\n" + "" +"\n" +
                                "Body: " + Body + "\n" );
                        //String [] tab1 = {montant,exxp,numero,no,referencee,tr,Body};
                        //Toast.makeText(this, ""+tab1, Toast.LENGTH_SHORT).show();
                        /*for(String table1 : tab1) {
                            Toast.makeText(this, ""+table1, Toast.LENGTH_SHORT).show();
                        }*/
                        JSONObject obj=new JSONObject();
                        try {
                            obj.put("montant",montant);
                            obj.put("expediteur",mexp);
                            obj.put("numero",numero);
                            obj.put("date",no2);
                            obj.put("heure",hour);
                            obj.put("reference",ref);
                            obj.put("id_de_transaction",trr);
                            obj.put("corps_du_message",Body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        array.put(obj);
                        System.out.println("premier "+array);
                        //Toast.makeText(this, ""+array, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(this, ""+array, Toast.LENGTH_SHORT).show();
                        /*JSONArray jsonArray = new JSONArray();
                        JSONObject object = new JSONObject();
                        try {
                            object.put("Montant: ", montant);
                            object.put("Expéditeur: ",exxp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        jsonArray.put(object);*/
                    }
                    else {
                        String BodyRetenu = Body;
                        String montant1 = "";
                        String exp2 = "";
                        String exp22 = "";
                        String date1 = "";
                        String datee1 = "";
                        String num = "";
                        String transaction = "";
                        String transac = "";
                        String intru = "e";
                        String intru2 = ":";
                        montant1 = BodyRetenu.substring(15,BodyRetenu.indexOf("d"));
                        exp2 = BodyRetenu.substring(27,BodyRetenu.indexOf("("));
                        exp22 = exp2.substring(1);
                        //num = BodyRetenu.substring(BodyRetenu.indexOf("("),BodyRetenu.indexOf("s"));
                        date1 = BodyRetenu.substring(BodyRetenu.indexOf("(2"),BodyRetenu.indexOf("."));
                        datee1 = date1.substring(date1.indexOf(" 2"));
                        num = date1.substring(date1.indexOf("("),13);
                        String numa = date1.substring(date1.indexOf("2"),date1.indexOf(") s"));
                        transac = BodyRetenu.substring(BodyRetenu.indexOf("ID:"));
                        transaction = transac.substring(transac.indexOf(":"),transac.indexOf("F"));
                        //Toast.makeText(this, ""+res, Toast.LENGTH_SHORT).show();
                        String exe = exp2.replace(intru, "");
                        String ex = exp2.contains(intru) ? exe : null;
                        String tra = transaction.replace(intru2,"");
                        String trans = transaction.contains(intru2) ? tra : null;
                        String dat = datee1.substring(1,11);
                        String hours = datee1.substring(12,20);
                        String tra2 = tra.substring(1);
                        String exe2 = exe.substring(1);
                        smsList.add("Number: " + Number + "\n" + "" +"\n" +
                                //"Chiffre :" + chiffr + "\n" + "" +"\n" +
                                "MONTANT:" + montant1 + "\n" + "" +"\n" +
                                "Expéditeur:" + exe + "\n" + "" +"\n" +
                                "Numéro:" + numa + "\n" + "" +"\n" +
                                "Date:" + dat + "\n" + "" +"\n" +
                                "heure:" + hours + "\n" + "" +"\n" +
                                "Transaction ID:" + tra2 + "\n" + "" +"\n" +
                                "Body: " + Body + "\n" );
                        //String [] tab2 = {montant1,exe,numa,datee1,transaction,Body};
                        //Toast.makeText(this, ""+tab2, Toast.LENGTH_SHORT).show();
                        /*for(String table1 : tab2) {
                            Toast.makeText(this, ""+table1, Toast.LENGTH_SHORT).show();
                        }*/
                        JSONObject obj=new JSONObject();
                        try {
                            obj.put("montant",montant1);
                            obj.put("expediteur",exe);
                            obj.put("numero",numa);
                            obj.put("date",dat);
                            obj.put("heure",hours);
                            //obj.put("Référence: ",referencee);
                            obj.put("id_de_transaction",tra);
                            obj.put("corps_du_message",Body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        array.put(obj);
                        System.out.println("second "+array);
                    }

                    //Toast.makeText(this, ""+array, Toast.LENGTH_SHORT).show();
                    /*JSONObject object = new JSONObject();
                    try {
                        object.put("Montant", montant1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                }
                else{

                }
            }
            else {

            }

        }
        //Toast.makeText(this, ""+array, Toast.LENGTH_LONG).show();
        String url = "http://192.168.1.109:3001/getMomoDataFromApp";
        RequestQueue queue = Volley.newRequestQueue(this);
       /* JsonArrayRequest request_json = new JsonArrayRequest(Request.Method.POST, url, array,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Get Final response
                        //Toast.makeText(ReadMsg.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.e("Error: ", volleyError.getMessage());
                //Toast.makeText(ReadMsg.this, ""+volleyError, Toast.LENGTH_LONG).show();
            }
        });*/
        //queue.add(request_json);
        System.out.println("final "+array);
        //pla();
        //System.out.println(array);
        c.close();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, smsList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        // Play song.
        handler = new Handler();
        //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        //String texte_date = sdf.format(new Date());
        Date madate = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(madate);

        Calendar todayy = Calendar.getInstance();
        todayy.set(Calendar.HOUR_OF_DAY,15);
        todayy.set(Calendar.MINUTE,53);
        todayy.set(Calendar.SECOND,00);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mediaPlayer.start();
                showContacts();
                System.out.println("Correct");
                System.out.println(" ");
                /*handler.post(new Runnable() {
                    @Override
                    public void run() {
                        mediaPlayer.start();
                        showContacts();
                        System.out.println("Correct");
                    }
                });*/
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,todayy.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
        //timer.scheduleAtFixedRate(task,0,3000);
        //Log.d("hh:mm : ", texte_date);
        //while (texte_date.contains("13:57")){
            //System.out.println("Il est l heure");
            //mediaPlayer.start();
        //    }



        return START_STICKY;
    }

    // Destroy
    @Override
    public void onDestroy() {
        // Release the resources
        //mediaPlayer.release();
        super.onDestroy();
    }
}