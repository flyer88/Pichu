package com.dove.flyer.pichu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.dove.flyer.pichu.core.Filter;
import com.dove.flyer.pichu.core.Pichu;

/**
 * Created by flyer on 16/8/18.
 */

@Filter(
        keep = {
            R.id.test_btn_2,
            R.id.test_btn_3
        }
)
public class SecondActivity extends Activity{
    Pichu mPichu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPichu = new Pichu.Builder()
                .build()
                .createChangedFactory(getLayoutInflater())
                .openFilter(SecondActivity.this);

        setContentView(R.layout.activity_layout);

        findViewById(R.id.test_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
