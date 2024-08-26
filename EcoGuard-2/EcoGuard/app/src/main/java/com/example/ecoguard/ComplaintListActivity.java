package com.example.ecoguard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.ecoguard.Adapter.ComplaintAdapter;
import com.example.ecoguard.Model.Complaint;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ComplaintListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewComplaints;
    private List<Complaint> complaintList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_list);

        recyclerViewComplaints = findViewById(R.id.recyclerViewComplaints);
        recyclerViewComplaints.setLayoutManager(new LinearLayoutManager(this));

        // Initialize complaintList and add some sample data
        complaintList = new ArrayList<>();

        // Display the complaint in your list or TextView
        ComplaintAdapter adapter = new ComplaintAdapter(complaintList);
        recyclerViewComplaints.setAdapter(adapter);



        // Floating action button to add new investigation
        FloatingActionButton fabAddComplaint = findViewById(R.id.fabAddComplaint);
        fabAddComplaint.setOnClickListener(v -> {
            startActivity(new Intent(ComplaintListActivity.this, AddComplaintActivity.class));
        });
    }
}
