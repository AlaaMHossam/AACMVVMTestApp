package com.lifetimecode.aacmvvmtestapp.utilities;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

public class ImageBindingAdapter {

    @BindingAdapter({"app:imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        if (!url.equals("")) {
            Picasso.get().load(url).resize(200, 200).into(imageView);
        }
    }

    @BindingAdapter({"app:onFocusChange"})
    public static void onFocusChange(TextInputEditText text, final View.OnFocusChangeListener listener) {
        text.setOnFocusChangeListener(listener);
    }
}