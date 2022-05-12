package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class DemoAdapter extends ArrayAdapter<Word> {
        Context context;

    public DemoAdapter(@NonNull Context context, @NonNull List<Word> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        Word word = getItem(position);
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_view, parent, false);
        }
        TextView earthDate = listItemView.findViewById(R.id.earthDate);
        earthDate.setText(word.getEarthData());
        TextView fullName = listItemView.findViewById(R.id.fullName);
        fullName.setText(word.getmCameraName());
        ImageView imageSrc = listItemView.findViewById(R.id.imgSrc);
        Glide.with(this.context)
                .load(word.getmImgSrc())
                .into(imageSrc);
        return listItemView;
    }
}
