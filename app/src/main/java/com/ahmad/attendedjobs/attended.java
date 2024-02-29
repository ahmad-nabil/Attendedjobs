package com.ahmad.attendedjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ahmad.attendedjobs.adabters.recycleviewattend;
import com.ahmad.attendedjobs.customlists.attendlist;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class attended extends AppCompatActivity {
RecyclerView attendrecycleview;
  List<attendlist> eventdata, trainingdata,compinedata=new ArrayList<>();
  Button backbtn_attended;
  TextView titleTV;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attended);
//recycleview with adabters
    attendrecycleview=findViewById(R.id.attendrecycleview);
        SharedPreferences getList = getSharedPreferences("arrays", MODE_PRIVATE);
     String event = getList.getString("eventjoined", "");
     String training = getList.getString("trainings", "");
     String attends = getList.getString("attend list", "");
     String title = "<font color=#F3E2E2>ATTEND </font> <font color=#ffcc00>JOB</font> <font color=#F3E2E2> And </font> <font color=#F3E2E2>courses</font>";
    titleTV=findViewById(R.id.titleTV);
    titleTV.setText(Html.fromHtml(title));
     Gson gson = new Gson();

     if (!event.isEmpty()) {
         Type type = new TypeToken<List<attendlist>>() {
         }.getType();
     eventdata = gson.fromJson(event, type);
     }else{
         eventdata =  new ArrayList<>();

     }
     if (!training.isEmpty()) {
         Type type = new TypeToken<List<attendlist>>() {
         }.getType();
trainingdata = gson.fromJson(training, type);
     }else{
         trainingdata =  new ArrayList<>();

     }
     //to avoid invoke null bug i intilize new array list before combine
                compinedata.addAll(trainingdata);
                compinedata.addAll(eventdata);


        recycleviewattend adabterattend=new recycleviewattend(this,compinedata,eventdata, trainingdata);
attendrecycleview.setAdapter(adabterattend);
        attendrecycleview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

backbtn_attended=findViewById(R.id.backbtn_attended);
          backbtn_attended.setOnClickListener(new View.OnClickListener() {
              SharedPreferences.Editor save=getList.edit();
              @Override
              public void onClick(View view) {
adabterattend.notifyDataSetChanged();


                              eventdata=eventdata.stream().filter(compinedata::contains).collect(Collectors.toList());
                  Gson gson = new Gson();
                  String training = gson.toJson(trainingdata);
                  String events = gson.toJson(eventdata);

                  save.putString("eventjoined",events );
                  save.putString("trainings",training );
                  //clear list event and training

                  save.commit();
                  attended.super.onBackPressed();
          }});


 }
}