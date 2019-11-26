package com.kozyrev.simbirtraineeship;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.adapter.UsersAdapter;
import com.kozyrev.simbirtraineeship.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    private User user;

    public ProfileFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initToolbar();
        initProfile();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView ivProfilePhoto = getView().findViewById(R.id.iv_profile_photo);
        TextView tvName = getView().findViewById(R.id.tv_name);
        TextView tvDateBirth = getView().findViewById(R.id.tv_date_birth_value);
        TextView tvWork = getView().findViewById(R.id.tv_work_value);
        SwitchCompat switchCompat = getView().findViewById(R.id.switchCompat);
        RecyclerView rvFriends = getView().findViewById(R.id.rv_friends);

        ivProfilePhoto.setImageResource(user.getPhotoId());
        tvName.setText(user.getName());
        tvDateBirth.setText(user.getDateBirth().toString());
        tvWork.setText(user.getProfession());
        switchCompat.setChecked(user.isPush());

        UsersAdapter usersAdapter = new UsersAdapter(user.getFriends());
        rvFriends.setAdapter(usersAdapter);

        ivProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment photoDialog = new PhotoProfileDialogFragment();
                photoDialog.show(getFragmentManager(), "dialog");
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initToolbar(){
        TextView toolbarTitle = getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setVisibility(View.VISIBLE);
        toolbarTitle.setText(R.string.nav_profile);
        SearchView searchView = getActivity().findViewById(R.id.toolbar_search);
        searchView.setVisibility(View.GONE);
        setHasOptionsMenu(true);
    }

    private void initProfile() {
        user = new User(R.drawable.image_man, "Константинов Денис");
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        try {
            user.setDateBirth(format.parse("1-Feb-1990"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setProfession("Хирургия, травмотология");
        user.setPush(true);
        List<User> friends = new LinkedList<>();
        friends.add(new User(R.drawable.avatar_3, "Дмитрий Валерьевич"));
        friends.add(new User(R.drawable.avatar_2, "Евгений Александров"));
        friends.add(new User(R.drawable.avatar_1, "Виктор Кузнецов"));
        user.setFriends(friends);
    }
}
