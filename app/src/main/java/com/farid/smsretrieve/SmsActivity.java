package com.farid.smsretrieve;



import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsActivity extends Activity {
    /** Called when the activity is first created. */
    public String k="koko";
    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub

            Toast.makeText(context, "Action", Toast.LENGTH_SHORT).show();
            if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
                Bundle bundle = intent.getExtras();
                if(bundle != null){
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    SmsMessage[] messages = new SmsMessage[pdus.length];
                    for(int i = 0; i<pdus.length; i++)
                        messages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                    for(SmsMessage message : messages)
                    {
                        Log.i("",message.getOriginatingAddress()+"::"+message.getMessageBody());
                        k="je suis ";
                        k=message.getOriginatingAddress()+"::"+message.getMessageBody();
                        if(message.getOriginatingAddress().equals("0022960720383"))
                        {
                            Log.i("","voila je peux le capturé mnt ");
                            Toast.makeText(context,message.getMessageBody(), Toast.LENGTH_LONG).show();
                        }
                        Log.i("",k);
                        Toast.makeText(context, k, Toast.LENGTH_LONG).show();
                    }
                }
            }

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_msg);

        SmsManager smsManager = SmsManager.getDefault();

        Receiver receiver = new Receiver();
        registerReceiver(receiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));



    }
}
