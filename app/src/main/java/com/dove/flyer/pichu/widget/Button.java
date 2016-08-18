package com.dove.flyer.pichu.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.dove.flyer.pichu.R;

/**
 * Created by flyer on 16/8/18.
 */
public class Button extends android.widget.Button{
    public Button(Context context) {
        super(context);
    }

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Button(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }


}
