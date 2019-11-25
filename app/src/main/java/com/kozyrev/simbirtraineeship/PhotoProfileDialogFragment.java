package com.kozyrev.simbirtraineeship;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PhotoProfileDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.profile_photo_dialog, null);
        initListeners(view);
        builder.setView(view);
        return builder.create();
    }

    private void initListeners(View rootView){
        Button dialogCamera = rootView.findViewById(R.id.dialog_camera);
        Button deleteDialog = rootView.findViewById(R.id.dialog_delete);

        dialogCamera.setOnClickListener(view -> Toast.makeText(getActivity(), "BIRD", Toast.LENGTH_SHORT).show());
        deleteDialog.setOnClickListener(view -> Toast.makeText(getActivity(), "DELETE", Toast.LENGTH_SHORT).show());
    }
}
