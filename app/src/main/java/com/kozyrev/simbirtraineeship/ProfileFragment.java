package com.kozyrev.simbirtraineeship;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.adapter.UsersAdapter;
import com.kozyrev.simbirtraineeship.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    private User user;

    public ProfileFragment() {}

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initProfile();
        return view;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView ivProfilePhoto = (ImageView) getView().findViewById(R.id.iv_profile_photo);
        TextView tvName = (TextView) getView().findViewById(R.id.tv_name);
        TextView tvDateBirth = (TextView) getView().findViewById(R.id.tv_date_birth_value);
        TextView tvWork = (TextView) getView().findViewById(R.id.tv_work_value);
        SwitchCompat switchCompat = (SwitchCompat) getView().findViewById(R.id.switchCompat);
        RecyclerView rvFriends = (RecyclerView) getView().findViewById(R.id.rv_friends);

        ivProfilePhoto.setImageResource(user.getPhotoId());
        tvName.setText(user.getName());
        tvDateBirth.setText(user.getDateBirth().toString());
        tvWork.setText(user.getProfession());
        switchCompat.setChecked(user.isPush());

        UsersAdapter usersAdapter = new UsersAdapter(user.getFriends());
        rvFriends.setAdapter(usersAdapter);
    }
}
