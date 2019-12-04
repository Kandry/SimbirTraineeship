package com.kozyrev.simbirtraineeship.filters_fragment;

import android.content.Context;

import com.google.gson.Gson;
import com.kozyrev.simbirtraineeship.model.Category;
import com.kozyrev.simbirtraineeship.model.Event;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JSONHelperCategory {

    public static List<Category> importFromJSON(Context context, String fileName){

        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;

        try {
           /* fileInputStream = context.openFileInput(fileName);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();*/
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read();
            inputStream.close();
            String json = new String(buffer, "UTF-8");
            DataItems dataItems = new Gson().fromJson(json, DataItems.class); //.fromJson(new String(buffer, "UTF-8"), DataItems.class);//(streamReader, DataItems.class);
            return dataItems.getData();
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            /*
            if (streamReader != null){
                try{
                    streamReader.close();
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }
            if (fileInputStream != null){
                try{
                    fileInputStream.close();
                } catch (IOException ex){
                    ex.printStackTrace();
                }
            }*/
        }

        return null;
    }

    private static class DataItems {

        private List<Category> data;

        private List<Category> getData() {
            return data;
        }

        private void setData(List<Category> data) {
            this.data = data;
        }
    }
}
