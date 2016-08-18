package com.dove.flyer.pichu.utils;

import java.util.HashMap;

/**
 * Created by flyer on 16/8/18.
 * 所有已经实现替换的控件
 */
public final class WidgetName {

    static String[] sWidgetArray = new String[]{
        "Button","TextView","LinearLayout"
    };

    public static boolean containsWidget(String name){
        for (String widgetName : sWidgetArray){
            if (widgetName.equals(name)){
                return true;
            }
        }
        return false;
    }

}
