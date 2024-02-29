package com.ahmad.attendedjobs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmad.attendedjobs.adabters.recycleviewnotfication;
import com.ahmad.attendedjobs.customlists.listnotfication;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.constant.ImageProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class profile extends AppCompatActivity implements View.OnClickListener {
EditText Email_profile,Phone_profile,birthdate_profile,username_profile,editfullname_profile;
SharedPreferences getProfiledata;
Uri updateprofile;
Button savebtnprofile,notfi_profile,backbtnprofile;
TextView edittv;
Boolean showwnotfiprofile=false;
RecyclerView recyclelistnotfication;
    List<listnotfication>receivedData;

    View ViewProfile;
    recycleviewnotfication adabterNotficationprofile;

de.hdodenhof.circleimageview.CircleImageView updateprofilepic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //intilize component
        editfullname_profile=findViewById(R.id.editfullname_profile);
        Email_profile=findViewById(R.id.Email_profile);
        Phone_profile=findViewById(R.id.Phone_profile);
        birthdate_profile=findViewById(R.id.birthdate_profile);
        username_profile=findViewById(R.id.username_profile);
        updateprofilepic=findViewById(R.id.updateprofilepic);
        savebtnprofile=findViewById(R.id.savebtnprofile);
        edittv=findViewById(R.id.edittv);
        notfi_profile=findViewById(R.id.notfiprofilebutton);
        backbtnprofile=findViewById(R.id.backbtn_profile);
        backbtnprofile.setOnClickListener(this);
        notfi_profile.setOnClickListener(this);
        savebtnprofile.setOnClickListener(this);
        updateprofilepic.setOnClickListener(this);
//shared preferences to get data
        getProfiledata=getSharedPreferences("user",MODE_PRIVATE);
        editfullname_profile.setText(getProfiledata.getString("fullname","not register correctly"));
        username_profile.setText(getProfiledata.getString("username","not register correctly"));
        birthdate_profile.setText(getProfiledata.getString("birthdate","not register correctly"));
        Phone_profile.setText(getProfiledata.getString("phone","not register correctly"));
        Email_profile.setText(getProfiledata.getString("email","not register correctly"));

        //check if uri not null  will set it in img
        if (getProfiledata.getString("uri",null)!=null){

            updateprofilepic.setImageURI( Uri.parse(getProfiledata.getString("uri",null)));
        }
//inflate view notfication
  ViewProfile   = LayoutInflater.from(profile.this).inflate(R.layout.listnotfiction_view,null);
        ViewProfile.setAlpha(0f);
        FrameLayout framprofile=findViewById(R.id.framprofile);
        framprofile.addView(ViewProfile);
        //Rescycleview for notfication list
        recyclelistnotfication=ViewProfile.findViewById(R.id.recyclelistnotficatio);

    }
    private void closenotfictaion() {
        AlphaAnimation animation=new AlphaAnimation(1f,0f);
        animation.setDuration(500);
        ViewProfile.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showwnotfiprofile=false;
                ViewProfile.setAlpha(0f);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    private void showwnotficationlist(){
        SharedPreferences getList = getSharedPreferences("arrays", MODE_PRIVATE);
        String json = getList.getString("notfication", "");
        if (!json.isEmpty()){
            Gson gson = new Gson();
            Type type = new TypeToken<List<listnotfication>>() {}.getType();
            receivedData = gson.fromJson(json, type);
            adabterNotficationprofile=new recycleviewnotfication(receivedData,profile.this);
            recyclelistnotfication.setAdapter(adabterNotficationprofile);
            recyclelistnotfication.setLayoutManager(new LinearLayoutManager(profile.this,LinearLayoutManager.VERTICAL,false));
        }else{
            receivedData=new ArrayList<>();
            adabterNotficationprofile=new recycleviewnotfication(receivedData,profile.this);
            recyclelistnotfication.setAdapter(adabterNotficationprofile);
            recyclelistnotfication.setLayoutManager(new LinearLayoutManager(profile.this,LinearLayoutManager.VERTICAL,false));
        }
        if (receivedData.isEmpty()){
            Toast.makeText(this, "no notfication now", Toast.LENGTH_SHORT).show();
        }
        AlphaAnimation animation=new AlphaAnimation(0f,1f);
        animation.setDuration(500);
        ViewProfile.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ViewProfile.setAlpha(1f);
                showwnotfiprofile=true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    @Override
    public void onClick(View view) {
        SharedPreferences.Editor store=getProfiledata.edit();
        if (view.getId()==R.id.updateprofilepic){
            ImagePicker.Companion.with(this).crop().maxResultSize(100,100).provider(ImageProvider.BOTH).start();
        } else if (view.getId()==R.id.savebtnprofile) {
            PropertyValuesHolder rotation = PropertyValuesHolder.ofFloat(View.ROTATION, 270f, 90f);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(savebtnprofile, rotation);
            animator.setDuration(1000); // 1 second
            animator.start();
            store.putString("phone",Phone_profile.getText().toString()).commit();
            store.putString("email",Email_profile.getText().toString()).commit();
            store.putString("birthdate",birthdate_profile.getText().toString()).commit();
            store.putString("fullname",editfullname_profile.getText().toString()).commit();
            store.putString("username",username_profile.getText().toString()).commit();
edittv.setText("save");
if (updateprofile!=null) {
    store.putString("uri", updateprofile.toString()).commit();
}
store.commit();
        }else if (view.getId()==R.id.notfiprofilebutton){
            if (showwnotfiprofile){
                closenotfictaion();
            }else {
                showwnotficationlist();
            }

        }else if (view.getId()==R.id.backbtn_profile){
finish();        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
updateprofile=data.getData();
if (updateprofile!=null){
updateprofilepic.setImageURI(updateprofile);
}
    }


}