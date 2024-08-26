package com.example.ecoguard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    Button btn_view_complaints, btn_official_investigations, btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btn_view_complaints = findViewById(R.id.btn_view_complaints);
        btn_official_investigations = findViewById(R.id.btn_official_investigations);
        btn_logout = findViewById(R.id.btn_logout);

        String assignedTasksData = "Task1,Task2,Task3";
        String ongoingInvestigationsData = "Investigation A,InvestigationB";
        String completedActionsData = "Actions X,Actions Y";

        displayData(R.id.textAssignedTasks, assignedTasksData);
        displayData(R.id.textOngoingInvestigations, ongoingInvestigationsData);
        displayData(R.id.textCompletedTasks, completedActionsData);

        btn_view_complaints.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, ComplaintListActivity.class)));
        btn_official_investigations.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, InvestigationListActivity.class)));
    }
        private void displayData(int textViewId, String data) {
            TextView textView = findViewById(textViewId);
            textView.setText(data);
        }

        public void logout(View view){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),LoginRegisterActivity.class));
            finish();
        }
    }
