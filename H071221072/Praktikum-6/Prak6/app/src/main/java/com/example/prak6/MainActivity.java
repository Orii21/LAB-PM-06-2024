package com.example.prak6;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ApiService apiService;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private Button btnLoadMore;
    private int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = RetrofitClient.getClient().create(ApiService.class);
        recyclerView = findViewById(R.id.rv_profile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnLoadMore = findViewById(R.id.btn_load);

        userAdapter = new UserAdapter(this);
        recyclerView.setAdapter(userAdapter);

        fetchUsers(currentPage);

        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage++;
                fetchUsers(currentPage);
            }
        });
    }

    private void fetchUsers(int page) {
        Call<UserResponse> call = apiService.getUsers(page);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body().getData();
                    userAdapter.addUsers(users);
                } else {
                    Log.e(TAG, "Response tidak berhasil atau body null");
                    Toast.makeText(MainActivity.this, "Gagal mendapatkan respons", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e(TAG, "Panggilan jaringan gagal", t);
                Toast.makeText(MainActivity.this, "Panggilan jaringan gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
