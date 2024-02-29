package com.ahmad.attendedjobs.adabters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.attendedjobs.R;
import com.ahmad.attendedjobs.Trainingcenter;
import com.ahmad.attendedjobs.customlists.recyclelistMainActivity;

import java.util.ArrayList;
import java.util.List;

public class recycleviewMainAdabter extends RecyclerView.Adapter<recycleviewMainAdabter.viewHolder> {
   List <recyclelistMainActivity> list=new ArrayList<>();
   Context context;
public  recycleviewMainAdabter( Context context){
    this.context=context;
}
@NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
   View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adabter_recycle_main,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.logotraining.setImageResource(list.get(position).getImg());
        holder.timeTraining.setText(list.get(position).getTime());
        holder.daysTraining.setText(list.get(position).getDays());
        holder.Training_title.setText(list.get(position).getTitlecourse());
        if (position==2){
            holder.timeTraining.setTextColor(Color.parseColor("#B225EF"));
            holder.daysTraining.setTextColor(Color.parseColor("#B225EF"));
        }
        if (position==3){
            holder.timeTraining.setTextColor(Color.parseColor("#5625EF"));
            holder.daysTraining.setTextColor(Color.parseColor("#5625EF"));
        }
holder.attendbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent pass_data=new Intent(context, Trainingcenter.class);
        pass_data.putExtra("title",list.get(position).getTitlecourse());
        pass_data.putExtra("imglogo",list.get(position).getImg());
        pass_data.putExtra("day",list.get(position).getDays());
        pass_data.putExtra("time",list.get(position).getTime());
        context.startActivity(pass_data);
    }
});
        holder.btngototrainingcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pass_data=new Intent(context, Trainingcenter.class);
                pass_data.putExtra("title",list.get(position).getTitlecourse());
                pass_data.putExtra("imglogo",list.get(position).getImg());
                pass_data.putExtra("day",list.get(position).getDays());
                pass_data.putExtra("time",list.get(position).getTime());
                context.startActivity(pass_data);
            }
        });
    }
    public void addCardItem(recyclelistMainActivity item) {
        list.add(item);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
    TextView Training_title,daysTraining,timeTraining;
    ImageView logotraining;
    Button attendbtn,btngototrainingcenter;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            Training_title=itemView.findViewById(R.id.Training_title);
            daysTraining=itemView.findViewById(R.id.daysTraining);
            timeTraining=itemView.findViewById(R.id.timeTraining);
            logotraining=itemView.findViewById(R.id.logotraining);
            attendbtn=itemView.findViewById(R.id.attendbutton);
            btngototrainingcenter=itemView.findViewById(R.id.btngototrainingcent);
        }
    }
}
