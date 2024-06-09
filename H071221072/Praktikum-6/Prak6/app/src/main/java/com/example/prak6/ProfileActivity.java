package com.example.prak6;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    private ImageView iv_profile;
    private TextView tv_name, tv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        iv_profile = findViewById(R.id.iv_profile);
        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);

        // Menerima data dari Intent
        String userName = getIntent().getStringExtra("USER_NAME");
        String userEmail = getIntent().getStringExtra("USER_EMAIL");
        String userAvatar = getIntent().getStringExtra("USER_AVATAR");

        // Menampilkan data
        tv_name.setText(userName);
        tv_email.setText(userEmail);
        Picasso.get().load(userAvatar).into(iv_profile);
    }
}
