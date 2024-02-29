package com.ahmad.attendedjobs;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class signup extends AppCompatActivity implements View.OnClickListener{

private Button btnSignup;
SharedPreferences recordData;
private EditText username,password,phone,email,fullname,birthdate;
ImageView backbtn;
    de.hdodenhof.circleimageview.CircleImageView  uploadavatar    ;
Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    //intilize UI with listener for button
        btnSignup=findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(this);
        uploadavatar=findViewById(R.id.uploadavatar);
        uploadavatar.setOnClickListener(this);
        backbtn=findViewById(R.id.backbtn_signup);
        backbtn.setOnClickListener(this);
        //intilize UI for other components such as Edit text
        username=findViewById(R.id.user_name_signup);
        password=findViewById(R.id.password_signup);
        email=findViewById(R.id.Email_signup);
        phone=findViewById(R.id.Phone_signup);
        fullname=findViewById(R.id.fullname_signup);
        birthdate=findViewById(R.id.birthdate_signup);
    //shared preference for record data
        recordData=getSharedPreferences("user",MODE_PRIVATE);

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        startActivity(new Intent(signup.this, MainActivity.class));

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.backbtn_signup){
            finish();
        }else if (view.getId()==R.id.uploadavatar){
            ImagePicker.Companion.with(this).crop().maxResultSize(200,200).start();
        }else if (view.getId()==R.id.btnSignup){
            if (TextUtils.isEmpty(username.getText())||
                    TextUtils.isEmpty(password.getText())
                    ||TextUtils.isEmpty(email.getText())){
                Toast.makeText(this, "fill all information", Toast.LENGTH_SHORT).show();
            }else {   SharedPreferences.Editor store=recordData.edit();
                if (password.getText().toString().length()>6&&uri!=null){

                    store.putString("phone",phone.getText().toString()).commit();
                    store.putString("email",email.getText().toString()).commit();
                    store.putString("birthdate",birthdate.getText().toString()).commit();
                    store.putString("fullname",fullname.getText().toString()).commit();
                    store.putString("username",username.getText().toString()).commit();
                    store.putString("password",password.getText().toString()).commit();
                    store.putString("uri",uri.toString()).commit();
                    startActivity(new Intent(this,MainActivity.class));
                }else {
                    store.putString("phone",phone.getText().toString()).commit();
                    store.putString("email",email.getText().toString()).commit();
                    store.putString("birthdate",birthdate.getText().toString()).commit();
                    store.putString("fullname",fullname.getText().toString()).commit();
                    store.putString("username",username.getText().toString()).commit();
                    store.putString("password",password.getText().toString()).commit();
                    startActivity(new Intent(this,MainActivity.class));
                }
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      uri  = data.getData();
        if (uri!=null){
            uploadavatar.setImageURI(uri);
        }


    }
}