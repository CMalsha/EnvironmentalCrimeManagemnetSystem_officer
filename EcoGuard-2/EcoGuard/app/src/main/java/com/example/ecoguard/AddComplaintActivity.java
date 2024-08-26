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

import com.example.ecoguard.Model.Complaint;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddComplaintActivity extends AppCompatActivity {
    EditText editTextDate, editTextTitle, editText_description;
    Button btn_selectImage, btn_addComplaint;
    private static final int PICK_IMAGE_REQUEST = 1;
    private TextView textView_ComplaintProofUri;
    DatabaseReference databaseReference;
    StorageReference mStorageReference;
    FirebaseDatabase database;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaint);


        editTextDate = findViewById(R.id.editTextDate);
        editTextTitle = findViewById(R.id.editTextTitle);
        editText_description = findViewById(R.id.editText_description);
        btn_selectImage = findViewById(R.id.btn_selectImage);
        textView_ComplaintProofUri = findViewById(R.id.textView_ComplaintProofUri);
        btn_addComplaint = findViewById(R.id.btn_addComplaint);

        // Set onClickListener for adding an image from the gallery
        btn_selectImage.setOnClickListener(v -> openGallery());

        // Set onClickListener for adding a complaint
        btn_addComplaint.setOnClickListener(v -> addComplaint());
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
            textView_ComplaintProofUri.setText("Selected Image URI: " + imageUri.toString());
        }
    }

    private void addComplaint() {
        // Get values from EditTexts
        String date = editTextDate.getText().toString();
        String title = editTextTitle.getText().toString();
        String description = editText_description.getText().toString();

        // Get the selected image URI
        Uri imageUri = getImageUri();

        // Create a Complaint object with the collected data
        Complaint complaint = new Complaint(date, title, imageUri.toString());

        // Save the complaint to Firebase
        saveComplaint(complaint);

        // Go back to ComplaintListActivity after submitting
        Intent intent = new Intent(AddComplaintActivity.this, ComplaintListActivity.class);
        startActivity(intent);
    }

    private void saveComplaint(Complaint complaint) {
        // Initialize Firebase Database reference
        this.databaseReference = FirebaseDatabase.getInstance().getReference("complaints");

        // Generating a unique key for the new complaint
        String key = databaseReference.push().getKey();

        // Saving the complaint to the "complaints" node in Firebase Realtime Database
        databaseReference.child(key).setValue(complaint);
    }

}

