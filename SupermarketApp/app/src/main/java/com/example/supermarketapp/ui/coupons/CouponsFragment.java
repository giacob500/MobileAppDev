package com.example.supermarketapp.ui.coupons;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermarketapp.R;
import com.example.supermarketapp.databinding.FragmentCouponsBinding;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class CouponsFragment extends Fragment {
    RecyclerView recyclerView;
    //Button couponButton;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;
    private FragmentCouponsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCouponsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Show actionbar at the top of the screen
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        recyclerView = root.findViewById(R.id.coupons_list);

        //couponButton = getActivity().findViewById(R.id.activateCouponButton);


        CouponsFragment.MyAdapter myAdapter = new CouponsFragment.MyAdapter(createTestData());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private List<Coupon> createTestData() {
        ArrayList<Coupon> testdata = new ArrayList<>();

        Coupon c1 = new Coupon();
        c1.setTitle("Maribel Jam & Marmelade");
        c1.setExpiration("Expires today");

        Coupon c2 = new Coupon();
        c2.setTitle("Rowan Hill French-style Pastries");
        c2.setExpiration("Expires tomorrow");

        Coupon c3 = new Coupon();
        c3.setTitle("Naturis Chilled Smoothies");
        c3.setExpiration("Expires tomorrow");

        Coupon c4 = new Coupon();
        c4.setTitle("Alesto Almonds");
        c4.setExpiration("Expires tomorrow");

        Drawable drawable = getResources().getDrawable(R.drawable.coupon4);
        c1.setImage(drawable);
        testdata.add(c1);

        drawable = getResources().getDrawable(R.drawable.coupon3);
        c2.setImage(drawable);
        testdata.add(c2);

        drawable = getResources().getDrawable(R.drawable.coupon2);
        c3.setImage(drawable);
        testdata.add(c3);

        drawable = getResources().getDrawable(R.drawable.coupon1);
        c4.setImage(drawable);
        testdata.add(c4);

        return testdata;

    }

    public class MyAdapter extends RecyclerView.Adapter<CouponsFragment.MyAdapter.MyViewHolder> {

        // Model
        private List<Coupon> coupons;

        public MyAdapter( List<Coupon> coupons){
            this.coupons = coupons;
        }

        @NonNull
        @Override
        public CouponsFragment.MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.single_coupon, parent, false);
            return new CouponsFragment.MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CouponsFragment.MyAdapter.MyViewHolder holder, int position) {
            System.out.println(position);
            holder.couponTitleTxt.setText(coupons.get(position).getTitle());
            if(coupons.get(position).getExpiration().equals("Expires today")){
                holder.couponExpiration.setTextColor(Color.RED);
            }
            holder.couponExpiration.setText(coupons.get(position).getExpiration());
            holder.couponThumbnail.setImageDrawable(coupons.get(position).getImage());
            //holder.button.se
            //holder.myText2.setText(data2[position]);
            // holder.myImage.setImageResource(images[position]);

            // holder.itemView.setOnClickListener(view -> {
            //mItemListener.onItemClick(detailsList.get(position)); // this will get the position of our item in recyclerview
            //});
            holder.couponButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(holder.couponButton.getText().equals("Active")){
                        holder.couponButton.setText("Activate");
                        Toast toast = Toast.makeText(getContext(), "Coupon deactivated", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        holder.couponButton.setText("Active");
                        Toast toast = Toast.makeText(getContext(), "Coupon activated", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return coupons.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView couponTitleTxt, couponExpiration;
            ImageView couponThumbnail;
            Button couponButton;
            ConstraintLayout constraintLayout;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                couponTitleTxt = itemView.findViewById(R.id.coupon_title_txt);
                couponExpiration = itemView.findViewById(R.id.coupon_description_txt);
                couponThumbnail = itemView.findViewById(R.id.coupon_imageView);
                couponButton = itemView.findViewById(R.id.activateCouponButton);
                constraintLayout = itemView.findViewById(R.id.constraint_layout2);
            }
        }
    }
}