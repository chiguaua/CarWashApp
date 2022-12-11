package ru.nsu.carwashapplication;

import android.annotation.SuppressLint;
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
import ru.nsu.carwashapplication.model.callhttp.loginCallback;
import ru.nsu.carwashapplication.model.callhttp.loginCallsend;
import ru.nsu.carwashapplication.model.globalVar;
import ru.nsu.carwashapplication.retrofit.ClientApi;
import ru.nsu.carwashapplication.retrofit.RetrofitServiceJson;

public class LogInPage extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    private TextView register, forgotPassword;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_log_in);


        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(this, RegisterUser.class));
                break;

            case R.id.signIn:
                userLogin();
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Нет почты");
            editTextEmail.requestFocus();
            return;
        }
        /*if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Пожалуйста, используйте правильную почту");
            editTextEmail.requestFocus();
            return;
        }*/
        if (password.isEmpty()) {
            editTextPassword.setError("Нет пароля");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 2) {
            editTextPassword.setError("Такой пароль невозможен");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        loginCallsend ls = new loginCallsend();
        ls.setPassword(password);
        ls.setUsername(email);

        RetrofitServiceJson retrofitService = new RetrofitServiceJson();
        ClientApi clientApi = retrofitService.getRetrofit().create(ClientApi.class);

        clientApi.login(ls)
                .enqueue(new Callback<loginCallback>() {
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        progressBar.setVisibility(View.INVISIBLE);
                        if (response.code() == 200) {
                            loginCallback ans = (loginCallback) response.body();
                            //Toast.makeText(LogInPage.this, ans.getAccessToken(), Toast.LENGTH_LONG).show();
                            globalVar.setUserMail(email);
                            startActivity(new Intent(LogInPage.this, CentralPage.class));
                        } else if (response.code() == 404) {
                            Toast.makeText(LogInPage.this, "Неверный mail", Toast.LENGTH_LONG).show();
                        }else if (response.code() == 400) {
                            Toast.makeText(LogInPage.this, "Неверный пароль", Toast.LENGTH_LONG).show();

                        }else {
                            Toast.makeText(LogInPage.this, response.toString(), Toast.LENGTH_LONG * 10).show();
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull Throwable t) {
                        //   Toast.makeText(LogInPage.this, "trouble", Toast.LENGTH_LONG*10).show();
                        Toast.makeText(LogInPage.this, t.toString(), Toast.LENGTH_LONG * 10).show();

                        // String resp = "Вход не удался: ".concat(t.toString());
                        //Toast.makeText(LogInPage.this, resp, Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        //Logger.getLogger(LogInPage.class.getName()).log(Level.SEVERE,"Ошибка",t);

                    }
                });

    }
}