package com.pad.cristina.freely.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pad.cristina.freely.R;
import com.pad.cristina.freely.model.UserInfo;
import com.pad.cristina.freely.util.ApiRequestsUtil;
import com.pad.cristina.freely.util.ValidityUtils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button btnLogin = findViewById(R.id.buttonLogin);
        final EditText emailEditTxt = findViewById(R.id.emailR);
        final EditText passwEditTxt = findViewById(R.id.passwordR);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        login(btnLogin, emailEditTxt, passwEditTxt);
    }

    private void login(Button btnRegister, final EditText emailEditTxt, final EditText passwEditTxt) {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String email = emailEditTxt.getText().toString();
                if (!ValidityUtils.isEmailValid(email)) {
                    Toast.makeText(LoginActivity.this, "Email " + email + " is incorrect.", Toast.LENGTH_SHORT).show();
                } else {
                    String password = passwEditTxt.getText().toString();
                    Map<String, String> body = new HashMap<>();
                    body.put("email", email);
                    body.put("password", password);
                    String responseJsonStr = ApiRequestsUtil.sendRequest(ApiRequestsUtil.BASE_URL + "/api/auth/login", ApiRequestsUtil.REQUEST_TYPES.POST, body);

                    String token = null;
                    try {
                        JSONParser parser = new JSONParser();
                        JSONObject jsonObject = (JSONObject) parser.parse(responseJsonStr);
                        token = (String) jsonObject.get("token");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (token != null) {
                        UserInfo.setToken(token);
                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void register_action(View view)
    {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}

