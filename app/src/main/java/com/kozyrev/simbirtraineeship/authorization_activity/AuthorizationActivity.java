package com.kozyrev.simbirtraineeship.authorization_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kozyrev.simbirtraineeship.MainActivity;
import com.kozyrev.simbirtraineeship.R;

public class AuthorizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        initToolbar();
        initView();
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

    private void initView(){
        Button btnEnter = findViewById(R.id.btn_enter);
        btnEnter.setOnClickListener(view -> {
            Intent intent = new Intent(AuthorizationActivity.this, MainActivity.class);
            startActivity(intent);
            AuthorizationActivity.this.finish();
        });
    }
}
