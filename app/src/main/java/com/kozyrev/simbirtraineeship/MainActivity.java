package com.kozyrev.simbirtraineeship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        setupBottomNavigationView();
    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupBottomNavigationView(){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        //NavigationUI.setupWithNavController(toolbar, navController);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
