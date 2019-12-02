package com.kozyrev.simbirtraineeship.profile_fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.kozyrev.simbirtraineeship.R;
import com.kozyrev.simbirtraineeship.model.User;
import com.kozyrev.simbirtraineeship.utils.AppPermissions;
import com.kozyrev.simbirtraineeship.utils.Camera;

import static android.app.Activity.RESULT_OK;

public class PhotoProfileDialogFragment extends DialogFragment {

    private static final int REQUEST_GALLERY = 200;
    private static final int START_CAMERA_APP = 201;

    private User user;
    private ProfileFragmentView profileFragmentView;

    private Camera camera;

    private String imageUriString;

    public PhotoProfileDialogFragment(ProfileFragmentView profileFragmentView, User user){
        this.profileFragmentView = profileFragmentView;
        this.user = user;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.profile_photo_dialog, null);
        camera = new Camera(profileFragmentView.getView().findViewById(R.id.iv_profile_photo));
        initListeners(view);
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        //notImageAdding = true;

        switch (requestCode) {
            /*
            case REQUEST_GALLERY:
                if (resultCode == RESULT_OK) {
                    getImageFromGallery(data);
                }
                break;*/

            case START_CAMERA_APP:
                if (resultCode == RESULT_OK) {
                    imageUriString = camera.getPhotoFromCamera();
                    profileFragmentView.updatePhoto(imageUriString, -1);
                    //user.setPhotoUri(imageUriString);
                }
                break;
        }
    }

    private void initListeners(View rootView){
        Button dialogCamera = rootView.findViewById(R.id.dialog_camera);
        Button deleteDialog = rootView.findViewById(R.id.dialog_delete);

        dialogCamera.setOnClickListener(view -> {
            addPhoto();
        });

        deleteDialog.setOnClickListener(view -> {
            user.setPhotoUri(null);
            profileFragmentView.updatePhoto(null, R.drawable.user_icon);
        });
    }

    private void addPhoto(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (AppPermissions.getNeedPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, AppPermissions.REQUEST_WRITE_EXTERNAL_STORAGE, getActivity())){
                callCameraApp();
            }
        } else {
            callCameraApp();
        }
    }

    private void callCameraApp(){
        Intent cameraAppIntent = camera.callCameraApp(getActivity(), getActivity());
        startActivityForResult(cameraAppIntent, START_CAMERA_APP);
    }
}
