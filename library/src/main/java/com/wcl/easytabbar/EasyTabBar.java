package com.wcl.easytabbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.com.wcl.easytabbar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于线性布局的TabBar选项卡,此选项卡可使用基于View的任何控件作为选项卡，子选项卡的控件id必须固定为： tabbarview_item (@id/tabbarview_item)；
 * 选项卡的选项监听器使用{@link OnTabSelectListener}接口实现。
 * @author 王春龙
 *
 */
public class EasyTabBar extends LinearLayout{
	
	private List<View> tabViewList;
	
	private OnTabSelectListener onTabSelectListener;

	private int defaultSelectedPosition = 0;
	private int selectedPosition = defaultSelectedPosition;

	private boolean fireTabListener = true;

	private boolean repeatSelectEnable = false;
	
	private int tabBarViewId = R.id.tabbar_item;
	
	public EasyTabBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttrs(attrs);
		init();
	}

	public EasyTabBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(attrs);
		init();
	}

	public EasyTabBar(Context context) {
		super(context);
		init();
	}

	private void initAttrs(AttributeSet attrs){
		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.EasyTabBar);
		repeatSelectEnable = ta.getBoolean(R.styleable.EasyTabBar_repeatSelectEnable, repeatSelectEnable);

		defaultSelectedPosition = ta.getInteger(R.styleable.EasyTabBar_defaultSelectedPosition, defaultSelectedPosition);
		setDefaultSelectedTab(defaultSelectedPosition);

		ta.recycle();
	}

	/**
	 * 设定默认选中的Tab位置
	 * @param position 默认选中Tab的位置
	 */
	public void setDefaultSelectedTab(int position){
		this.defaultSelectedPosition = position;
		this.selectedPosition = this.defaultSelectedPosition;
	}

	/**
	 * 设定已选选项卡是否可以重复选择并监听回调
	 * @param enable
	 */
	public void setRepeatSelectEnable(boolean enable){
		this.repeatSelectEnable = enable;
	}

	/**
	 * 得到当前选择项
	 * @return
	 */
	public int getSelectedPosition(){
		return selectedPosition;
	}

	private void init(){
		tabViewList = new ArrayList<>();
		post(new Runnable() {
			@Override
			public void run() {
				for(int i = 0; i < EasyTabBar.this.getChildCount(); i++){
					searchTabView(getChildAt(i));
				}
			}
		});
	}

	private int tabIndex = 0;
	private void searchTabView(View view){
		if(view instanceof EasyTabBar){
			return;
		}
		if(view.getId() == tabBarViewId){
			tabViewList.add(view);
			if(defaultSelectedPosition == tabIndex){
				view.setSelected(true);
			}
			view.setOnClickListener(onTabViewClickListener);
			
			tabIndex++;
		}
		else if(view instanceof ViewGroup){
			ViewGroup vg = (ViewGroup)view;
			int childCount = vg.getChildCount();
			for(int index = 0; index < childCount; index++){
				searchTabView(vg.getChildAt(index));
			}
		}
	}

	/**
	 * 设定选项卡选择监听器
	 * @param onTabSelectListener
	 */
	public void setOnTabViewSelectListener(OnTabSelectListener onTabSelectListener){
		this.onTabSelectListener = onTabSelectListener;
	}

	private OnClickListener onTabViewClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(v == null || !tabViewList.contains(v)){
				return;
			}

			selectTab(v);
		}
	};


	private void selectTab(View tabView) {
		int index = tabViewList.indexOf(tabView);
		
		if(!repeatSelectEnable && selectedPosition == index){
			return;
		}
		
		if(onTabSelectListener != null && onTabSelectListener instanceof OnTabSelectExpandListener){
			OnTabSelectExpandListener expandListener = (OnTabSelectExpandListener) onTabSelectListener;
			if(expandListener.onTabViewSelectedBeforeListener(this, tabView, index)) return;
		}

		invalidateSelectedTabView(index);
		
		selectedPosition = index;
		
		if(onTabSelectListener != null && fireTabListener) {
			onTabSelectListener.onTabViewSelectedListener(this, tabView, index);
		}
	}

	/**
	 * 选中指定位置的选项卡
	 * @param position 要选中的位置索引
	 */
	public void selectTab(int position){
		selectTab(position, false);
	}

	/**
	 * 选中指定位置的选项卡
	 * @param position 要选中的位置索引
	 * @param fireTabListener 是否触发选项卡监回调监听器
	 */
	public void selectTab(int position, boolean fireTabListener){
		if(position < 0 || tabViewList == null || position >= tabViewList.size()) return;
		this.fireTabListener = fireTabListener;
		selectTab(tabViewList.get(position));
		this.fireTabListener = true;
	}

	private void invalidateSelectedTabView(int selectedIndex) {
		for(int index = 0; index < tabViewList.size(); index++){
			if(index == selectedIndex){
				tabViewList.get(index).setSelected(true);
				continue;
			}
			tabViewList.get(index).setSelected(false);
		}
	}
}
