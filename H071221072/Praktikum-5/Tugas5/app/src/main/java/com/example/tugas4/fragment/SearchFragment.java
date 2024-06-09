package com.example.tugas4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas4.DataSource;
import com.example.tugas4.Instagram;
import com.example.tugas4.InstagramAdapter;
import com.example.tugas4.ProfileActivity;
import com.example.tugas4.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText etSearch;
    private RecyclerView rvResults;
    private InstagramAdapter adapter;
    private List<Instagram> allInstagrams;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        etSearch = view.findViewById(R.id.et_search);
        rvResults = view.findViewById(R.id.rv_results);

        allInstagrams = DataSource.instagrams;
        adapter = new InstagramAdapter(new ArrayList<>(), this::openProfile);

        rvResults.setLayoutManager(new LinearLayoutManager(getContext()));
        rvResults.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchInstagram(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
        return view;
    }

    private void searchInstagram(String query) {
        List<Instagram> filteredList = new ArrayList<>();
        for (Instagram instagram : allInstagrams) {
            if (instagram.getUsername().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(instagram);
            }
        }
        adapter.updateList(filteredList);
    }

    private void openProfile(Instagram instagram) {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        intent.putExtra("instagram", instagram);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView IV_Home = view.findViewById(R.id.IV_Home);
        ImageView IV_Postingan = view.findViewById(R.id.IV_Post);
        ImageView IV_Profile = view.findViewById(R.id.IV_Profile);

        IV_Home.setOnClickListener(v -> {
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, homeFragment)
                    .addToBackStack(null)
                    .commit();
        });

        IV_Postingan.setOnClickListener(v -> {
            PostinganFragment postinganFragment = new PostinganFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, postinganFragment)
                    .addToBackStack(null)
                    .commit();
        });

        IV_Profile.setOnClickListener(v -> {
            ProfileFragment profileFragment = new ProfileFragment();
            FragmentManager fragmentManager = getParentFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_container, profileFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
}
