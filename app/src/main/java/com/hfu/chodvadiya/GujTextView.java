package com.hfu.chodvadiya;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class GujTextView extends TextView
{

    public GujTextView(Context context) {
        super(context);
        initTypeFace(context);
    }

    public GujTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initTypeFace(context);

    }

    public GujTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeFace(context);

    }

    public GujTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initTypeFace(context);

    }

    private void initTypeFace(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"Saumil_guj2.ttf");
        this.setTypeface(typeface);
    }
}
