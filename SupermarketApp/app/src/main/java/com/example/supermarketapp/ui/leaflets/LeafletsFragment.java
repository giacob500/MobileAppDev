package com.example.supermarketapp.ui.leaflets;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.supermarketapp.databinding.FragmentLeafletsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class LeafletsFragment extends Fragment {

    private FragmentLeafletsBinding binding;
    RecyclerView recyclerView;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLeafletsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Show actionbar at the top of the screen
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        // Initialize variables
        recyclerView = root.findViewById(R.id.leaflets_list);
        MyAdapter myAdapter = new MyAdapter(createTestData());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    // Create leaflets data
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
            View view = inflater.inflate(R.layout.single_leaflet, parent, false);
            return new MyViewHolder(view);
        }

        // Put leaflets data in the carousel
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            System.out.println(position);
            holder.leafletTitle.setText(leaflets.get(position).getTitle());
            holder.leafletDescription.setText(leaflets.get(position).getDescription());
            holder.leafletThumbnail.setImageDrawable(leaflets.get(position).getImage());
            // Download pdf when leaflet is clicked
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    storageReference = firebaseStorage.getInstance().getReference();
                    ref = storageReference.child("leaflets/10-03-16-03.pdf");
                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            downloadFile(getContext(), "10-03-16-03", ".pdf", DIRECTORY_DOWNLOADS, uri.toString());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
                    Toast toast = Toast.makeText(getContext(), "Leaflet downloaded", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return leaflets.size();
        }

        // Associate carousel elements with layout elements
        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView leafletTitle, leafletDescription;
            ImageView leafletThumbnail;
            ConstraintLayout constraintLayout;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                leafletTitle = itemView.findViewById(R.id.leaflet_title_txt);
                leafletDescription = itemView.findViewById(R.id.leaflet_description_txt);
                leafletThumbnail = itemView.findViewById(R.id.leaflet_imageView);
                constraintLayout = itemView.findViewById(R.id.constraint_layout);
            }
        }

        // Method to download from web url and save in device's filesystem
        public void downloadFile(Context context, String fileName, String fileExtension, String destinationDirectory, String url){
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(url);
            DownloadManager.Request request = new DownloadManager.Request(uri);

            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalFilesDir(context, destinationDirectory, fileName + fileExtension);

            downloadManager.enqueue(request);
        }
    }
}

