package com.kozyrev.simbirtraineeship.authorization_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.kozyrev.simbirtraineeship.MainActivity;
import com.kozyrev.simbirtraineeship.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class AuthorizationActivity extends AppCompatActivity {

    private static final String KEY_EMAIL = "AuthorizationActivity_Email";
    private static final String KEY_PASS = "AuthorizationActivity_Pass";

    private EditText etInputEmail;
    private EditText etInputPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        initToolbar();
        initView();

        if (savedInstanceState != null){
            etInputEmail.setText(savedInstanceState.getCharSequence(KEY_EMAIL));
            etInputPass.setText(savedInstanceState.getCharSequence(KEY_PASS));
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(KEY_EMAIL, etInputEmail.getText().toString());
        outState.putCharSequence(KEY_PASS, etInputPass.getText().toString());
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
        etInputEmail = findViewById(R.id.et_input_email);
        etInputPass = findViewById(R.id.et_input_pass);

        Button btnEnter = findViewById(R.id.btn_enter);
        btnEnter.setOnClickListener(view -> {
            Intent intent = new Intent(AuthorizationActivity.this, MainActivity.class);
            startActivity(intent);
            AuthorizationActivity.this.finish();
        });

        Observable<CharSequence> emailInputObservable = RxTextView.textChanges(etInputEmail);
        Observable<CharSequence> passInputObservable = RxTextView.textChanges(etInputPass);

        Observable
                .combineLatest(emailInputObservable, passInputObservable, (charSequence, charSequence2) -> (charSequence.length() > 5) && (charSequence2.length() > 5))
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Boolean aBoolean) {
                        btnEnter.setEnabled(aBoolean);
                        btnEnter.setBackground(aBoolean ? getDrawable(R.color.leaf) : getDrawable(R.color.black__38));
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}