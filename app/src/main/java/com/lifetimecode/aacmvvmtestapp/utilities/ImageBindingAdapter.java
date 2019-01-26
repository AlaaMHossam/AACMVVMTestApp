package com.lifetimecode.aacmvvmtestapp.utilities;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.squareup.picasso.Picasso;

public class ImageBindingAdapter {

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        if (!url.equals("")) {
            Picasso.get().load(url).resize(200, 200).into(imageView);
        }
    }
}