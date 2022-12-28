package com.example.imageselecter;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GalleryItemView extends FrameLayout {
    public GalleryItemView(@NonNull Context context) {
        super(context);
    }

    public GalleryItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GalleryItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

}
