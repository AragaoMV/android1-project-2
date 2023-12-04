package com.marcos.android1_project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private Context context;
    private ArrayList contact_id, contact_name,contact_email, contact_phone;
    ContactAdapter(Context context, ArrayList contact_id, ArrayList contact_name,ArrayList contact_email,
                   ArrayList contact_phone ){
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.contato_id_text.setText(String.valueOf(contact_id.get(position)));
        holder.contato_name_text.setText(String.valueOf(contact_name.get(position)));
        holder.contato_email_text.setText(String.valueOf(contact_email.get(position)));
        holder.contato_phone_text.setText(String.valueOf(contact_phone.get(position)));
    }

    @Override
    public int getItemCount() {
        return contact_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView contato_id_text, contato_name_text, contato_email_text, contato_phone_text;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            contato_id_text = itemView.findViewById(R.id.contato_id_text);
            contato_name_text = itemView.findViewById(R.id.contato_name_text);
            contato_email_text = itemView.findViewById(R.id.contato_email_text);
            contato_phone_text = itemView.findViewById(R.id.contato_phone_text);
        }
    }
}
