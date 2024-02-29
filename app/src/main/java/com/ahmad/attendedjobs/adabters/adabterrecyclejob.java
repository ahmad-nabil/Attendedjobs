package com.ahmad.attendedjobs.adabters;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmad.attendedjobs.R;
import com.ahmad.attendedjobs.customlists.recycleListjop;
import com.ahmad.attendedjobs.jobs;

import java.util.ArrayList;
import java.util.List;

public class adabterrecyclejob extends RecyclerView.Adapter<adabterrecyclejob.holderjops> {
    List <recycleListjop> list=new ArrayList<>();
    private Context context;
    Activity activity;

    public adabterrecyclejob(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void additems(recycleListjop items){
        list.add(items);
}
    @NonNull
    @Override
    public holderjops onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewjobslist,parent,false);

        return new holderjops(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderjops holder, int position) {
        holder.titlejop.setText(list.get(position).getTitlejop());
        holder.experiencxesjop.setText(list.get(position).getExpierence());
        holder.logojop.setImageResource(list.get(position).getLogoimgjob());
holder.uploadcv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        //check permission if you r get permission or not

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){

            Intent uplodcv=new Intent(Intent.ACTION_GET_CONTENT);
            uplodcv.setType("application/pdf");
            ((Activity)context).startActivityForResult(uplodcv,1);
        }else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }


    }
});
holder.btnATtend.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            Intent uplodcv=new Intent(Intent.ACTION_GET_CONTENT);
            uplodcv.setType("application/pdf");
            ((Activity)context).startActivityForResult(uplodcv,1);
        }else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holderjops extends RecyclerView.ViewHolder {
        TextView experiencxesjop,titlejop;
        ImageView logojop;
        Button uploadcv,btnATtend;
        public holderjops(@NonNull View itemView) {
            super(itemView);
            experiencxesjop=itemView.findViewById(R.id.experiencxesjop);
            titlejop=itemView.findViewById(R.id.titlejop);
            logojop=itemView.findViewById(R.id.logojop);
            uploadcv=itemView.findViewById(R.id.uplodcv);
            btnATtend=itemView.findViewById(R.id.btnATtend);
        }
    }

}
