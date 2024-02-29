package com.ahmad.attendedjobs.adabters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.attendedjobs.R;
import com.ahmad.attendedjobs.customlists.attendlist;
import com.ahmad.attendedjobs.customlists.customevntviewpager;
import com.ahmad.attendedjobs.customlists.listnotfication;
import com.ahmad.attendedjobs.latestevent;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class adabetrViewpagerlatestEvents extends RecyclerView.Adapter<adabetrViewpagerlatestEvents.lateesteventViewpager> {
  private Context context;
    List<customevntviewpager> eventlist=new ArrayList<>();
    List<attendlist> list;
List <Integer>positionlist=new ArrayList<>();
Activity activity;
    public adabetrViewpagerlatestEvents(Context context , List<attendlist> list ,Activity activity) {
        this.list=list;
        this.context = context;
        this.activity=activity;
    }
public   void addevents(customevntviewpager customevntviewpager){
        eventlist.add(customevntviewpager);
}
    @NonNull
    @Override
    public lateesteventViewpager onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpagerlatestevent,parent,false);

        return new lateesteventViewpager(view);
    }

    @Override
    public void onBindViewHolder(@NonNull lateesteventViewpager holder, @SuppressLint("RecyclerView") int position) {
        holder.title_event.setText(eventlist.get(position).getEventtitle());
        holder.datesevent.setText(eventlist.get(position).getDateevent());
        holder.daysevent.setText(eventlist.get(position).getDayevent());
        holder.timesevent.setText(eventlist.get(position).getTimeevent());
    holder.btnjoin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (positionlist.contains(position)){
                Toast.makeText(context, "you already joined", Toast.LENGTH_SHORT).show();

            }else {
                list.add(new attendlist(eventlist.get(position).getEventtitle(),eventlist.get(position).getDateevent()+"\n"
                        +eventlist.get(position).getDayevent()+"\n"+eventlist.get(position).getTimeevent(),"",R.drawable.bell,2));
                positionlist.add(position);
            }


        }
    });
    }

    @Override
    public int getItemCount() {
        return eventlist.size();
    }

    public class lateesteventViewpager extends RecyclerView.ViewHolder {
        TextView title_event,daysevent,datesevent,timesevent;
        Button btnjoin;
        public lateesteventViewpager(@NonNull View itemView) {
            super(itemView);
            timesevent=itemView.findViewById(R.id.timesevent);
            daysevent=itemView.findViewById(R.id.daysevent);
            datesevent=itemView.findViewById(R.id.datesevent);
            title_event=itemView.findViewById(R.id.title_event);
            btnjoin=itemView.findViewById(R.id.btnjoin);

        }
    }
}
