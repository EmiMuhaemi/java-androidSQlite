package com.example.undipssql;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterView extends RecyclerView.Adapter<AdapterView.MyViewHolder> {

    private Context context;
    private ArrayList universityId, universityName, universityAddress;

    AdapterView(Context context, ArrayList universityId, ArrayList universityName, ArrayList universityAddress){

        this.context = context;
        this.universityId = universityId;
        this.universityName = universityName;
        this.universityAddress = universityAddress;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_data, parent, false);
        return new MyViewHolder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.univName_txt.setText(String.valueOf(universityName.get(position)));
        holder.univAddress_txt.setText(String.valueOf(universityAddress.get(position)));
        holder.univId_txt.setText(String.valueOf(universityId.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailUniversity.class);
                intent.putExtra("id", String.valueOf(universityId.get(position)));
                intent.putExtra("name", String.valueOf(universityName.get(position)));
                intent.putExtra("address", String.valueOf(universityAddress.get(position)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return universityId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView univName_txt, univAddress_txt, univId_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            univName_txt = itemView.findViewById(R.id.univName_txt);
            univAddress_txt = itemView.findViewById(R.id.univAddress_txt);
            univId_txt = itemView.findViewById(R.id.univId_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
