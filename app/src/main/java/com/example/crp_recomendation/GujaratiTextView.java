package com.example.crp_recomendation;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class GujaratiTextView extends AppCompatTextView {


    public GujaratiTextView(Context context) {
        super(context);
        initTypeface(context);
    }

    public GujaratiTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypeface(context);
    }

    public GujaratiTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeface(context);
    }

    private void  initTypeface(Context context){
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),"shruti.ttf");
        this.setTypeface(typeface);
    }
}
