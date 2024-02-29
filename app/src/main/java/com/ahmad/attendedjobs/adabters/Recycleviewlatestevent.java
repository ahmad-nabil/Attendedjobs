package com.ahmad.attendedjobs.adabters;

import android.Manifest;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.attendedjobs.R;
import com.ahmad.attendedjobs.customlists.customlistrecycleviewlatestevent;
import com.ahmad.attendedjobs.customlists.listnotfication;

import java.util.ArrayList;
import java.util.List;

public class Recycleviewlatestevent extends RecyclerView.Adapter<Recycleviewlatestevent.RecycleviewLatestEvent> {

    Context context;
    private List<Boolean> button_notfication = new ArrayList<>();
    List<customlistrecycleviewlatestevent> soonlist = new ArrayList<>();
    List<listnotfication> listnotfications;
    List<Integer> positionlist = new ArrayList<>();
    NotificationManagerCompat notificationManager;
    String CHANNEL_ID;

    public Recycleviewlatestevent(Context context, List<listnotfication> listnotfications, String CHANNEL_ID) {
        this.listnotfications = listnotfications;
        this.CHANNEL_ID = CHANNEL_ID;
        this.context = context;
    }

    public void additems(customlistrecycleviewlatestevent addlist) {
        soonlist.add(addlist);
    }

    @NonNull
    @Override
    public RecycleviewLatestEvent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_latestevents, parent, false);
        return new RecycleviewLatestEvent(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleviewLatestEvent holder, @SuppressLint("RecyclerView") int position) {
        holder.firstline.setText(soonlist.get(position).getFirstline());
        holder.secondline.setText(soonlist.get(position).getSecondline());
        button_notfication.add(false);
        holder.rememperme.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {
                if (button_notfication.get(holder.getAdapterPosition())) {
                    AlphaAnimation fade = new AlphaAnimation(0, 1);
                    fade.setDuration(1000);
                    holder.rememperme.startAnimation(fade);
                    holder.rememperme.setImageResource(R.drawable.bell);
                    button_notfication.set(position, false);
                    notificationManager = NotificationManagerCompat.from(context);
                    Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                            .setSmallIcon(R.drawable.bell).setContentTitle("removed notfication").setPriority(NotificationCompat.PRIORITY_HIGH).build();
                    notificationManager.notify(1, notification);
//rest background when press notfi icon
                    {
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), Color.argb(100, 255, 0, 255), Color.argb(0, 255, 255, 255));
                                colorAnimator.setDuration(1000);
                                colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                    @Override
                                    public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                                        holder.frameLayout.setBackgroundColor((Integer) colorAnimator.getAnimatedValue());
                                    }
                                });
                                colorAnimator.start();

                            }
                        }, 1000);
                    }

                } else {

                    AlphaAnimation fade = new AlphaAnimation(0, 1);
                    fade.setDuration(1000);
                    holder.rememperme.startAnimation(fade);
                    button_notfication.set(position, true);
                    holder.rememperme.setImageResource(R.drawable.notfiwork);
                    listnotfications.add(new listnotfication(soonlist.get(position).getFirstline(), soonlist.get(position).getSecondline(), R.drawable.bell));
                    notificationManager = NotificationManagerCompat.from(context);
                    Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                            .setSmallIcon(R.drawable.bell).setContentTitle(soonlist.get(position).getFirstline()).setContentTitle(soonlist.get(position).getSecondline())
                            .setPriority(NotificationCompat.PRIORITY_HIGH).build();

                    notificationManager.notify(1, notification);
                    //animate background when press notfi icon
                    {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), Color.argb(100, 255, 0, 255), Color.argb(100, 0, 255, 255));
                                colorAnimator.setDuration(1000);
                                colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                    @Override
                                    public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                                        holder.frameLayout.setBackgroundColor((Integer) colorAnimator.getAnimatedValue());
                                    }
                                });
                                colorAnimator.start();
                            }
                        }, 1000);
                    }
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return soonlist.size();
    }

    public class RecycleviewLatestEvent extends RecyclerView.ViewHolder {
TextView firstline,secondline;
ImageView rememperme;
FrameLayout frameLayout;
        public RecycleviewLatestEvent(@NonNull View itemView) {
            super(itemView);
            firstline=itemView.findViewById(R.id.firstline);
            secondline=itemView.findViewById(R.id.secondline);
            rememperme=itemView.findViewById(R.id.remeberme);
            frameLayout=itemView.findViewById(R.id.framlayout);
        }
    }
}
