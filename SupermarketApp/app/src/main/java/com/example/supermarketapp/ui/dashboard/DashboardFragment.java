package com.example.supermarketapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.supermarketapp.MainActivity;
import com.example.supermarketapp.R;
import com.example.supermarketapp.databinding.FragmentDashboardBinding;
import com.example.supermarketapp.ui.MyAdapter;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    // storing images and descriptions in list RecyclerView https://youtu.be/18VcnYN5_LM?t=285
    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.drawable.first_leaflet, R.drawable.second_leaflet};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // Create leaflets list
        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.leaflets);
        s2 = getResources().getStringArray(R.array.description);
/*
        MyAdapter myAdapter = new MyAdapter(getContext(), s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));*/

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}