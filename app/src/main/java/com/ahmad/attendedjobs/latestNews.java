package com.ahmad.attendedjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Config;
import android.view.View;
import android.widget.Button;

import com.ahmad.attendedjobs.adabters.adabterrecylelatest;
import com.ahmad.attendedjobs.customlists.lastnewslist;

import java.util.Locale;

public class latestNews extends AppCompatActivity implements View.OnClickListener  {
RecyclerView latestnewsRecycleview;
    adabterrecylelatest adabternews;
    Button sharebutton,backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_news);


        latestnewsRecycleview=findViewById(R.id.recyclelatestnews);
        adabternews=new adabterrecylelatest(latestNews.this);
        adabternews.addnews(new lastnewslist(getString(R.string.google),getString(R.string.GoogleLastUpdates),getString(R.string.G_LATESTNEWS),R.drawable.google));
        adabternews.addnews(new lastnewslist(getString(R.string.android),getString(R.string.androidlatestnews),getString(R.string.androiddevwebsitefullnews),R.drawable.android));
        adabternews.addnews(new lastnewslist(getString(R.string.ios),getString(R.string.LastUpdates),getString(R.string.ioslastupdat),R.drawable.ioslogo));
 latestnewsRecycleview.setAdapter(adabternews);
 latestnewsRecycleview.setLayoutManager(new LinearLayoutManager(latestNews.this,LinearLayoutManager.VERTICAL,false));

sharebutton=findViewById(R.id.sharebtn);
backbtn=findViewById(R.id.backbtnlastnews);
sharebutton.setOnClickListener(this);
backbtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.sharebtn){
            Intent share =   new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_SUBJECT,"share attend jop ");
            String app_url = " https://play.google.com/store/apps/details?id=";
            share.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
            startActivity(Intent.createChooser(share, "Share via"));
        }else if(view.getId()==R.id.backbtnlastnews){
            finish();
        }
    }
}