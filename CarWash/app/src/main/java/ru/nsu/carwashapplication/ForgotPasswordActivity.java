package ru.nsu.carwashapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText emailEditText;
    private Button resetPasswordButton;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = (EditText) findViewById(R.id.email);
        resetPasswordButton = (Button) findViewById(R.id.resetPassword);

        auth = FirebaseAuth.getInstance();
        
        resetPasswordButton.setOnClickListener(v -> resetPassword());
    }
    
    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()) {
            emailEditText.setError("Нет почты");
            emailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Пожалуйста, используйте правильную почту");
            emailEditText.requestFocus();
            return;
        }

        auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(ForgotPasswordActivity.this, "Проверьте свою почту, сообщение может быть в спаме", Toast.LENGTH_LONG).show();
                startActivity(new Intent(ForgotPasswordActivity.this, LogInPage.class));

            }else {
                Toast.makeText(ForgotPasswordActivity.this, "Такой почты не существует", Toast.LENGTH_LONG).show();
                emailEditText.requestFocus();
            }
        });
    }
}