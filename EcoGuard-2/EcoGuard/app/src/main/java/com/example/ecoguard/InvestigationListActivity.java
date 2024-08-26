package com.example.ecoguard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.ecoguard.Adapter.InvestigationAdapter;
import com.example.ecoguard.Model.Investigation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class InvestigationListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewInvestigations;
    private List<Investigation> investigationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investigation_list);

        recyclerViewInvestigations = findViewById(R.id.recyclerViewInvestigations);
        recyclerViewInvestigations.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the investigationList (you might populate it from a database or other source)
        investigationList = new ArrayList<>();

        // Create an adapter and set it to the RecyclerView
        InvestigationAdapter adapter = new InvestigationAdapter(investigationList);
        recyclerViewInvestigations.setAdapter(adapter);



        // Floating action button to add new investigation
        FloatingActionButton fabAddInvestigation = findViewById(R.id.fabAddInvestigation);
        fabAddInvestigation.setOnClickListener(v -> {
            startActivity(new Intent(InvestigationListActivity.this, AddInvestigationActivity.class));
        });
    }

}