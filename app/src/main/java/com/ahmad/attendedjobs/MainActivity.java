package com.ahmad.attendedjobs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.ahmad.attendedjobs.adabters.adabterViewPagerMainActivity;
import com.ahmad.attendedjobs.adabters.recycleviewnotfication;
import com.ahmad.attendedjobs.customlists.attendlist;
import com.ahmad.attendedjobs.customlists.lastnewslist;
import com.ahmad.attendedjobs.customlists.listnotfication;
import com.ahmad.attendedjobs.customlists.modelViewpagerMainActivity;
import com.ahmad.attendedjobs.customlists.recyclelistMainActivity;
import com.ahmad.attendedjobs.adabters.recycleviewMainAdabter;
import com.ahmad.attendedjobs.transformationViewpager.DepthPageTransformer;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
private DrawerLayout mainactivity;
private NavigationView navigationView;
RecyclerView recycleList,recyclelistnotficatio;
    recycleviewMainAdabter adabterRecycle;
     ViewPager2 viewpagerMainactivity;
     Button  nextbtn_main,perviousbtn_main,notfibutton;
     int page;
Toolbar toolbar;
    adabterViewPagerMainActivity adabterViewPager;
    ArrayList<attendlist> attend;
FrameLayout notfiviewmain;
    View notfiViewMainActivity;
