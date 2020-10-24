package com.example.crp_recomendation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.crp_recomendation.croprotation.cropRotation;
import com.example.crp_recomendation.crpYojana.govtPrograms;
import com.example.crp_recomendation.crprecom.budgetTracker;
import com.example.crp_recomendation.crprecom.crop_recomen;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    String disrict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        disrict = getIntent().getStringExtra("district");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new crop_recomen(disrict)).commit();



    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.CropReco:
                            selectedFragment = new crop_recomen(disrict);
                            break;
                        case R.id.CropRotat:
                            selectedFragment = new cropRotation();
                            break;
                        case R.id.BudgetTrac:
                            selectedFragment = new budgetTracker();
                            break;
                        case R.id.GovtProg:
                            selectedFragment = new govtPrograms();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    return true;
                }
            };




}
