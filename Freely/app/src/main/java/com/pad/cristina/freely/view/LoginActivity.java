package com.pad.cristina.freely.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pad.cristina.freely.R;
import com.pad.cristina.freely.model.UserInfo;
import com.pad.cristina.freely.util.ValidityUtils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button btnLogin = findViewById(R.id.buttonLogin);
        final EditText emailEditTxt = findViewById(R.id.email);
        final EditText passwEditTxt = findViewById(R.id.password);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        login(btnLogin, emailEditTxt, passwEditTxt);
    }
    public static String getJSON(String url, String email, String password) {
        HttpURLConnection con = null;
        try {
            URL u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.getOutputStream().write(("email="+email+"&password="+password).getBytes());
            con.connect();

            if (con.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                return sb.toString();
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }
    private void login(Button btnRegister, final EditText emailEditTxt, final EditText passwEditTxt) {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String email = emailEditTxt.getText().toString();
                if (!ValidityUtils.isEmailValid(email)) {
                    Toast.makeText(LoginActivity.this, "Email-ul " + email + " este gresit", Toast.LENGTH_SHORT).show();
                } else {
                    String password = passwEditTxt.getText().toString();
                    String responseJsonStr = getJSON("http://192.168.43.192:3000/api/auth/login", email, password);
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

