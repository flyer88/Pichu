package com.dove.flyer.pichu.core;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;

import com.dove.flyer.pichu.utils.WidgetName;

/**
 * Created by flyer on 16/8/3.
 */
public class PichuInflaterFactory implements LayoutInflater.Factory{

    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app.",
            "android.view."
    };

    public final static String PICHU_PATH = "com.dove.flyer.pichu.widget.";

    LayoutInflater mLayoutInflater;


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        if (mLayoutInflater == null ) {
            mLayoutInflater = LayoutInflater.from(context);
        }
        if (context instanceof ContextThemeWrapper){
            Context baseContext = ((ContextThemeWrapper)context).getBaseContext();
            if ( mLayoutInflater.getContext() != baseContext){
                mLayoutInflater = LayoutInflater.from(context);
            }
        }
        View view = createView(context, name, attrs);

        return view;
    }


    private View createView(Context context, String name, AttributeSet attrs) {
        View view = null;
            if (-1 == name.indexOf('.')){
                view = createPichuView(context,name,attrs);
                if (view == null) {
                    view = createAndroidView(context,name,attrs);
                }
            }else {
                try {
                    view = mLayoutInflater.createView(name, null, attrs);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        return view;
    }

    /**
     * 创建我自己的 view
     * @param context
     * @param name
     * @param attrs
     * @return
     */
    private View createPichuView(Context context,String name,AttributeSet attrs){
        View view = null;
        try {
            if (WidgetName.containsWidget(name)) {
                view = mLayoutInflater.createView(PICHU_PATH + name, null, attrs);
            }
        } catch (ClassNotFoundException e) {
            Log.e("PichuInflaterFactory","error while create pichu view【" + name + "】 : " + e.getMessage());
        }
        return view;
    }


    /**
     * 创建系统 view
     * @param context
     * @param name
     * @param attrs
     * @return
     */
    private View createAndroidView(Context context,String name,AttributeSet attrs){
        View view = null;
        for (String prefix : sClassPrefixList) {
            try {
                view = mLayoutInflater.createView(name, prefix, attrs);
            } catch (ClassNotFoundException e) {
                Log.e("PichuInflaterFactory","error while create android view【" + name + "】 : " + e.getMessage());
            }
        }

//        if (view != null) {
//            return view;
//        }
//
//        try {
//            view = mLayoutInflater.createView(name, "android.view.", attrs);
//        } catch (ClassNotFoundException e) {
//            Log.e("PichuInflaterFactory","error while create android view【" + name + "】 : " + e.getMessage());
//        }
        return view;
    }
}
