package com.example.ecoguard;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import java.util.regex.Pattern;

import com.example.ecoguard.Model.UserModel;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;

public class RegisterActivity extends AppCompatActivity {
    public static final String USERS = "users";
    EditText email, userName, password, confirmPassword, phoneNumber;
    Button btn_Register;
    TextView loginBtn;
    UserModel UserModel;
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    Pattern pat = Pattern.compile(emailRegex);
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseDatabase database;
    private DatabaseReference Ref;

    @SuppressLint("MissingInflatedId")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        phoneNumber = findViewById(R.id.phoneNumber);
        btn_Register = findViewById(R.id.btn_register);
        loginBtn = findViewById(R.id.loginButton);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        Ref = database.getReference().child(USERS);


        loginBtn.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginRegisterActivity.class)));

        btn_Register.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();
            String userUsername = userName.getText().toString().trim();
            String userPassword = password.getText().toString().trim();
            String userConfirmPassword = confirmPassword.getText().toString().trim();
            String userPhoneNumber = phoneNumber.getText().toString().trim();

            if (userEmail.isEmpty() || !pat.matcher(userEmail).matches()) {
                email.setError("Please Enter a valid Email");
                return;
            } else if (!userUsername.matches("^[a-z0-9]+$")) {
                userName.setError("Use lowercase and numbers");
                return;
            } else if (userPassword.isEmpty() || userPassword.length() < 6) {
                password.setError("Please enter a password with at least 6 characters");
                return;
            } else if (!userConfirmPassword.equals(userPassword)) {
                confirmPassword.setError("Passwords don't match");
                return;
            } else if (userPhoneNumber.isEmpty() || userPhoneNumber.length() < 10){
                phoneNumber.setError("Please enter a phone number with 10 digits");
            }

            mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),LoginRegisterActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

//    private void PerformAuth() {
//        String userEmail = email.getText().toString();
//        String userUsername = userName.getText().toString();
//        String userPassword = password.getText().toString();
//        String userConfirmPassword = confirmPassword.getText().toString();
//        String userPhoneNumber = phoneNumber.getText().toString();
//
//        if (userEmail.isEmpty() || !pat.matcher(userEmail).matches()) {
//            email.setError("Please Enter a valid Email");
//            return;
//        } else if (!userUsername.matches("^[a-z0-9]+$")) {
//            userName.setError("Use lowercase and numbers");
//            return;
//        } else if (userPassword.isEmpty() || userPassword.length() < 6) {
//            password.setError("Please enter a password with at least 6 characters");
//            return;
//        } else if (!userConfirmPassword.equals(userPassword)) {
//            confirmPassword.setError("Passwords don't match");
//            return;
//        } else if (userPhoneNumber.isEmpty() || userPhoneNumber.length() < 10){
//            phoneNumber.setError("Please enter a phone number with 10 digits");
//        }
//
//        mAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(task -> {
//
//            if (task.isSuccessful()) {
//
//                FirebaseUser firebaseUser = mAuth.getCurrentUser();
//                assert firebaseUser != null;
//                String userId = firebaseUser.getUid();
//
//                Ref = FirebaseDatabase.getInstance().getReference().child(USERS).child(userId);
//                UserModel model = new UserModel(userId, userEmail, userUsername, userPassword, userPhoneNumber);
//
//                FirebaseDatabase.getInstance("https://ecoguard-d5ba3-default-rtdb.firebaseio.com/").getReference("Users").
//                        child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).
//                        setValue(model).addOnCompleteListener(task1 -> {
//                            if (task1.isSuccessful()){
//                                Toast.makeText(RegisterActivity.this, "Registration successfully", Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(getApplicationContext(),LoginRegisterActivity.class));
//                            } else {
//                                Toast.makeText(RegisterActivity.this, "Registration successfully", Toast.LENGTH_LONG).show();
//                            }
//                        });
//            } else {
////                progressDialog.dismiss();
//                Toast.makeText(RegisterActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}