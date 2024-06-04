package com.example.recycleviewex;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Receive data from MainActivity
        String name = getIntent().getStringExtra("name");
        String Description = getIntent().getStringExtra("Description");
        int imageResId = getIntent().getIntExtra("image", 0);

        // Find views in the layout
        TextView nameTextView = findViewById(R.id.textViewNameDetails);
        TextView versionTextView = findViewById(R.id.textViewDescriptionDetails);
        ImageView imageView = findViewById(R.id.imageViewDetails);

        // Set data to views
        nameTextView.setText(name);
        versionTextView.setText(Description);
        imageView.setImageResource(imageResId);
    }
}
