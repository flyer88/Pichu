package com.dove.flyer.pichu.core;

import android.view.LayoutInflater;

/**
 * Created by flyer on 16/8/18.
 */
public class Pichu {

    public static void init(LayoutInflater inflater){
        inflater.setFactory(new PichuInflaterFactory());
    }

}
