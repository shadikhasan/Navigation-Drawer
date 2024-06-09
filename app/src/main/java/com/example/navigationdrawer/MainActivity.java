package com.example.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton drawerButton;
    NavigationView navigationView;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageUtils.loadLanguage(this);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayoutId);
        drawerButton = findViewById(R.id.drawerButtonId);
        navigationView = findViewById(R.id.navigationViewId);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.open();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                if(itemId == R.id.navMenuProfileId)
                {
                    Toast.makeText(MainActivity.this, "profile clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
                if(itemId == R.id.navMenuSettingsId)
                {
                    LanguageUtils.showLanguageDialog(MainActivity.this);
                }
                if(itemId == R.id.navMenuAboutId)
                {
                    Toast.makeText(MainActivity.this, "about clicked", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.close();

                return false;
            }
        });
    }


}