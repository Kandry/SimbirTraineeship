package com.kozyrev.simbirtraineeship.profile_fragment;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.adapter.UsersAdapter;
import com.kozyrev.simbirtraineeship.model.User;
import com.kozyrev.simbirtraineeship.utils.CropSquareTransformation;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

public class ProfileFragmentView extends Fragment implements ProfileFragmentContract.View {

    private static final String TAG = "ProfileFragmentView";

    private ImageView ivProfilePhoto;
    private TextView tvName;
    private TextView tvDateBirth;
    private TextView tvWork;
    private SwitchCompat switchCompat;
    private UsersAdapter usersAdapter;

    private List<User> friends;
    private User user;

    private ProfileFragmentPresenter profileFragmentPresenter;

    public ProfileFragmentView() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initToolbar();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        int id = 1;

        profileFragmentPresenter = new ProfileFragmentPresenter(this);
        profileFragmentPresenter.requestUserData(id);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.top_menu_profile, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void setDataToViews(User user) {
        if (user != null) {

            tvName.setText(user.getName());
            tvDateBirth.setText(user.getDateBirth().toString());
            tvWork.setText(user.getProfession());
            switchCompat.setChecked(user.isPush());

            updatePhoto(user.getPhotoUri(), user.getPhotoId());

            friends.clear();
            friends = user.getFriends();
            usersAdapter.dataSetChanged(friends);

            this.user = user;
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(getActivity(), getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    private void initToolbar(){
        TextView toolbarTitle = getActivity().findViewById(R.id.toolbar_title);
        toolbarTitle.setVisibility(View.VISIBLE);
        toolbarTitle.setText(R.string.nav_profile);

        SearchView searchView = getActivity().findViewById(R.id.toolbar_search);
        searchView.setVisibility(View.GONE);

        setHasOptionsMenu(true);
    }

    private void initViews(View view){
        ivProfilePhoto = view.findViewById(R.id.iv_profile_photo);
        tvName = view.findViewById(R.id.tv_name);
        tvDateBirth = view.findViewById(R.id.tv_date_birth_value);
        tvWork = view.findViewById(R.id.tv_work_value);
        switchCompat = view.findViewById(R.id.switchCompat);

        friends = new LinkedList<>();
        RecyclerView rvFriends = view.findViewById(R.id.rv_friends);
        usersAdapter = new UsersAdapter(friends);
        rvFriends.setAdapter(usersAdapter);

        ivProfilePhoto.setOnClickListener(currentView -> {
            DialogFragment photoDialog = new PhotoProfileDialogFragment(this, user);
            photoDialog.show(getFragmentManager(), "dialog");
        });
    }

    void updatePhoto(String photoUriStr, int imageResId){
        int targetWidth = (int) getResources().getDimension(R.dimen.iv_profile_photo_width); //ivProfilePhoto.getLayoutParams().width;//tvName.getLayoutParams().width;
        int targetHeight = (int) getResources().getDimension(R.dimen.iv_profile_photo_height);

        if (photoUriStr != null) {
            ivProfilePhoto.getLayoutParams().height = targetHeight;
            Uri photoUri = Uri.parse(photoUriStr);
            Picasso.get()
                    .load(photoUri)
                    //.resizeDimen(R.dimen.iv_profile_photo_width, R.dimen.iv_profile_photo_height)
                    //.centerCrop()
                    .transform(new CropSquareTransformation())
                    .into(ivProfilePhoto); //.resize(targetWidth, targetHeight) .centerCrop()
        } else {
            ivProfilePhoto.getLayoutParams().height = targetWidth;
            ivProfilePhoto.setImageResource(imageResId);
        }
    }
}