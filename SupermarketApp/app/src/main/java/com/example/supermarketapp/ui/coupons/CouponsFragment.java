package com.example.supermarketapp.ui.coupons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.supermarketapp.databinding.FragmentCouponsBinding;

public class CouponsFragment extends Fragment {

    private CouponsViewModel couponsViewModel;
    private FragmentCouponsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        couponsViewModel =
                new ViewModelProvider(this).get(CouponsViewModel.class);

        binding = FragmentCouponsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Show actionbar at the top of the screen
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

        final TextView textView = binding.textCoupons;
        couponsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}