boolean showwnotfi=false;
List<listnotfication>receivedData;
recycleviewnotfication adabterNotfication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attend=getIntent().getParcelableArrayListExtra("attend");

        //intilize  menu components
        {
            mainactivity = findViewById(R.id.mainactivity);
            navigationView = findViewById(R.id.navigationView);
            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mainactivity, toolbar, R.string.OpenNavi, R.string.CloseNavi);
            mainactivity.addDrawerListener(toggle);
            mainactivity.setScrimColor(getResources().getColor(android.R.color.transparent));
            toggle.syncState();
            navigationView.setItemIconTintList(null);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
            getSupportActionBar().setTitle(null);
            navigationView.setNavigationItemSelectedListener(this);
        }
        //recycleview training center section
        {
            recycleList=findViewById(R.id.recycleList);
            adabterRecycle=new recycleviewMainAdabter(this);
            adabterRecycle.addCardItem(new recyclelistMainActivity(getString(R.string.androidfulltrack),"10:00am-1:00pm","Sun-Thu",R.drawable.android));
            adabterRecycle.addCardItem(new recyclelistMainActivity(getString(R.string.iosfulltrack),"11:00am-2:00pm","Sat-tus",R.drawable.ioslogo));
            adabterRecycle.addCardItem(new recyclelistMainActivity(getString(R.string.qapm),"9:30am-1:00pm","mon-thu",R.drawable.qa2));
            recycleList.setAdapter(adabterRecycle);
            recycleList.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        }

        // view pager components last news section
        {

           viewpagerMainactivity=findViewById(R.id.viewpagerMainactivity);
        adabterViewPager=new adabterViewPagerMainActivity(this,viewpagerMainactivity);
        adabterViewPager.addItem(new lastnewslist(getString(R.string.android),getString(R.string.androidlatestnews),"",R.drawable.android));
        adabterViewPager.addItem(new lastnewslist(getString(R.string.google),getString(R.string.GoogleLastUpdates),"",R.drawable.google));
        adabterViewPager.addItem(new lastnewslist(getString(R.string.ios),getString(R.string.LastUpdates),"",R.drawable.ioslogo));
        viewpagerMainactivity.setAdapter(adabterViewPager);
    viewpagerMainactivity.setOffscreenPageLimit(3);
    viewpagerMainactivity.setClipToPadding(false);
    DepthPageTransformer depthPageTransformer=new DepthPageTransformer(); //transformer get from class
    viewpagerMainactivity.setPageTransformer(depthPageTransformer);
    viewpagerMainactivity.setCurrentItem(1,true);
viewpagerMainactivity.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
  startActivity(new Intent(MainActivity.this, latestNews.class));
    }
});

        }
        viewpagerMainactivity.setUserInputEnabled(false);//disable swipe using tough and scroll   now u swipe using only  button if you want swip  just delete this line
        //intilize listener button next and previous for  view pager
        {
            nextbtn_main=findViewById(R.id.nextbtn_main);
            perviousbtn_main=findViewById(R.id.perviousbtn_main);
            NextAndPreviuos_viewpager(nextbtn_main,perviousbtn_main);

        }

        //framlayout AND  notification button to fetch view and  show if their any  notification
        notfibutton=findViewById(R.id.notfibutton);
        notfiviewmain=findViewById(R.id.notfiviewmain);
        //inflate vieww notification
        notfiViewMainActivity = LayoutInflater.from(MainActivity.this).inflate(R.layout.listnotfiction_view,null);
        notfiViewMainActivity.setAlpha(0f);
        notfiviewmain.addView(notfiViewMainActivity);
        //recycleview list notfication
        recyclelistnotficatio=notfiViewMainActivity.findViewById(R.id.recyclelistnotficatio);




        //add view notfication to fram when press on notfie  button
        notfibutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showwnotfi) {

                    closenotfictaion();
                } else {

                    showwnotficationlise();
                }
            }});

    }
    private void closenotfictaion() {
        AlphaAnimation animation=new AlphaAnimation(1f,0f);
        animation.setDuration(500);
        notfiViewMainActivity.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                showwnotfi=false;
                notfiViewMainActivity.setAlpha(0f);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


            private void showwnotficationlise(){
                SharedPreferences getList = getSharedPreferences("arrays", MODE_PRIVATE);
                String json = getList.getString("notfication", "");
                if (!json.isEmpty()){
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<listnotfication>>() {}.getType();
                    receivedData = gson.fromJson(json, type);
                    adabterNotfication=new recycleviewnotfication(receivedData,MainActivity.this);
                    recyclelistnotficatio.setAdapter(adabterNotfication);
                    recyclelistnotficatio.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                }else{
                    receivedData=new ArrayList<>();
                    adabterNotfication=new recycleviewnotfication(receivedData,MainActivity.this);
                    recyclelistnotficatio.setAdapter(adabterNotfication);
                    recyclelistnotficatio.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                }
if (receivedData.isEmpty()){
    Toast.makeText(this, "no notfication now", Toast.LENGTH_SHORT).show();
}
                AlphaAnimation animation=new AlphaAnimation(0f,1f);
                animation.setDuration(500);
                notfiViewMainActivity.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        notfiViewMainActivity.setAlpha(1f);
                        showwnotfi=true;
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }




            private void NextAndPreviuos_viewpager(Button nextbtnMain, Button perviousbtnMain) {
        nextbtnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewpagerMainactivity.getCurrentItem()==adabterViewPager.getItemCount()-1){
                    Toast.makeText(MainActivity.this, "you are in last pages and you will back to first page immediately ", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            viewpagerMainactivity.setCurrentItem(0,true);        }
                    },500);


                }else {
                    viewpagerMainactivity.setCurrentItem(viewpagerMainactivity.getCurrentItem()+1,true);
                }
        }});
        perviousbtn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewpagerMainactivity.getCurrentItem()==0){
                    Toast.makeText(MainActivity.this, "you are in first pages and you will go  to  last page immediately ", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewpagerMainactivity.setCurrentItem(adabterViewPager.getItemCount()-1,true);
                        }
                    },500);


                }else {
                    viewpagerMainactivity.setCurrentItem(viewpagerMainactivity.getCurrentItem()-1,true);
                }
            }

            }
        );

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.profile){
            startActivity(new Intent(this,profile.class));
        } else if (item.getItemId()==R.id.PRIVACY_POLICEY) {
            startActivity(new Intent(this,privacy.class));
        }  else if (item.getItemId()==R.id.SETTINGS) {
            startActivity(new Intent(this,settings.class));
        } else if (item.getItemId()==R.id.LOGOUT) {
            startActivity(new Intent(this,login.class));
        } else if (item.getItemId()==R.id.PAYMENT) {
            startActivity(new Intent(this,payments.class));
        } else if (item.getItemId()==R.id.JOBS) {
            startActivity(new Intent(this,jobs.class));
        }else if (item.getItemId()==R.id.latest_event) {
            startActivity(new Intent(this,latestevent.class));
        }else if (item.getItemId()==R.id.attended) {
            Intent intent = new Intent(this, attended.class);
            intent.putExtra("myParcelableData", attend);
startActivity(intent);
        }

 return true;   }




    }

