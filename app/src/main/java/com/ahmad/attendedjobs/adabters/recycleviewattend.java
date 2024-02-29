package com.ahmad.attendedjobs.adabters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.attendedjobs.R;
import com.ahmad.attendedjobs.customlists.attendlist;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class recycleviewattend extends RecyclerView.Adapter<recycleviewattend.attend> {
    @NonNull

    Context context;
    List<attendlist>attendlists;
    List<attendlist>training;
    List<attendlist>event;
int identfier;
SharedPreferences sharedPreferences;
    public recycleviewattend(@NonNull Context context,List<attendlist> attendlists, List<attendlist>event, List<attendlist>training) {
        this.context = context;
        this.attendlists = attendlists;
this.event=event;
this.training=training;
    }


    public attend onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleattendlist,parent,false);
        return new attend(view);
    }
    public void removeItem(int position) {
        attendlists.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onBindViewHolder(@NonNull attend holder, int position) {
        holder.titleeattend.setText(attendlists.get(position).getTitle());
        holder.introductionattend.setText(attendlists.get(position).getIntroduction() +"\n"+ attendlists.get(position).getInfo());
        holder.logoattended.setImageResource(attendlists.get(position).getImg());
        sharedPreferences=context.getSharedPreferences("arrays",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        holder.dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
if (attendlists.get(adapterPosition).getUniqueIdentifier()==1){
    training.remove(attendlists.get(adapterPosition));
    removeItem(adapterPosition);
} else if (attendlists.get(adapterPosition).getUniqueIdentifier()==2) {
    event.remove(attendlists.get(adapterPosition));
    removeItem(adapterPosition);

}


                }


        }
    });
    }

    @Override
    public int getItemCount() {
        return attendlists.size();
    }

    class attend extends RecyclerView.ViewHolder{
TextView titleeattend,introductionattend;
ImageView logoattended;
Button dismiss;
        public attend(@NonNull View itemView) {
            super(itemView);
            titleeattend=itemView.findViewById(R.id.titleeattend);
            introductionattend=itemView.findViewById(R.id.introductioattend);
            logoattended=itemView.findViewById(R.id.logoattend);
            dismiss=itemView.findViewById(R.id.dismiss);
        }
    }
}
