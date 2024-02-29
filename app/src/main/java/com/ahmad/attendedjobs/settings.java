package com.ahmad.attendedjobs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class settings extends AppCompatActivity implements View.OnClickListener {
EditText oldpassword,newpassword,repassword,feedbackfiled;
Button arbtn,enbtn,backbtn,sendbtn;
SharedPreferences getsettingdata;
String language;
ConstraintLayout constlayout;
RatingBar rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        constlayout=findViewById(R.id.constlayout);
        getsettingdata = getSharedPreferences("user", MODE_PRIVATE);
//intilize button language;
        arbtn=findViewById(R.id.arbtn);
        enbtn=findViewById(R.id.enbtn);
        backbtn=findViewById(R.id.backbtn_setting);
        sendbtn=findViewById(R.id.sendbtn);
        sendbtn.setBackgroundColor(Color.parseColor("#52a0fd"));
      arbtn.setOnClickListener(this);
      backbtn.setOnClickListener(this);
enbtn.setOnClickListener(this);
sendbtn.setOnClickListener(this);
        //get language now for button
        Configuration config = getResources().getConfiguration();
        Locale currentLocale = config.locale;
        language= currentLocale.getLanguage();

        if (language.equals("ar")) {
            arbtn.setBackgroundColor(Color.parseColor("#808080"));
            enbtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
            arbtn.setClickable(false);
        }else if (language.equals("en")){
            enbtn.setBackgroundColor(Color.parseColor("#808080"));
            arbtn.setBackgroundColor(Color.parseColor("#FFFFFF"));
            enbtn.setClickable(false);
        }

        feedbackfiled=findViewById(R.id.feedbackfiled);
        oldpassword = findViewById(R.id.oldPassword);
        newpassword = findViewById(R.id.newPassword);
        repassword = findViewById(R.id.renewPassword);

      /*
      under this brief i make text watcher between parentheses to get text from old and new password   to save it using snackbar
       */

            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence.length() == 10) {
                        // Add a new line character to move to the second line
                        feedbackfiled.append("\n");
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    oldpassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView textView, int actionid, KeyEvent keyEvent) {
                            if (actionid==EditorInfo.IME_ACTION_NEXT){
                                if (!oldpassword.getText().toString().equals(getsettingdata.getString( "password", null))) {
                                    Toast.makeText(settings.this, "write your real password", Toast.LENGTH_SHORT).show();
                                    return true; }

                            }
                            return false;
                        }
                    });

                        if (newpassword.getText().toString().equals(repassword.getText().toString()) && newpassword.getText().toString().length() > 5) {

                            Snackbar.make(constlayout, "press save to save change password", Snackbar.LENGTH_INDEFINITE).setAction("Save", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    SharedPreferences.Editor editor = getsettingdata.edit();
                                    editor.putString("password", repassword.getText().toString());
                                    oldpassword.setText("");
                                    newpassword.setText("");
                                    repassword.setText("");
                                }
                            }).show();
                        }
                    }

                } ;

        oldpassword.addTextChangedListener(textWatcher);
        newpassword.addTextChangedListener(textWatcher);
        repassword.addTextChangedListener(textWatcher);
feedbackfiled.addTextChangedListener(textWatcher);
        //ratingbar
        rate=findViewById(R.id.ratingBar);
rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rate, boolean fromUser) {
        if (fromUser) {
            int rating = Math.round(rate);
            Toast.makeText(settings.this, "New rating: " + rate, Toast.LENGTH_SHORT).show();
        }
    }
});


    }

    @Override
    public void onClick(View view) {
        View viewsplasher=findViewById(R.id.splasher);
if (view.getId()==R.id.arbtn){
viewsplasher.setVisibility(View.VISIBLE);
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Locale.setDefault(new Locale("ar"));
            Configuration config = new Configuration();
            config.locale = new Locale("ar");
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
            recreate();
viewsplasher.setVisibility(View.GONE);

        }
    }, 3000);


    }else if (view.getId()==R.id.enbtn){
    viewsplasher.setVisibility(View.VISIBLE);
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Locale.setDefault(new Locale("en"));
            Configuration config = new Configuration();
            config.locale = new Locale("en");
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
            recreate();
            viewsplasher.setVisibility(View.GONE);

        }
    }, 3000);
        }else if (view.getId()==R.id.backbtn_setting){
    finish();
}else if (view.getId()==R.id.sendbtn){

    if (TextUtils.isEmpty(feedbackfiled.getText())){

        Toast.makeText(this, "enter feed back befor press on send", Toast.LENGTH_SHORT).show();}
    else {Toast.makeText(this, "thanks for your feedback ", Toast.LENGTH_SHORT).show();}
feedbackfiled.setText("");
    }
    }

}

