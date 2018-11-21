package com.KkTechsol.personalitydevelopment;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


    }
    public void start(View view){
        Intent mainIntent = new Intent(Start.this,MainActivity.class);
        startActivity(mainIntent);

    }
    public void rate(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+ getApplicationContext().getPackageName()));
        try{
            startActivity(intent);
        }
        catch(Exception e){
            intent.setData(Uri.parse("https://play.google.com"));
        }

    }
    public void share(View view){
        String pakg=getApplicationContext().getPackageName();
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        // sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Current Affairs 2018"+"https://play.google.com/store/apps/details?id="+pakg);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Current Affairs 2018"+"https://play.google.com/store/apps/details?id="+pakg);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void moreapps(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);

        intent.setData(Uri.parse("https://play.google.com/store/apps/dev?id=6385105107502440808"));
        try{
            startActivity(intent);
        }
        catch(Exception e){
            intent.setData(Uri.parse("https://play.google.com"));
        }

    }
}
