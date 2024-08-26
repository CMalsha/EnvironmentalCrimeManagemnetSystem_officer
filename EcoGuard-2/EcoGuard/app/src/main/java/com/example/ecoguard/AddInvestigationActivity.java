package com.example.ecoguard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import com.example.ecoguard.Model.Investigation;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddInvestigationActivity extends AppCompatActivity {
    EditText editTextLogNo, editTextDate, TxtInvestigationDetails;
    Spinner spinner_status;
    Button btnUploadEvidence, btnSubmit;
    private static final int PICK_IMAGE_REQUEST = 1;
    private TextView textView_InvestigationProofUri;
    DatabaseReference databaseReference;
//    StorageReference mStorageReference;
    FirebaseDatabase database;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_investigation);

        editTextLogNo = findViewById(R.id.editTextLogNo);
        spinner_status = findViewById(R.id.spinner_status);
        editTextDate = findViewById(R.id.editTextDate);
        TxtInvestigationDetails = findViewById(R.id.TxtInvestigationDetails);
        btnUploadEvidence = findViewById(R.id.btnUploadEvidence);
        textView_InvestigationProofUri = findViewById(R.id.textView_InvestigationProofUri);
        btnSubmit = findViewById(R.id.btnSubmit);

//        mStorageReference = FirebaseStorage.getInstance("gs://ecoguard-d5ba3.appspot.com").getReference("Investigations");



        // Set up the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.status_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_status.setAdapter(adapter);

        // Set onClickListener for adding an image from the gallery
        btnUploadEvidence.setOnClickListener(v -> openGallery());

        // Set onClickListener for adding a complaint
        btnSubmit.setOnClickListener(v -> addSubmit());
    }

    private Uri getImageUri() {
        // Get the selected image URI
        return null; // Modify this logic based on your requirements
    }

    private void openGallery() {
        // To open the gallery here
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    // To handle the result of the gallery selection
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            // Set the selected image URI text in the TextView
            textView_InvestigationProofUri.setText("Selected Image URI: " + imageUri.toString());
        }
    }

    private void addSubmit() {
        // Get values from EditTexts
        String logoNo = editTextLogNo.getText().toString();
        String status = spinner_status.toString();
        String date = editTextDate.getText().toString();
        String description = TxtInvestigationDetails.getText().toString();

        // Get the selected image URI
        //Uri imageUri = getImageUri();

        // Create a Complaint object with the collected data
        Investigation investigation = new Investigation(logoNo, status, date, description);

        // Save the complaint to Firebase
        saveInvestigation(investigation);

        // Go back to ComplaintListActivity after submitting
        Intent intent = new Intent(AddInvestigationActivity.this, InvestigationListActivity.class);
        startActivity(intent);
    }

    private void saveInvestigation(Investigation investigation) {
        // Initialize Firebase Database reference
        this.databaseReference = FirebaseDatabase.getInstance().getReference("investigations");

        // Generating a unique key for the new complaint
        String key = databaseReference.push().getKey();

        // Saving the complaint to the "complaints" node in Firebase Realtime Database
        databaseReference.child(key).setValue(investigation);
    }
}
