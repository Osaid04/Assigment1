package com.example.assigment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;


    private static final String VALID_EMAIL = "osaid@gmail.com";
    private static final String VALID_PASSWORD = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {

        emailEditText = findViewById(R.id.edit_email);
        passwordEditText = findViewById(R.id.edit_password);
        loginButton = findViewById(R.id.btnlogin);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();


        if (email.equals(VALID_EMAIL) && password.equals(VALID_PASSWORD)) {
            openMainActivity2();
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void openMainActivity2() {

        Intent intent = new Intent(this, MainActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
