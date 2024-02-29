package com.ahmad.attendedjobs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class payments extends AppCompatActivity implements View.OnClickListener {
Button btnsave_payment,backbtnpayments;
EditText expirtiondata,cardnum,cvvnum;
SharedPreferences savecard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        expirtiondata=findViewById(R.id.expirtiondata);
        cardnum=findViewById(R.id.cardnum);
        cvvnum=findViewById(R.id.cvvnum);

        //listener btn
        btnsave_payment=findViewById(R.id.btnsave_payment);
        backbtnpayments=findViewById(R.id.backbtn_payment);
        btnsave_payment.setOnClickListener(this);
        backbtnpayments.setOnClickListener(this);

        savecard=getSharedPreferences("user",MODE_PRIVATE);
        //get old data if their any data from shared prefernces
        expirtiondata.setText(savecard.getString("expiratiion",""));
        cvvnum.setText(savecard.getString("cvvnum",""));
        cardnum.setText(savecard.getString("cardnum",""));
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.backbtn_payment){
            finish();
        }else if (view.getId()==R.id.btnsave_payment){
            SharedPreferences.Editor save=savecard.edit();
            if (TextUtils.isEmpty(expirtiondata.getText())||TextUtils.isEmpty(cvvnum.getText())||TextUtils.isEmpty(cardnum.getText())){
                Toast.makeText(this, "fill all information", Toast.LENGTH_SHORT).show();
            }else{

                save.putString("cardnum",cardnum.getText().toString());
                save.putString("cvvnum",cvvnum.getText().toString());
                save.putString("expiratiion",expirtiondata.getText().toString());
                save.commit();
            }
        }
    }
}