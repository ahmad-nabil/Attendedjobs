package com.ahmad.attendedjobs;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ahmad.attendedjobs.adabters.adabterrecyclejob;
import com.ahmad.attendedjobs.customlists.recycleListjop;

import java.net.URI;

public class jobs extends AppCompatActivity implements View.OnClickListener {
private Button backbtn;
RecyclerView rviewjops;
    Uri CVFILE;
    adabterrecyclejob  adabterrecyclejob;
    private  int STORAGE_PERMISSION_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);
        //intilize button with listener
        backbtn=findViewById(R.id.backbtn_jops);
        backbtn.setOnClickListener(this);
        //recycleview with adabter
        rviewjops=findViewById(R.id.rviewjops);
        adabterrecyclejob=new adabterrecyclejob(jobs.this,this);
        adabterrecyclejob.additems(new recycleListjop("Android Developer","2 Years Experience",R.drawable.android));
        adabterrecyclejob.additems(new recycleListjop("Android Developer","trainee knowledge of \n Java Language and Android\n Development Basics",R.drawable.android));
        adabterrecyclejob.additems(new recycleListjop("IOS Developer","4 Years Experience",R.drawable.ioslogo));
        adabterrecyclejob.additems(new recycleListjop("Social Media Marketing","2 Years Experience",R.drawable.facebook));
        rviewjops.setAdapter(adabterrecyclejob);
        rviewjops.setLayoutManager(new LinearLayoutManager(jobs.this,RecyclerView.VERTICAL,false));


    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.backbtn_jops){
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK){
            CVFILE=data.getData();
            Toast.makeText(this, "your cv is uploaded", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}