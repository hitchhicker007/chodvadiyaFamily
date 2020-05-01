package com.hfu.chodvadiya.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GallaryImageAdapter extends BaseAdapter {

    private Context ctx;
    private List<String> images;
    public GallaryImageAdapter(Context ctx,List<String> images){
        this.images = images;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(ctx);
//        imageView.setImageResource(images[position]);
        Picasso.with(ctx).load(images.get(position)).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new GridView.LayoutParams(220,220));
        return imageView;
    }
}
