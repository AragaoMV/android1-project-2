package com.marcos.android1_project2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList contact_id, contact_name,contact_email, contact_phone;
    ContactAdapter(Activity activity,Context context, ArrayList contact_id, ArrayList contact_name,ArrayList contact_email,
                   ArrayList contact_phone ){

    this.activity = activity;
    this.context = context;
    this.contact_id = contact_id;
    this.contact_name = contact_name;
    this.contact_email = contact_email;
    this.contact_phone = contact_phone;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_card, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.contato_id_text.setText(String.valueOf(contact_id.get(position)));
        holder.contato_name_text.setText(String.valueOf(contact_name.get(position)));
        holder.contato_email_text.setText(String.valueOf(contact_email.get(position)));
        holder.contato_phone_text.setText(String.valueOf(contact_phone.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(contact_id.get(position)));
                intent.putExtra("name",String.valueOf(contact_name.get(position)));
                intent.putExtra("email",String.valueOf(contact_email.get(position)));
                intent.putExtra("phone",String.valueOf(contact_phone.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contact_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView contato_id_text, contato_name_text, contato_email_text, contato_phone_text;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contato_id_text = itemView.findViewById(R.id.contato_id_text);
            contato_name_text = itemView.findViewById(R.id.contato_name_text);
            contato_email_text = itemView.findViewById(R.id.contato_email_text);
            contato_phone_text = itemView.findViewById(R.id.contato_phone_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
