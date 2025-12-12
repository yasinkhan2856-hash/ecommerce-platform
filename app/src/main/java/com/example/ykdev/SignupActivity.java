package com.example.ykdev;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btnSignup;
    private TextView loginLink;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupscreen);

        // Firebase instance
        mAuth = FirebaseAuth.getInstance();

        // Match your XML IDs
        email = findViewById(R.id.input_email_address);
        password = findViewById(R.id.input_password);
        btnSignup = findViewById(R.id.btn_signup);
        loginLink = findViewById(R.id.login_link);

        // ---- SIGN UP BUTTON ----
        btnSignup.setOnClickListener(v -> {
            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();

            if (TextUtils.isEmpty(userEmail)) {
                email.setError("Enter Email");
                return;
            }
            if (TextUtils.isEmpty(userPassword)) {
                password.setError("Enter Password");
                return;
            }
            if (userPassword.length() < 6) {
                password.setError("Password must be at least 6 characters");
                return;
            }

            // Firebase signup call
            mAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(SignupActivity.this,
                                        "Signup Successful!", Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(SignupActivity.this, loginActivity.class));
                                finish();

                            } else {
                                Toast.makeText(SignupActivity.this,
                                        "Error: " + task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        });

        // ---- Already have account → Login ----
        loginLink.setOnClickListener(v ->
                startActivity(new Intent(SignupActivity.this, loginActivity.class))
        );
    }
}
