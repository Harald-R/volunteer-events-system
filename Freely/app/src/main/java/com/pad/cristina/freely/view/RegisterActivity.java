package com.pad.cristina.freely.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Button btnRegister = findViewById(R.id.buttonRegister);
        final EditText nameEditTxt = findViewById(R.id.nameR);
        final EditText emailEditTxt = findViewById(R.id.emailR);
        final EditText passwEditTxt = findViewById(R.id.passwordR);
        final EditText passwReEditTxt = findViewById(R.id.confirmPassword);

        register(btnRegister,nameEditTxt,emailEditTxt,passwEditTxt,passwReEditTxt);

    }
    private void register(Button btnRegister, final EditText nameEditTxt, final EditText emailEditTxt, final EditText passwEditTxt, final EditText passwReEditTxt) {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String email = emailEditTxt.getText().toString();
                if (!ValidityUtils.isEmailValid(email)) {
                    Toast.makeText(RegisterActivity.this, "Email" + email + " is incorrect.", Toast.LENGTH_SHORT).show();
                } else {
                    String name = nameEditTxt.getText().toString();
                    String password = passwEditTxt.getText().toString();
                    String passwordRe = passwReEditTxt.getText().toString();
                    if(password.equals(passwordRe)){
                        String passwordChecked = passwEditTxt.getText().toString();
                        Map<String, String> body = new HashMap<>();
                        body.put("name", name);
                        body.put("email", email);
                        body.put("password", passwordChecked);
                        body.put("type", "1");
                        String responseJsonStr = ApiRequestsUtil.sendRequest("http://192.168.43.192:3000/api/auth/register", ApiRequestsUtil.REQUEST_TYPES.POST, body);
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
                            Intent intent = new Intent(RegisterActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
                        }
                } else{
                        Toast.makeText(RegisterActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
