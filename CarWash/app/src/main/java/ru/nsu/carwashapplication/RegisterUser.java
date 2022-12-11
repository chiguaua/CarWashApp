package ru.nsu.carwashapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.nsu.carwashapplication.model.Client;
import ru.nsu.carwashapplication.model.globalVar;
import ru.nsu.carwashapplication.retrofit.ClientApi;
import ru.nsu.carwashapplication.retrofit.RetrofitServiceJson;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {


    private TextView banner, registerUser;
    private EditText editTextfullName, editTextAge, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextfullName = (EditText) findViewById(R.id.fullName);
        editTextAge = (EditText) findViewById(R.id.age);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
                startActivity(new Intent(this, LogInPage.class));
                break;
            case R.id.registerUser:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullName = editTextfullName.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        if(fullName.isEmpty()) {
            editTextfullName.setError("No full name");
            editTextfullName.requestFocus();
            return;
        }

        if (age.isEmpty()){
            editTextAge.setError("No age");
            editTextAge.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("No email");
            editTextEmail.requestFocus();
            return;
        }
        /*if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please use valid email");
            editTextEmail.requestFocus();
            return;
        }*/
        if(password.isEmpty()){
            editTextPassword.setError("No password");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() <2){
            editTextPassword.setError("No short password");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        Client client = new Client();
        client.setName(fullName);
        client.setPassword(password);
        client.setEmail(email);
        client.setPhone(age); //!!!!!!!

        RetrofitServiceJson retrofitService = new RetrofitServiceJson();
        ClientApi clientApi = retrofitService.getRetrofit().create(ClientApi.class);

        clientApi.save(client)
                .enqueue(new Callback<Client>() {
                    @Override
                    public void onResponse(@NonNull Call<Client> call, @NonNull Response<Client> response) {
                        Toast.makeText(RegisterUser.this,"Регистрация прошла", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        globalVar.setUserMail(email);
                        startActivity(new Intent(RegisterUser.this, CentralPage.class));
                    }
                    @Override
                    public void onFailure(@NonNull Call<Client> call, @NonNull Throwable t) {
                        String resp = "Регистрация не прошла: ".concat(t.toString());
                        Toast.makeText(RegisterUser.this,resp, Toast.LENGTH_LONG).show();
                        Logger.getLogger(RegisterUser.class.getName()).log(Level.SEVERE,"Ошибка",t);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });


    }
}