package com.kozyrev.simbirtraineeship.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.core.content.FileProvider;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Camera {

    private Uri cameraImageUri;
    private ImageView imageView;

    public Camera(ImageView imageView){
        this.imageView = imageView;
    }

    public String getPhotoFromCamera(){
        Picasso.get()
                .load(cameraImageUri)
                .into(imageView);
        return cameraImageUri.toString();
    }

    public Intent callCameraApp(Context appContext, Context currientContext){
        Intent cameraAppIntent = new Intent();
        cameraAppIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException ex){
            ex.printStackTrace();
        }

        String authorities = appContext.getPackageName() + ".fileprovider";
        cameraImageUri = FileProvider.getUriForFile(currientContext, authorities, photoFile);

        cameraAppIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageUri);
        return cameraAppIntent;
    }

    private File createImageFile() throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMAGE_" + timeStamp + "_";
        File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        return File.createTempFile(imageFileName, ".jpg", storageDirectory);
    }
}
