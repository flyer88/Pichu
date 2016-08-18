package com.dove.flyer.pichu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.dove.flyer.pichu.core.Pichu;

/**
 * Created by flyer on 16/8/18.
 */
public class SecondActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Pichu.init(getLayoutInflater());

        setContentView(R.layout.activity_layout);

        findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
