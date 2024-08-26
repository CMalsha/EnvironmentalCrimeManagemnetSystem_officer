package com.example.ecoguard;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;


public class LoginRegisterActivity extends AppCompatActivity {

    public static final String USERS = "users";
    EditText Email, Password;
    Button loginButton;
    TextView registerButton;
    private ProgressDialog loadingBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_btn);
        registerButton = findViewById(R.id.registerButton);

        loadingBar = new ProgressDialog(this);
        registerButton.setOnClickListener(v -> startActivity(new Intent(LoginRegisterActivity.this, RegisterActivity.class)));
        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(v -> {

            String email = Email.getText().toString();
            String password = Password.getText().toString();
            signIn(email, password);
        });
    }

    private void signIn(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginRegisterActivity.this, "Please enter Email and Password", Toast.LENGTH_SHORT).show();
            return;
        }
        loadingBar.setTitle("Login");
        loadingBar.setMessage("Please wait, while we are checking your credentials..");
        loadingBar.show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loadingBar.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginRegisterActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent dashboardIntent = new Intent(LoginRegisterActivity.this, DashboardActivity.class);
                            startActivity(dashboardIntent);
                            finish(); // Optional, to close LoginRegisterActivity after navigation
                        } else {
                            Toast.makeText(LoginRegisterActivity.this, "Login Unsuccessful, Please try Again...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
