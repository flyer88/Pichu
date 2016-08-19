package com.dove.flyer.pichu.core;

import android.view.LayoutInflater;

import java.lang.annotation.Annotation;

/**
 * Created by flyer on 16/8/18.
 */
public class Pichu {

    public static boolean sChange = false;

    private LayoutInflater mLayoutInflater;
    public static void init(boolean change){
        sChange = change;
    }


    public Pichu createChangedFactory(LayoutInflater inflater){
        mLayoutInflater = inflater;
        mLayoutInflater.setFactory(new PichuInflaterFactory());
        return this;
    }

    public Pichu createChangedFactory(LayoutInflater inflater,boolean change){
        mLayoutInflater = inflater;
        mLayoutInflater.setFactory(new PichuInflaterFactory(change));
        return this;
    }


    /**
     * 获取注解中的 id，设置进 factory 中
     * @param object
     * @return
     */
    public Pichu openFilter(Object object){
        Annotation[] annotations = object.getClass().getAnnotations();
        for (Annotation annotation:annotations){
            if (annotation instanceof Filter){
                Filter changeClazz = (Filter)annotation;
                int [] keepIds = changeClazz.keep();
                ((PichuInflaterFactory)mLayoutInflater.getFactory()).setKeepView(keepIds);
                int [] changeIds = changeClazz.change();
                ((PichuInflaterFactory)mLayoutInflater.getFactory()).setChangeView(changeIds);
            }
        }
        return this;
    }


    public static class Builder{
        public Builder(){

        }

        public  Pichu build(){
            return new Pichu();
        }
    }
}
