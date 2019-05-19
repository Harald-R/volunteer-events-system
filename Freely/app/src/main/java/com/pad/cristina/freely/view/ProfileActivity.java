package com.pad.cristina.freely.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.pad.cristina.freely.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_profile:
                        Intent intent1 = new Intent(ProfileActivity.this, ProfileActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.action_events:
                        Intent intent3 = new Intent(ProfileActivity.this, DashboardActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.action_favorites:
                        Intent intent2 = new Intent(ProfileActivity.this, DashboardActivity.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });
    }
}
