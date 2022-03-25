package com.kunal456k.moviedatabase.views.custom;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kunal456k.moviedatabase.R;
import com.kunal456k.moviedatabase.helpers.ImageLoadHelper;

public class UrlImageView extends androidx.appcompat.widget.AppCompatImageView {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .placeholder(R.drawable.place_holder).error(R.drawable.broken_image);

    public UrlImageView(@NonNull Context context) {
        super(context);
    }

    public UrlImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public UrlImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @BindingAdapter(value = "imageUrl", requireAll = false)
    public static void setImageUrl(@NonNull UrlImageView imageView, @Nullable String imageUrl) {
        String loadUrl = ImageLoadHelper.getSmallPosterUrl(imageUrl);
        Glide.with(imageView.getContext()).load(loadUrl)
                .apply(OPTIONS).fitCenter().into(imageView);
    }
}
