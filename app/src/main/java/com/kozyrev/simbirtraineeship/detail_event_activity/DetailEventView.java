package com.kozyrev.simbirtraineeship.detail_event_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.detail_event_activity.fragment_shirt.ShirtFragment;
import com.kozyrev.simbirtraineeship.model.Event;

import static com.kozyrev.simbirtraineeship.utils.Constants.EVENT_ID;

public class DetailEventView extends AppCompatActivity implements DetailEventContract.View {

    private static final String TAG = "DetailEventView";

    private BottomNavigationView bnvDetailEvent;

    private DetailEventPresenter detailEventPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);

        initToolbar();
        initViews();

        Intent intent = getIntent();
        int id = intent.getIntExtra(EVENT_ID, -1);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ShirtFragment shirtFragment = new ShirtFragment(id);
        ft.replace(R.id.nav_detail_event_fragment, shirtFragment);
        ft.commit();

        detailEventPresenter = new DetailEventPresenter(this, this);
        detailEventPresenter.requestEventData(id);
    }

    @Override
    public void setDataToViews(Event event) {

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, R.string.communication_error, Toast.LENGTH_LONG).show();
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar_detail_event);
        setSupportActionBar(toolbar);
/*
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(view ->  {
            onBackPressed();
        });*/
    }

    private void initViews(){
        //toolbarDetailEventTitle = findViewById(R.id.toolbar_detail_event_title);

        bnvDetailEvent = findViewById(R.id.bnv_detail_event);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bnvDetailEvent.getChildAt(0);

        for (int i = 0; i<menuView.getChildCount(); i++){
            BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
            ImageView icon = itemView.findViewById(R.id.icon);
            TextView smallLabel = itemView.findViewById(R.id.smallLabel);
            TextView largeLabel = itemView.findViewById(R.id.largeLabel);

            smallLabel.setMinLines(2);
            largeLabel.setMinLines(2);

            smallLabel.setMaxLines(2);
            largeLabel.setMaxLines(2);

            smallLabel.setTextSize(getResources().getDimension(R.dimen.bnv_labels_textSize));
            smallLabel.setTextColor(getResources().getColor(R.color.black___40));
            largeLabel.setTextSize(getResources().getDimension(R.dimen.bnv_labels_textSize));
            largeLabel.setTextColor(getResources().getColor(R.color.black___40));

            smallLabel.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            largeLabel.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            icon.setPadding(0,0, 0, (int) getResources().getDimension(R.dimen.bnv_icons_padding));
        }
    }
}
