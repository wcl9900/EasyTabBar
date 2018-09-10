package com.wcl.easytabbar.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wcl.easytabbar.EasyTabBar;
import com.wcl.easytabbar.OnTabSelectExpandListener;
import com.wcl.easytabbar.OnTabSelectListener;
import com.wcl.easytabbar.R;

public class NormalTabsActivity extends AppCompatActivity {

    private TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        EasyTabBar easyTabBar = findViewById(R.id.easytabbar);
        easyTabBar.setRepeatSelectEnable(false);

//        easyTabBar.setOnTabViewSelectListener(onTabSelectListener);
        easyTabBar.setOnTabViewSelectListener(onTabSelectExpandListener);
        textViewInfo = findViewById(R.id.tab_info);
    }

    protected int getLayout(){
        return R.layout.activity_normal_tabs;
    }

    OnTabSelectListener onTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabViewSelectedListener(EasyTabBar easyTabBar, View selectedTabView, int selectedPosition) {
            showToast("选中了：" + selectedPosition);
            textViewInfo.setText("TAB:" + selectedPosition);
        }
    };

    OnTabSelectListener onTabSelectExpandListener = new OnTabSelectExpandListener() {
        @Override
        public boolean onTabViewSelectedBeforeListener(EasyTabBar easyTabBar, View willSelectedTabView, int willSelectedPosition) {
            if(willSelectedPosition == 1) {
                showToast("拦截Tab：" + willSelectedPosition);
                return true;
            }
            return false;
        }

        @Override
        public void onTabViewSelectedListener(EasyTabBar easyTabBar, View selectedTabView, int selectedPosition) {
            textViewInfo.setText("选中 TAB:" + selectedPosition);
        }
    };

    private void showToast(String info){
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }
}
