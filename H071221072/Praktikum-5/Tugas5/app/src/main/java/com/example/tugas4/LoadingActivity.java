package com.example.tugas4;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoadingActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        handler = new Handler();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Run the background task
        executor.execute(() -> {
            Intent intent = getIntent();
            Instagram instagram = intent.getParcelableExtra("instagram");
            // Simulate background process, e.g., network or database operation
            try {
                Thread.sleep(2000); // Simulating delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // After the background task is done, start ProfileActivity
            handler.post(() -> {
                Intent profileIntent = new Intent(LoadingActivity.this, ProfileActivity.class);
                profileIntent.putExtra("instagram", instagram);
                startActivity(profileIntent);
                finish(); // Close LoadingActivity
            });
        });
    }
}
