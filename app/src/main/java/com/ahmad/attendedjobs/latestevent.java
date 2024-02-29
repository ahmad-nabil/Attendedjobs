package com.ahmad.attendedjobs;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ahmad.attendedjobs.adabters.Recycleviewlatestevent;
import com.ahmad.attendedjobs.adabters.adabetrViewpagerlatestEvents;
import com.ahmad.attendedjobs.customlists.attendlist;
import com.ahmad.attendedjobs.customlists.customevntviewpager;
import com.ahmad.attendedjobs.customlists.customlistrecycleviewlatestevent;
import com.ahmad.attendedjobs.customlists.listnotfication;
import com.ahmad.attendedjobs.transformationViewpager.DepthPageTransformer;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class latestevent extends AppCompatActivity implements View.OnClickListener {
Button backbtn_latestevent,next_eventbtn,previous_eventbtn;
ViewPager2 vpagerEvent;
    adabetrViewpagerlatestEvents adabterEvent;
    RecyclerView RecyclerViewLatestEvent;
Recycleviewlatestevent adabterRecycle;
    List<attendlist>event_join=new ArrayList<>();
    List<listnotfication>listnotfications=new ArrayList<>();
    String CHANNEL_ID;


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latestevent);
//intilize btn with listener
    {
        backbtn_latestevent = findViewById(R.id.backbtn_latesteventlayout);
        next_eventbtn = findViewById(R.id.next_eventbtn);
        previous_eventbtn = findViewById(R.id.previous_eventbtn);
        backbtn_latestevent.setOnClickListener(this);
        next_eventbtn.setOnClickListener(this);
        previous_eventbtn.setOnClickListener(this);
    }

//viewpager with transformer and adabter
    {

        vpagerEvent = findViewById(R.id.vpagerEvent);
        adabterEvent = new adabetrViewpagerlatestEvents(latestevent.this,event_join,this);
        adabterEvent.addevents(new customevntviewpager("Power Youth Event In Irbid Now!", "11:00am-2:00pm", "Sunday", "23-1-2019"));
        adabterEvent.addevents(new customevntviewpager("Power Youth Event  Now!", "11:00am-2:00pm", "Sunday", "23-1-2019"));
        adabterEvent.addevents(new customevntviewpager("Power Youth Event ", "11:00am-2:00pm", "Sunday", "23-1-2019"));
        vpagerEvent.setAdapter(adabterEvent);
        vpagerEvent.setCurrentItem(1, true);
        vpagerEvent.setClipToPadding(false);
        vpagerEvent.setOffscreenPageLimit(3);
        vpagerEvent.setPadding(50, 0, 50, 0);
        vpagerEvent.setPageTransformer(new DepthPageTransformer());
        vpagerEvent.setUserInputEnabled(false);
    }
    //for notfication in recycle view
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
       CHANNEL_ID = "attend jop"; // Define your channel ID
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "attend event Channel",
                NotificationManager.IMPORTANCE_HIGH
        );

        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);
    }
  //recycleview
  {
    RecyclerViewLatestEvent = findViewById(R.id.RecyclerViewlatestevent);
    adabterRecycle = new Recycleviewlatestevent(latestevent.this,listnotfications,CHANNEL_ID);
    adabterRecycle.additems(new customlistrecycleviewlatestevent("Educations Students", "All Over The World!"));
    adabterRecycle.additems(new customlistrecycleviewlatestevent("Happy Programming Day", "Event!"));
    adabterRecycle.additems(new customlistrecycleviewlatestevent("QA Session", "Talking About Testing"));
    RecyclerViewLatestEvent.setAdapter(adabterRecycle);
    RecyclerViewLatestEvent.setLayoutManager(new LinearLayoutManager(latestevent.this, LinearLayoutManager.VERTICAL, false));
  }
}

    @Override
    public void onClick(View view) {  SharedPreferences saveList = getSharedPreferences("arrays", MODE_PRIVATE);
        SharedPreferences.Editor editor = saveList.edit();
if (view.getId()==R.id.backbtn_latesteventlayout) {

    if (!event_join.isEmpty()||!listnotfications.isEmpty()) {
        if (!event_join.isEmpty()&&!listnotfications.isEmpty()){
            Gson gson = new Gson();
            String event = gson.toJson(event_join);
            String notfication=gson.toJson(listnotfications);
            editor.putString("eventjoined", event);
            editor.putString("notfication", notfication);
            editor.commit();
            super.onBackPressed();
        }else if (!event_join.isEmpty()){
                Gson gson = new Gson();
                String event = gson.toJson(event_join);
                editor.putString("eventjoined", event);
                editor.commit();
                super.onBackPressed();
            }else {
                Gson gson = new Gson();
                String notfication=gson.toJson(listnotfications);
                editor.putString("notfication", notfication);
                editor.commit();
                super.onBackPressed();
            }
        } else{
        Toast.makeText(this, "didnt join any event or notfication and you back to previous screen", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }} else if (view.getId()==R.id.next_eventbtn) {
if (vpagerEvent.getCurrentItem()==adabterEvent.getItemCount()-1){
    Toast.makeText(this, "you are in last pages and you will back to first page immediately ", Toast.LENGTH_SHORT).show();
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {

            vpagerEvent.setCurrentItem(0,true);        }
    },500);


}else {
    vpagerEvent.setCurrentItem(vpagerEvent.getCurrentItem()+1,true);
}
}else if (view.getId()==R.id.previous_eventbtn) {
    if (vpagerEvent.getCurrentItem()==0){
        Toast.makeText(this, "you are in first pages and you will go last pagee immediately ", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                vpagerEvent.setCurrentItem(adabterEvent.getItemCount()-1,true);
            }
        },500);


    }else {
        vpagerEvent.setCurrentItem(vpagerEvent.getCurrentItem()-1,true);
    }
}
    }
}