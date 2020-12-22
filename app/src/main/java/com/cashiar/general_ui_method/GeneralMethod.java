package com.cashiar.general_ui_method;

import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.cashiar.R;
import com.cashiar.tags.Tags;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class GeneralMethod {

    @BindingAdapter("error")
    public static void errorValidation(View view, String error) {
        if (view instanceof EditText) {
            EditText ed = (EditText) view;
            ed.setError(error);
        } else if (view instanceof TextView) {
            TextView tv = (TextView) view;
            tv.setError(error);


        }
    }

    @BindingAdapter("image")
    public static void image(View view, String endPoint) {
        if (view instanceof CircleImageView) {
            CircleImageView imageView = (CircleImageView) view;
            if (endPoint != null) {

                Picasso.get().load(Uri.parse(Tags.IMAGE_URL + endPoint)).resize(720,480).onlyScaleDown().into(imageView);
            } else {
                Picasso.get().load(R.drawable.ic_user).into(imageView);

            }
        } else if (view instanceof RoundedImageView) {
            RoundedImageView imageView = (RoundedImageView) view;

            if (endPoint != null) {

                Picasso.get().load(Uri.parse(Tags.IMAGE_URL + endPoint)).resize(720,480).onlyScaleDown().into(imageView);
            } else {
                Picasso.get().load(R.drawable.ic_user).into(imageView);

            }
        } else if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;

            if (endPoint != null) {

                Picasso.get().load(Uri.parse(Tags.IMAGE_URL + endPoint)).resize(720,480).onlyScaleDown().into(imageView);
            } else {
                Picasso.get().load(R.drawable.ic_user).into(imageView);

            }
        }

    }



    @BindingAdapter({ "date"})
    public static void displayDateTime(TextView textView, long dates) {




        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String date = dateFormat2.format(new Date(dates));

        textView.setText( date);
    }






}










