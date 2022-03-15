package com.example.supermarketapp.ui;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermarketapp.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Call.Details> detailsList;
    private ItemClickListener mItemListener;

    Context context;
    String data1[], data2[];
    int images[];

    public MyAdapter(List<Call.Details> detailsList, ItemClickListener itemClickListener){
        this.detailsList = detailsList;
        this.mItemListener = itemClickListener;
    }

    public MyAdapter(Context ct, String s1[], String s2[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);

        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(detailsList.get(position)); // this will get the position of our item in recyclerview
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public interface ItemClickListener{
        void onItemClick(Details details);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2;
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.leaflet_title_txt);
            myText2 = itemView.findViewById(R.id.leaflet_description_txt);
            myImage = itemView.findViewById(R.id.leaflet_imageView);
        }
    }
}
