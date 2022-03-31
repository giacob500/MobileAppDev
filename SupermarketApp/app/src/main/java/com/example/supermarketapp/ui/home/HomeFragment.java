package com.example.supermarketapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.supermarketapp.R;
import com.example.supermarketapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private TextView textView;
    private String fetoAmbizioso2;
    Bundle bundle;
    String updatedText;
    // put at the top under oncreate

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);*/
        // Hide actionbar at the top of the screen
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //updatedText = getArguments().getString("chosenSupermarket1");
/*
        final TextView textView = binding.favouriteSupermarketTextView;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
*/

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Intent intent = new Intent(get, MainActivity.class);
        //chosenSupermarket = fetoAmbizioso;
        textView = (TextView)getView().findViewById(R.id.favouriteSupermarketTextView);
        textView.setText(R.string.chosen_supermarket_textView);
/*
        Button button = getView().findViewById(R.id.choose_supermarket_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        */
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

