package com.kozyrev.simbirtraineeship.authorization_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.kozyrev.simbirtraineeship.R;

public class AuthorizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        initToolbar();
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar_auth);
        TextView tvTitleAuth = findViewById(R.id.tv_title_auth);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(view ->  {
            onBackPressed();
        });

        tvTitleAuth.setText(getString(R.string.auth_tv_toolbar));
    }
}
