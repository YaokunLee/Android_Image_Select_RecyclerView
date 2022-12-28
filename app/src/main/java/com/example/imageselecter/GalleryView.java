package com.example.imageselecter;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.imageselecter.utils.ImageLoaderUtils;

import java.util.ArrayList;

public class GalleryView extends RecyclerView {
    public GalleryView(@NonNull Context context) {
        super(context);
        init();
    }

    public GalleryView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GalleryView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayoutManager(new GridLayoutManager(getContext(), 4));
        // todo 获取相册数据
        ArrayList<ImageData> imageData = getImageData();
        setAdapter(new GalleyViewAdapter(imageData));
    }

    private ArrayList<ImageData> getImageData() {
        return ImageLoaderUtils.getImageDatas(getContext());
    }


    public static class ImageData{
        String path;
        String date;
        String size;
        String fileName;

        public ImageData(String path, String date, String size, String fileName) {
            this.path = path;
            this.date = date;
            this.size = size;
            this.fileName = fileName;
        }

        public ImageData(String path, String date, String fileName) {
            this.path = path;
            this.date = date;
            this.fileName = fileName;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public ImageData() {
        }
    }

    public class GalleyViewAdapter extends RecyclerView.Adapter<GalleyViewHolder>{

        ArrayList<ImageData> data;

        public GalleyViewAdapter() {

        }

        public GalleyViewAdapter(ArrayList<ImageData> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public GalleyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // 这里的parent是recyclerView 而不是itemView
            View view = LayoutInflater.from(getContext()).inflate(R.layout.gallery_view_item, parent, false);
            Log.i("lyk", "onCreateViewHolder");
            return new GalleyViewHolder(view);
        }

        @Override
        public int getItemCount() {
            if (this.data!=null) return this.data.size();
            else return 0;
        }

        @Override
        public void onBindViewHolder(@NonNull GalleyViewHolder holder, int position) {
            if (position < 0 || position >= this.data.size()) {
                return;
            }

            ImageData data = this.data.get(position);
            Glide.with(getContext())
                    .load(data.getPath()) // 加载路径
                    .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用缓存，直接从原图加载
                    .centerCrop() // 居中剪切
                    .into(holder.getImageView());

        }
    }


    public static class GalleyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public GalleyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.gallery_view_item_image_view);
        }

        public ImageView getImageView() {
            return imageView;
        }

    }

}
