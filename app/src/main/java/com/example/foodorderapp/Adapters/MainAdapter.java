package com.example.foodorderapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.DetailActivity;
import com.example.foodorderapp.MainActivity;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewholder>
{

    ArrayList<MainModel> list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.sample_foodlist,parent,false);
        return new viewholder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        final MainModel model = list.get(position);
        holder.foodimage.setImageResource(model.getImage());
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.description.setText(model.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);

                intent.putExtra("image",model.getImage());
                intent.putExtra("name",model.getName());
                intent.putExtra("description",model.getDescription());
                intent.putExtra("price",model.getPrice());
                intent.putExtra("type",1);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView foodimage;
        TextView name, price, description;
        CardView cardView;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            foodimage = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.txtName);
            price = itemView.findViewById(R.id.txtPrize);
            description =itemView.findViewById(R.id.txtDescription);

            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
