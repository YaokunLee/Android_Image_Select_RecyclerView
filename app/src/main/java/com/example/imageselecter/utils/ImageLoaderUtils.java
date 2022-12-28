package com.example.imageselecter.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.imageselecter.GalleryView;

import java.util.ArrayList;

public class ImageLoaderUtils {

    public static ArrayList<GalleryView.ImageData> getImageDatas(Context context) {
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = context.getContentResolver();
        String[] projection = new String[]{
                MediaStore.Images.ImageColumns.DATA, MediaStore.Images.ImageColumns.DISPLAY_NAME,
                MediaStore.Images.ImageColumns.SIZE, MediaStore.Images.ImageColumns.DATE_ADDED
        };
        Cursor cursor = contentResolver.query(imageUri, projection, null, null,
                MediaStore.Images.Media.DATE_ADDED + " desc");


        ArrayList<GalleryView.ImageData> result  =new ArrayList<>();

        if (cursor == null) {
            return result;
        }
        if (cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA));
                String fileName =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED));

                result.add(new GalleryView.ImageData(path, date, fileName));
            }
        }
        cursor.close();
        return result;
    }
}
