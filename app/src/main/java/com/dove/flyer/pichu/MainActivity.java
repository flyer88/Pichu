package com.dove.flyer.pichu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends Activity {

    Button mButton;
    LinearLayout mRootLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        mButton = (Button) findViewById(R.id.test_btn);
        mRootLl = (LinearLayout) findViewById(R.id.root_ll);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }
}
