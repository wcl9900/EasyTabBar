# EasyTabBar
    简易TabBar，旨在使Tab布局配置更灵活
    
# 使用方式
    Gradle
    repositories {
        jcenter()
    }
    compile 'com.wcl.easytabbar:library:1.0'
    
   1.EasyTabBar布局：需对Tab子项设定android:id="@id/tabbar_item"
    
    <com.wcl.easytabbar.EasyTabBar
        android:id="@+id/easytabbar"
        android:layout_width="match_parent"
        android:layout_height="60dp"
        android:orientation="horizontal"
        android:background="@color/colorPrimary"
        app:defaultSelectedPosition="0">
        <LinearLayout
            android:id="@id/tabbar_item"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:orientation="vertical">
            <ImageView
                android:layout_width="match_parent"
                android:layout_height="0dp"
                android:layout_weight="1"
                android:layout_gravity="center_horizontal"
                android:src="@drawable/tab_bg"/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_horizontal"
                android:textColor="#fff"
                android:text="TAB0"/>
        </LinearLayout>

        <View
            android:layout_width="1px"
            android:layout_height="match_parent"
            android:background="#fff"/>

        <LinearLayout
            android:id="@id/tabbar_item"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:orientation="vertical">
            <ImageView
                android:layout_width="match_parent"
                android:layout_height="0dp"
                android:layout_weight="1"
                android:layout_gravity="center_horizontal"
                android:src="@drawable/tab_bg"/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center_horizontal"
                android:textColor="#fff"
                android:text="TAB1"/>
        </LinearLayout>
                    .
                    .
                    .
    </com.wcl.easytabbar.EasyTabBar>
    
   2.EasyTabBar选项卡监听回调
   
    OnTabSelectListener onTabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabViewSelectedListener(EasyTabBar easyTabBar, View selectedTabView, int selectedPosition) {
            showToast("选中了：" + selectedPosition);
            textViewInfo.setText("TAB:" + selectedPosition);
        }
    };
    easyTabBar.setOnTabViewSelectListener(onTabSelectListener);
   
    
   更多使用方式请查看demo