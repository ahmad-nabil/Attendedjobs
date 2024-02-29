package com.ahmad.attendedjobs.adabters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.attendedjobs.R;
import com.ahmad.attendedjobs.customlists.listnotfication;
import com.google.gson.Gson;

import java.util.*;

public class recycleviewnotfication extends RecyclerView.Adapter<recycleviewnotfication.holder> {
    List<listnotfication> list;
    Context context;
SharedPreferences sharedPreferences;
    public recycleviewnotfication(List<listnotfication> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewnotfication,parent,false);
        return new holder(v);
    }
public void removeitem(int position){
        list.remove(position);
        notifyDataSetChanged();
}
    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
            holder.introductionotfi.setText(list.get(position).getIntro());
            holder.titlenotfi.setText(list.get(position).getTitle());
            holder.logonotfi.setImageResource(list.get(position).getImg());
            sharedPreferences=context.getSharedPreferences("arrays",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();

holder.dismissnotfication.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        removeitem(position);
        Gson gson = new Gson();
        String notfication=gson.toJson(list);
        editor.putString("notfication", notfication);
        editor.commit();
        notifyItemRemoved(position);
    }
});

    }

    @Override
    public int getItemCount() {

         return list.size();


    }

    class holder extends RecyclerView.ViewHolder{
Button dismissnotfication;
ImageView logonotfi;
TextView introductionotfi,titlenotfi;
        public holder(@NonNull View itemView) {
            super(itemView);
            dismissnotfication=itemView.findViewById(R.id.dismissnotfication);
            logonotfi=itemView.findViewById(R.id.logonotfi);
            introductionotfi=itemView.findViewById(R.id.introductionotfi);
            titlenotfi=itemView.findViewById(R.id.titlenotfi);
        }
    }
}
