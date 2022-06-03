package com.farid.smsretrieve;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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

import java.util.ArrayList;
import java.util.TimerTask;

public class CustomTask extends TimerTask {
    ListView listView;
    private static final int PERMISSION_REQUEST_READ_CONTACTS = 100;
    ArrayList smsList;
    private ContentResolver contentResolver;
    private Context context;
    public CustomTask(Context context) {
        this.context = context;
    }

    //@Override
    /*public void run() {
        System.out.println("3 SECONDES");
        showContacts();
    }*/
    public void run()  {
        //Intent i = new Intent(context, CronService.class);
        Uri inboxURI = Uri.parse("content://sms/inbox");
        //SuspiciousActivityTable.CONTENT_URI;
        //Context context = null;
        ContentResolver cr = context.getContentResolver();
        smsList = new ArrayList();
                //.update(uri, values2, where,new String[]{"Null"});
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

            if (Number.contains("MTN MoMo") && Body.contains("ransaction") && Body.contains("solde")) {
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
                    /*String[] arrOfStr = BodyRetenu.split(" ");
                    for (int i =0 ; arrOfStr.length;i++)
                        System.out.println("voila "+arrOfStr[i]); */
                        montant = BodyRetenu.substring(31,BodyRetenu.indexOf("F"));
                        exp = BodyRetenu.substring(40,BodyRetenu.indexOf("("));
                        exxp = exp.substring(exp.indexOf("e"));
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
                        transac = BodyRetenu.substring(BodyRetenu.indexOf("action"));
                        transaction = transac.substring(transac.indexOf(":"));
                        String tr = transaction.substring(transaction.indexOf(":"),11);
                        /*
                        smsList.add("Number: " + Number + "\n" + "" +"\n" +
                                //"Chiffre :" + chiffr + "\n" + "" +"\n" +
                                "MONTANT : " + montant + "\n" + "" +"\n" +
                                "Expéditeur : d" + exxp + "\n" + "" +"\n" +
                                "Numéro : " + numero + "\n" + "" +"\n" +
                                "Date et Heure : " + no + "\n" + "" +"\n" +
                                "Référence :" + referencee + "\n" + "" +"\n" +
                                "Transaction ID " + tr + "\n" + "" +"\n" +
                                "Body: " + Body + "\n" );*/
                        //String [] tab1 = {montant,exxp,numero,no,referencee,tr,Body};
                        //Toast.makeText(this, ""+tab1, Toast.LENGTH_SHORT).show();
                        /*for(String table1 : tab1) {
                            Toast.makeText(this, ""+table1, Toast.LENGTH_SHORT).show();
                        }*/
                        JSONObject obj=new JSONObject();
                        try {
                            obj.put("montant",montant);
                            obj.put("expediteur",exxp);
                            obj.put("numero",numero);
                            obj.put("date_et_heure",no);
                            obj.put("reference",referencee);
                            obj.put("id_de_transaction",tr);
                            obj.put("corps_du_message",Body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        array.put(obj);
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
                        /*
                        smsList.add("Number: " + Number + "\n" + "" +"\n" +
                                //"Chiffre :" + chiffr + "\n" + "" +"\n" +
                                "MONTANT :" + montant1 + "\n" + "" +"\n" +
                                "Expéditeur :" + exe + "\n" + "" +"\n" +
                                "Numéro :" + numa + "\n" + "" +"\n" +
                                "Date et heure :" + datee1 + "\n" + "" +"\n" +
                                "Transaction ID :" + tra + "\n" + "" +"\n" +
                                "Body: " + Body + "\n" );

                         */
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
                            obj.put("date_et_heure",datee1);
                            //obj.put("Référence: ",referencee);
                            obj.put("id_de_transaction",tra);
                            obj.put("corps_du_message",Body);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        array.put(obj);
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
        String url = "http://192.168.1.103:3001/getMomoDataFromApp";
        JSONObject object = new JSONObject();
        try {
            object.put("Text", "test");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue queue = Volley.newRequestQueue(null);
        JsonArrayRequest request_json = new JsonArrayRequest(Request.Method.POST, url, array,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Get Final response
                        Toast.makeText(listView.getContext(), ""+response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.e("Error: ", volleyError.getMessage());
                Toast.makeText(listView.getContext(), ""+volleyError, Toast.LENGTH_LONG).show();
            }
        });
        queue.add(request_json);
        //System.out.println(array);
        c.close();
        //ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, smsList);
        //listView.setAdapter(adapter);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

    }
    
}
