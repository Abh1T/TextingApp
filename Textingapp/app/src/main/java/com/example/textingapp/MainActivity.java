package com.example.textingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
textReceiver textReceiver;
ArrayList<String> getMessages;
ArrayList<String> getSender;
Handler handler;
int x;
boolean aion;
EditText editText;
int aistate;
Button voicesend;
TextView textView;
Button button;
String received;
TextView voicetext;
ImageView imageView;
ViewPager viewPager;
Button aimode;
SmsManager smsManager;
String[] strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        x = 0;
        viewPager = findViewById(R.id.viewpager);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        viewPager.setAdapter(imageAdapter);
        aistate = 0;
        aion = false;
        received = "";
        textReceiver = new textReceiver();
         getMessages = new ArrayList<>();
        getSender = new ArrayList<>();
        voicesend = findViewById(R.id.voicer);
        imageView = findViewById(R.id.imageView);
        voicetext = findViewById(R.id.textView);
        aimode = findViewById(R.id.aioner);
        smsManager = SmsManager.getDefault();
        button = findViewById(R.id.sender);
        textView = findViewById(R.id.receiv);
        editText = findViewById(R.id.textto);
        strings = new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.RECORD_AUDIO};
        ActivityCompat.requestPermissions(this, strings, 1);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        Log.d("Permission", "Granted");
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
            Log.d("Permission", "Granted");
            button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = editText.getText().toString();
                sender(temp);
            }
        });



            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    speecher();
                }
            });


            voicesend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String temper = voicetext.getText().toString();
                    sender(temper);
                }
            });

            aimode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(aion == false) {
                        aion = true;
                        aimode.setText("AI ON");
                        aimode.setBackgroundColor(Color.GREEN);
                    }else{
                        aion = false;
                        aimode.setText("AI OFF");
                        aimode.setBackgroundColor(Color.RED);
                    }

                }
            });
    }


    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(textReceiver, intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(textReceiver);
    }

    public void sender(String str){
        final String no = str;
        if(aion == false) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    smsManager.sendTextMessage("5554", null, no, null, null);
                    Log.d("no", "runs");
                }
            };
            handler.postDelayed(r , 1000);
            Toast.makeText(this, "Message Sent", Toast.LENGTH_SHORT).show();
        }
        if(aion){
            Runnable x = new Runnable() {
                @Override
                public void run() {
                    String var = received.toLowerCase();
                    ArrayList<String> arrayList = new ArrayList<>();
                    if(aistate == 0){
                        if(var.contains("hey") || var.contains("hello") || var.contains("hi") || var.contains("gm") || var.contains("g'day")){
                            aistate++;
                            arrayList.add("Yo");
                            arrayList.add("Hey");
                            arrayList.add("Hi");
                            arrayList.add("Howdy");
                            arrayList.add("Ahoy");
                            int num = (int)(Math.random()*5);
                            textView.setText(arrayList.get(num));
                            final String the = arrayList.get(num);
                            Runnable f = new Runnable() {
                                @Override
                                public void run() {
                                    smsManager.sendTextMessage("5554", null, the, null,null);
                                    Log.d("no", "runs");
                                }
                            };
                            handler.postDelayed(f , 1000);
                            arrayList = new ArrayList<>();
                        }
                    }else if(aistate == 1){
                        arrayList = new ArrayList<>();
                        if(var.contains("how") || var.contains("what's") || var.contains("doing") || var.contains("family")){
                            aistate++;
                            arrayList.add("It's good. How about you?");
                            arrayList.add("It's ok. How about you?");
                            arrayList.add("It's bad. How about you?");
                            arrayList.add("It could be better. How about you?");
                            arrayList.add("I dont want to say right now.");
                            int num = (int)(Math.random()*5);
                            textView.setText(arrayList.get(num));
                            final String the = arrayList.get(num);
                            Runnable f = new Runnable() {
                                @Override
                                public void run() {
                                    smsManager.sendTextMessage("5554", null, the, null,null);
                                    Log.d("no", "runs");
                                }
                            };
                            handler.postDelayed(f , 1000);
                            arrayList = new ArrayList<>();
                        }
                    }else if(aistate == 2){
                        arrayList = new ArrayList<>();
                        if(var.contains("bye") || var.contains("gn") || var.contains("later") || var.contains("lueg") || var.contains("go")){
                            aistate++;
                            arrayList.add("Bye");
                            arrayList.add("Good night");
                            arrayList.add("See you");
                            arrayList.add("I'll see you soon");
                            arrayList.add("Hasta Luego");
                            int num = (int)(Math.random()*5);
                            textView.setText(arrayList.get(num));
                            final String the = arrayList.get(num);
                            Runnable f = new Runnable() {
                                @Override
                                public void run() {
                                    smsManager.sendTextMessage("5554", null, the, null,null);
                                    Log.d("no", "runs");
                                }
                            };
                            handler.postDelayed(f , 1000);
                            arrayList = new ArrayList<>();
                        }
                    }
                }
            };
            handler.postDelayed(x , 1000);
            Toast.makeText(this, "AI Spoke", Toast.LENGTH_SHORT).show();
        }
    }


    public class textReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            Log.d("RUNS", "THis ran ");
            if(bundle!= null){
                Object[] pdus = (Object[])bundle.get("pdus");
                int x = 0;
                SmsMessage[] smsMessages = new SmsMessage[pdus.length];
                while(x< pdus.length){
                    Log.d("RUNS", "THis ran "+"yup");
                    smsMessages[x] = SmsMessage.createFromPdu((byte[])pdus[x]);
                    getMessages.add(smsMessages[x].getMessageBody());
                    Log.d("RUNS", "THis ran "+smsMessages[x].getMessageBody()+" number: "+x);
                    getSender.add(smsMessages[x].getOriginatingAddress());
                    textView.setText(smsMessages[x].getMessageBody());
                    received= smsMessages[x].getMessageBody();
                    x++;
                }
                    if(aion){
                        sender("string");
                    }


            }

        }

    }
    public void speecher(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 10);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 10){
            if(resultCode == RESULT_OK && data != null){
                ArrayList<String> ress = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                voicetext.setText(ress.get(0));
            }
        }
    }
}
