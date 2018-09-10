package com.wcl.easytabbar;

import android.view.View;

/**
 * {@link EasyTabBar}扩展自{@link OnTabSelectListener}的选择回调监听器，可支持“选择前”监听
 * @author 王春龙
 *
 */
public interface OnTabSelectExpandListener extends OnTabSelectListener {
	/**
	 * {@link EasyTabBar}选项卡选择前监听器
	 * @param willSelectedTabView 将要选中的Tab视图
	 * @param willSelectedPosition 将要选中的Tab的索引位置
	 * @return 返回true则拦截
	 */
	boolean onTabViewSelectedBeforeListener(EasyTabBar easyTabBar, View willSelectedTabView, int willSelectedPosition);
}
