package com.example.recycleviewex;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> dataSet;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomeAdapter adapter;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize dataset, RecyclerView, layout manager, and adapter
        dataSet = new ArrayList<>();
        recyclerView = findViewById(R.id.resView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        editTextSearch = findViewById(R.id.editTextSearch);

        // Populate dataset with data from myData
        for (int i = 0; i < myData.nameArray.length; i++) {
            dataSet.add(new DataModel(
                    myData.nameArray[i],
                    myData.DescriptionArray[i],
                    myData.drawableArray[i],
                    myData.id_[i]
            ));
        }

        // Initialize and set adapter
        adapter = new CustomeAdapter(dataSet);
        recyclerView.setAdapter(adapter);

        // Set up search functionality
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString()); // Filter dataset based on search query
            }
        });

        // Set click listener on adapter to handle item clicks
        adapter.setOnItemClickListener(new CustomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataModel dataModel) {
                // Handle item click here
                Toast.makeText(MainActivity.this, "Clicked on: " + dataModel.getName(), Toast.LENGTH_SHORT).show();
                // Example: Open DetailsActivity with the clicked item's data
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("name", dataModel.getName());
                intent.putExtra("Description", dataModel.getVersion());
                intent.putExtra("image", dataModel.getImage());
                startActivity(intent);
            }
        });
    }

    // Function to filter dataset based on search query
    private void filter(String text) {
        ArrayList<DataModel> filteredList = new ArrayList<>();
        for (DataModel item : dataSet) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList); // Update adapter with filtered list
    }
}
