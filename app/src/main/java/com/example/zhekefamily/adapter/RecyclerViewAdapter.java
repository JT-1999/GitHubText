package com.example.zhekefamily.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhekefamily.R;
import com.example.zhekefamily.activity.Users_item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private List<Users_item> muserslist;
    static  class MyViewHolder extends RecyclerView.ViewHolder{
        private View usersview;
        ImageView usersImage;
        TextView usersName;
        Button usersDelete;


    public MyViewHolder(View view)
    {
        super(view);
        usersview=view;
        usersImage=view.findViewById(R.id.photo);
        usersName=view.findViewById(R.id.text);
        usersDelete=view.findViewById(R.id.delete);
    }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        holder.usersview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Users_item users=muserslist.get(position);
                Toast.makeText(view.getContext(), users.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.usersImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Users_item users=muserslist.get(position);
                Toast.makeText(view.getContext(), users.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.usersDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Users_item users=muserslist.get(position);
                Toast.makeText(view.getContext(), users.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Users_item users=muserslist.get(position);
        holder.usersImage.setImageResource(users.getimageID());
        holder.usersName.setText(users.getName());

    }

    @Override
    public int getItemCount() {
        return muserslist.size();
    }
    public RecyclerViewAdapter(List<Users_item> userslist)
    {
        muserslist=userslist;
    }
}
