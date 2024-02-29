package com.ahmad.attendedjobs.adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.ahmad.attendedjobs.R;
import com.ahmad.attendedjobs.customlists.lastnewslist;
import com.ahmad.attendedjobs.customlists.modelViewpagerMainActivity;

import java.util.ArrayList;
import java.util.List;

public class adabterViewPagerMainActivity extends RecyclerView.Adapter<adabterViewPagerMainActivity.holder> {
private Context context;
private List <lastnewslist> modleviewpager=new ArrayList<>();
    ViewPager2 viewPagermain;

    public adabterViewPagerMainActivity(Context context, ViewPager2 viewPager2) {
        this.context = context;
        this.viewPagermain=viewPager2;
    }
    public void addItem(lastnewslist item) {
        modleviewpager.add(item);
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.adabterviewpagermain,parent,false);

        return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
holder.iconlogo.setImageResource(modleviewpager.get(position).getImg());
holder.titleviewpagermain.setText(modleviewpager.get(position).getTitle());
  holder.detailsviewpagermain.setText(modleviewpager.get(position).getIntroductionnews());

    }

    @Override
    public int getItemCount() {
        return modleviewpager.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView titleviewpagermain,detailsviewpagermain;
        ImageView iconlogo;
        public holder(@NonNull View itemView) {
            super(itemView);
            titleviewpagermain=itemView.findViewById(R.id.titleviewpagermain);
            detailsviewpagermain=itemView.findViewById(R.id.detailsviewpagermain);
            iconlogo=itemView.findViewById(R.id.iconlogo);
        }
    }

}
