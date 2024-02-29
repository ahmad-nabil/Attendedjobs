package com.ahmad.attendedjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmad.attendedjobs.customlists.attendlist;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Trainingcenter extends AppCompatActivity implements View.OnClickListener {
   private TextView titlecourse,daycourse,timecourse,titleinformation,detailsinformation;
   Intent getdata;
ImageView logocourse;
Button attendnow,backbtn_training;
RadioButton btnradiopublic,btnradioprivate;
    List <attendlist> attendlists;
boolean checklist=false;
ArrayList <String> typecourse;
SharedPreferences getarrays;
boolean attendcourse=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainingcenter);
        //intilize listener text and button
       titlecourse=findViewById(R.id.titlecourse);
        daycourse=findViewById(R.id.dayscourse);
        timecourse=findViewById(R.id.timecourse);
        titleinformation=findViewById(R.id.titleinformation);
        detailsinformation=findViewById(R.id.detailsinformation);
        logocourse=findViewById(R.id.logocourse);
        getdata=getIntent(); //get data
        titlecourse.setText(getdata.getStringExtra("title"));
        daycourse.setText(getdata.getStringExtra("day"));
        timecourse.setText(getdata.getStringExtra("time"));
        logocourse.setImageResource(getdata.getIntExtra("imglogo",0));
        //here intilize and write data depend on your choice in main activity
        if (getdata.getIntExtra("imglogo",0)==R.drawable.android){
    titleinformation.setText("Advanced Android Development");
    detailsinformation.setText("In the Advanced Android Development training,\n  you take your Android coding skills to the next level.\n The course teaches you ways to expand \n the user experience,improve your app's performance,\n and add advanced features like custom views, animations, and location-awareness. \n Each lesson includes a tutorial with solution code in GitHub,\n concept documentation, and a slide deck.");
} else if (getdata.getIntExtra("imglogo",0)==R.drawable.ioslogo) {
            titleinformation.setText("Advanced IOS Development");
            detailsinformation.setText("In the Advanced IOS Development training,,\n  you take your IOS coding skills to the next level.\n The course teaches you ways to expand \n the user experience,improve your app's performance,\n and add advanced features like custom views, animations, and location-awareness. \n Each lesson includes a tutorial with solution code in GitHub,\n concept documentation, and a slide deck.");

        } else if (getdata.getIntExtra("imglogo",0)==R.drawable.qa2) {
            titleinformation.setText("Advanced QA and PM For Development");
            detailsinformation.setText("In the Advanced QA and PM training,\\n  you take your QA and PM skills to the next level.\\n  The course teaches you ways to expand \\n    the user experience, improve your test app's performance,,\\n and add advanced features  and concept documentation.");

        }
//radio button if private session or public
        btnradioprivate=findViewById(R.id.btnradioprivate);
        btnradiopublic=findViewById(R.id.btnradiopublic);
        //button attend and back button intilize with listiener
        attendnow=findViewById(R.id.attendnow);
        backbtn_training=findViewById(R.id.backbtn_training);
        attendnow.setOnClickListener(this);
        backbtn_training.setOnClickListener(this);

        //check if their old data in training or not
        getarrays=getSharedPreferences("arrays",MODE_PRIVATE);
   String json=getarrays.getString("trainings","");
   if (!json.isEmpty()){
       Gson gson=new Gson();
       Type type=new TypeToken<List<attendlist>>(){}.getType();
       attendlists=gson.fromJson(json,type);
   }else {
       attendlists=new ArrayList<>();
   }

    }

    @Override
    public void onClick(View view) {
        SharedPreferences.Editor editor = getarrays.edit();
        if (view.getId() == R.id.attendnow) {
            if (attendlists.size() > 0) {

                for (int index = 0; index <= attendlists.size() - 1; ) {
                    if (attendlists.get(index).getTitle().equals(titleinformation.getText().toString())) {
                        Toast.makeText(this, "you attended already", Toast.LENGTH_SHORT).show();
                        attendcourse=true;
                        break;
                    } else if (index == attendlists.size()-1) {

                        if (attendcourse == false) {
                            if (btnradioprivate.isChecked()) {
                                attendlists.add(new attendlist(titleinformation.getText().toString(), daycourse.getText().toString() + "\n" + timecourse.getText().toString(), "private session", getdata.getIntExtra("imglogo", 0),1));
                                Toast.makeText(this, "attended", Toast.LENGTH_SHORT).show();
                                attendcourse = true;
                                break;

                            } else if (btnradiopublic.isChecked()) {
                                attendlists.add(new attendlist(titleinformation.getText().toString(), daycourse.getText().toString() + "\n" + timecourse.getText().toString(), "public session", getdata.getIntExtra("imglogo", 0),1));
                                Toast.makeText(this, "attended", Toast.LENGTH_SHORT).show();
                                attendcourse = true;
                                break;

                            } else {
                                Toast.makeText(this, "choose type session", Toast.LENGTH_SHORT).show();
                                break;

                            }
                        }
                    }
                    index++;
                }


            }




            if (attendcourse == false) {
                if (btnradioprivate.isChecked()) {
                    attendlists.add(new attendlist(titleinformation.getText().toString(), daycourse.getText().toString() + "\n" + timecourse.getText().toString(), "private session", getdata.getIntExtra("imglogo", 0),1));
                    Toast.makeText(this, "attended", Toast.LENGTH_SHORT).show();
                    attendcourse = true;

                } else if (btnradiopublic.isChecked()) {
                    attendlists.add(new attendlist(titleinformation.getText().toString(), daycourse.getText().toString() + "\n" + timecourse.getText().toString(), "public session", getdata.getIntExtra("imglogo", 0),1));
                    Toast.makeText(this, "attended", Toast.LENGTH_SHORT).show();
                    attendcourse = true;

                } else {
                    Toast.makeText(this, "choose type session", Toast.LENGTH_SHORT).show();
                }
            }}else if (view.getId()==R.id.backbtn_training) {
           if (attendlists.size()>0){
              Gson gson=new Gson();
              String json=gson.toJson(attendlists);
              editor.putString("trainings",json).commit();
              super.onBackPressed();
           }else {
               Toast.makeText(this, "you didnt attend any course", Toast.LENGTH_SHORT).show();
               super.onBackPressed();
           }

       }
    }
}