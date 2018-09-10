package com.wcl.easytabbar.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wcl.easytabbar.R;

/**
 * EasyTabBar demo
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_normal_tabs).setOnClickListener(this);
        findViewById(R.id.btn_multi_tabs).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_normal_tabs:
                startActivity(new Intent(this, NormalTabsActivity.class));
                break;
            case R.id.btn_multi_tabs:
                startActivity(new Intent(this, MultiTabsActivity.class));
                break;
        }
    }
}
