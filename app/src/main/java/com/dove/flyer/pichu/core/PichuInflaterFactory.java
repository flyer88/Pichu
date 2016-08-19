package com.dove.flyer.pichu.core;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;

import com.dove.flyer.pichu.R;
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

    private int mKeepIds[];//保留的 ids
    private int mChangeIds[];//替换的 ids

    private boolean mChange = true;//当前 Factory 是否开启替换

    public PichuInflaterFactory(){

    }
    public PichuInflaterFactory(boolean isChange){
        mChange = isChange;
    }

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
                view = chooseCreateType(context,name,attrs,view);
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
     * 根据几个开启标志，来进行确定是否开启替换 view
     * @param context
     * @param name
     * @param attrs
     * @param view
     * @return
     */
    private View chooseCreateType(Context context,String name,AttributeSet attrs,View view){
        if (Pichu.sChange) {//必须要全局开启
            if (mChange) {//当前 LayoutInflater 是否开启
                view = createPichuView(context, name, attrs);
                    if (mKeepIds.length > 0) {//如果 id 相同，view 变成空，让后面的去创建
                        for (int id : mKeepIds) {
                            if (view != null) {
                                if (view.getId() == id) {
                                    view = null;
                                }
                            }
                        }
                    }
            } else if (mChangeIds.length > 0){//即使没有开启变换，但是注解了需要变换的，依旧替换
                view = createPichuView(context, name, attrs);
                for (int id : mChangeIds) {
                    if (view != null) {
                        if (id != view.getId()) {
                            view = null;
                        }
                    }
                }
            }

        } else {
            Log.e("PichuInflaterFactory","forget to use init()");
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

        return view;
    }


    public void setKeepView(int[] ids){
        this.mKeepIds = ids;
    }

    public void setChangeView(int [] ids){
        this.mChangeIds = ids;
    }
}
