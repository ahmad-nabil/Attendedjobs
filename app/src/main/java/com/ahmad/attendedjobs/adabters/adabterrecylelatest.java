package com.ahmad.attendedjobs.adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.attendedjobs.R;
import com.ahmad.attendedjobs.customlists.lastnewslist;

import java.util.ArrayList;
import java.util.List;

public class adabterrecylelatest extends RecyclerView.Adapter<adabterrecylelatest.holderlatestnews> {
   private Context context;
List<lastnewslist> news=new ArrayList<>();

    public adabterrecylelatest(Context context) {
        this.context = context;
    }

public void addnews(lastnewslist add){
        news.add(add);
}
    @NonNull
    @Override
    public holderlatestnews onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewlatestnews,parent,false);

        return new holderlatestnews(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderlatestnews holder, int position) {
        holder.titlenews.setText(news.get(position).getTitle());
        holder.introductionnews.setText(news.get(position).getIntroductionnews());
        holder.completnews.setText(news.get(position).getCompleationnews());
        holder.iconnews.setImageResource(news.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class holderlatestnews extends RecyclerView.ViewHolder {

TextView titlenews,introductionnews,completnews;
ImageView iconnews;
        public holderlatestnews(@NonNull View itemView) {
            super(itemView);
            titlenews=itemView.findViewById(R.id.titlenews);
            introductionnews=itemView.findViewById(R.id.introductionnews);
            completnews=itemView.findViewById(R.id.completnews);
            iconnews=itemView.findViewById(R.id.iconnews);

        }
    }
}
