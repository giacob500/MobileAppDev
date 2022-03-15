package com.example.supermarketapp.ui.dashboard;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermarketapp.R;
import com.example.supermarketapp.databinding.FragmentDashboardBinding;
import com.example.supermarketapp.ui.Leaflet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.leaflets_list);

        MyAdapter myAdapter = new MyAdapter(createTestData());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    private List<Leaflet> createTestData() {
        ArrayList<Leaflet> testdata = new ArrayList<>();

        Leaflet l1 = new Leaflet();
        l1.setTitle("10/03-16/03");
        l1.setDescription("Weekly Leaflet");

        Leaflet l2 = new Leaflet();
        l2.setTitle("17/03-21/03 ");
        l2.setDescription("Weekly Leaflet");

        Drawable drawable = getResources().getDrawable(R.drawable.first_leaflet);
        l1.setImage(drawable);
        testdata.add(l1);

        drawable = getResources().getDrawable(R.drawable.second_leaflet);
        l2.setImage(drawable);
        testdata.add(l2);
/*
        // Code to use for undefined number of leaflets
        Leaflet lSample = new Leaflet();
        Drawable drawable;

        for (int i = 0; i < 2; i++) {
            lSample.setTitle("Date");
            lSample.setDescription("Description");
            drawable = getResources().getDrawable(R.drawable.first_leaflet);
            lSample.setImage(drawable);
            testdata.add(lSample);
        }
*/
        return testdata;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        // Model
        private List<Leaflet> leaflets;

        public MyAdapter( List<Leaflet> leaflets){
            this.leaflets = leaflets;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.my_row, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            System.out.println(position);
            holder.leaflet_title_txt.setText(leaflets.get(position).getTitle());
            holder.myImage.setImageDrawable(leaflets.get(position).getImage());
            //holder.myText2.setText(data2[position]);
           // holder.myImage.setImageResource(images[position]);

           // holder.itemView.setOnClickListener(view -> {
                //mItemListener.onItemClick(detailsList.get(position)); // this will get the position of our item in recyclerview
            //});
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(getContext(), "Leaflet downloaded", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return leaflets.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView leaflet_title_txt, myText2;
            ImageView myImage;
            ConstraintLayout constraintLayout;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                leaflet_title_txt = itemView.findViewById(R.id.leaflet_title_txt);
                myText2 = itemView.findViewById(R.id.leaflet_description_txt);
                myImage = itemView.findViewById(R.id.leaflet_imageView);
                constraintLayout = itemView.findViewById(R.id.constraint_layout);
            }
        }
    }
}

