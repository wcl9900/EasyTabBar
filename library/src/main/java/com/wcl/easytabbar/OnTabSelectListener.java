package com.wcl.easytabbar;

import android.view.View;

/**
 * {@link EasyTabBar}选项卡选择监听器
 * @author 王春龙
 *
 */
public interface OnTabSelectListener {
	/**
	 * {@link EasyTabBar}选项卡选择监听器
	 * @param selectedTabView 选中Tab的View视图
	 * @param selectedPosition 选中Tab的位置
	 */
	void onTabViewSelectedListener(EasyTabBar easyTabBar, View selectedTabView, int selectedPosition);
}
