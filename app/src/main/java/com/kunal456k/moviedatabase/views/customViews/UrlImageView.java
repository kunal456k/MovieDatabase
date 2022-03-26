package com.kunal456k.moviedatabase.views.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.kunal456k.moviedatabase.R;
import com.kunal456k.moviedatabase.helpers.ImageLoadHelper;

public class UrlImageView extends androidx.appcompat.widget.AppCompatImageView {
    Drawable placeHolderSrc;
    Drawable brokenImageSrc;
    String posterSize;

    public UrlImageView(@NonNull Context context) {
        super(context);
    }

    public UrlImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        checkCustomFields(context, attrs);
    }

    public UrlImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        checkCustomFields(context, attrs);
    }

    private void checkCustomFields(Context context, AttributeSet attrs) {
        TypedArray a =  context.obtainStyledAttributes(attrs,
                R.styleable.UrlImageView, 0, 0);
        placeHolderSrc = a.getDrawable(R.styleable.UrlImageView_placeHolderSrc);
        brokenImageSrc = a.getDrawable(R.styleable.UrlImageView_brokenImageSrc);
        posterSize = a.getString(R.styleable.UrlImageView_posterSize);
        a.recycle();
    }

    @BindingAdapter(value = "imageUrl")
    public static void setImageUrl(@NonNull UrlImageView imageView, @Nullable String imageUrl) {
        String loadUrl = ImageLoadHelper.getPosterUrl(imageUrl, imageView.posterSize);
        Glide.with(imageView.getContext()).load(loadUrl).fitCenter()
                .placeholder(imageView.placeHolderSrc)
                .error(imageView.brokenImageSrc).fitCenter().into(imageView);
    }
}
