package com.example.ecoguard;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    Drawable drawable;
    private TextView EcoGuardTextView;
    private ImageView imageView;
    private Button PleaseLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        // Initialize your UI components
        EcoGuardTextView = findViewById(R.id.EcoGuard);
        imageView = findViewById(R.id.imageview);

        // Set your image and text
        EcoGuardTextView.setText("EcoGuard!");

        // Your existing code for setting an image
        drawable = getResources().getDrawable(R.drawable.img);
        imageView.setImageResource(R.drawable.img);

        // Set a background task to navigate to LoginRegisterActivity after a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent LoginRegisterIntent = new Intent(MainActivity.this, LoginRegisterActivity.class);
                startActivity(LoginRegisterIntent);
                finish(); // Optional, to close MainActivity after navigation
            }
        }, 2000); // 3000 milliseconds (3 seconds) delay, you can adjust this


    }
}



