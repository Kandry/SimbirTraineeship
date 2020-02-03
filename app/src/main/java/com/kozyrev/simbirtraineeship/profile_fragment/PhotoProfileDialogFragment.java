package com.kozyrev.simbirtraineeship.profile_fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
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
import com.squareup.picasso.Picasso;

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

        switch (requestCode) {

            case REQUEST_GALLERY:
                if (resultCode == RESULT_OK) {
                    getImageFromGallery(data);
                }
                break;

            case START_CAMERA_APP:
                if (resultCode == RESULT_OK) {
                    imageUriString = camera.getPhotoFromCamera();
                    profileFragmentView.updatePhoto(imageUriString, -1);
                }
                break;
        }
    }

    private void initListeners(View rootView){
        Button dialogUpload = rootView.findViewById(R.id.dialog_upload);
        Button dialogCamera = rootView.findViewById(R.id.dialog_camera);
        Button deleteDialog = rootView.findViewById(R.id.dialog_delete);

        dialogUpload.setOnClickListener(view -> getGalleryImage());

        dialogCamera.setOnClickListener(view -> addPhoto());

        deleteDialog.setOnClickListener(view -> {
            user.setPhotoUri(null);
            profileFragmentView.updatePhoto(null, R.drawable.user_icon);
        });
    }

    private void getGalleryImage(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_GALLERY);
    }

    private void getImageFromGallery(Intent data){
        int takeFlags = data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        Uri originalUri = data.getData();
        getActivity().getContentResolver().takePersistableUriPermission(originalUri, takeFlags);

        imageUriString = originalUri.toString();
        profileFragmentView.updatePhoto(imageUriString, -1);
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
        Intent cameraAppIntent = camera.callCameraApp(getActivity());
        startActivityForResult(cameraAppIntent, START_CAMERA_APP);
    }
}
