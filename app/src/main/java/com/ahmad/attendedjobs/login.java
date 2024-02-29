package com.ahmad.attendedjobs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity implements View.OnClickListener {
    private Button loginBtn,forgetpasswordBtn,signuplayoutBtn;
private EditText username_login,password_login;
SharedPreferences logindata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //intilize UI component with listener
        loginBtn=findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        forgetpasswordBtn=findViewById(R.id.forgetpasswordBtn);
        forgetpasswordBtn.setOnClickListener(this);
        signuplayoutBtn=findViewById(R.id.signuplayoutBtn);
 signuplayoutBtn.setOnClickListener(this);
        username_login=findViewById(R.id.username_login);
        password_login=findViewById(R.id.password_login);
logindata=getSharedPreferences("user",MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.loginBtn){

if (logindata.getString("username",null).equals(username_login.getText().toString())&&
       logindata.getString("password",null).equals(password_login.getText().toString()))
{
startActivity(new Intent(this,MainActivity.class));}
else {
    Toast.makeText(this, "check information or press back to pass to main activity", Toast.LENGTH_SHORT).show();

}
        }else if (view.getId()==R.id.signuplayoutBtn){
            startActivity(new Intent(this, signup.class));
        }else if (view.getId()==R.id.forgetpasswordBtn){
            AlertDialog.Builder builder=new AlertDialog.Builder(login.this);
            builder.setTitle("rest password");
            builder.setMessage("Enter username:");
            final EditText input = new EditText(login.this);
        builder.setView(input);
            builder.setPositiveButton("rest", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String enteredText = input.getText().toString();
                    if (enteredText.equals(logindata.getString("username",""))){
                        Toast.makeText(login.this, "your password is " +logindata.getString("password",""), Toast.LENGTH_SHORT).show();
                    }

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
        builder.show();
        }}


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

        startActivity(new Intent(login.this, MainActivity.class));

    }
}