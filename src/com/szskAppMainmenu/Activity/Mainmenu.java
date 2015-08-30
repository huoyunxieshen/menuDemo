package com.szskAppMainmenu.Activity;


import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class Mainmenu extends RelativeLayout {
	
	//菜单的点击的监听器
	private MainmenuItemClickedListener listener;
	
	public void setMainmenuItemClickedListener(MainmenuItemClickedListener listener){
		this.listener = listener;
	}
	
	private RelativeLayout level1;
	private RelativeLayout level2;
	private RelativeLayout level3;
	private boolean isAnimating;
	private boolean MainmenuVisable = true;

	public Mainmenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public Mainmenu(Context context) {
		super(context);
		initView(context);
	}
	
	//获取自定义控件的最内层
	public RelativeLayout getLevel1(){
		return level1;
	}
	//获取自定义控件的中间层
	public RelativeLayout getLevel2(){
		return level2;
	}
	//获取自定义控件的最外层
	public RelativeLayout getLevel3(){
		return level3;
	}
	
	//菜单点击的监听器
	public interface MainmenuItemClickedListener{
		public void onItemClicked(View view);
	}
	
	//初始化自定义菜单组合控件的默认布局
	private void initView(Context context){
		View.inflate(context, R.layout.main_menu, this);
		level1 = (RelativeLayout) findViewById(R.id.level1);
		level2 = (RelativeLayout) findViewById(R.id.level2);
		level3 = (RelativeLayout) findViewById(R.id.level3);
		
		for (int index = 0; index < level3.getChildCount(); index++) {
			
		}
	}
	
	/**
	 * 私有旋转内部控件的方法
	 * @param view 被旋转的控件
	 * @param fromAngle 开始角度
	 * @param toAngle 结束角度
	 * @param delay 延迟时间
	 */
	private void rotate(View view,int fromAngle,
			int toAngle,int delay){
		//旋转的圆心x轴是中心点,y轴最下方1.0F
		RotateAnimation ra = new RotateAnimation(fromAngle, toAngle, 
				Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF, 1.0f);
		ra.setDuration(300);
		//动画播完停留在当前状态
		ra.setFillAfter(true);
		//动画播放后的延迟时间
		ra.setStartOffset(delay);
		view.startAnimation(ra);
	}
	
	//显示菜单
	
	private void showMenu(){
		if (isAnimating) {
			return;
		}
		for (int index = 0; index < level3.getChildCount(); index++) {
			level3.getChildAt(index).setClickable(true);
		}
		isAnimating = true;
		rotate(level1, -180, 0, 0);
		rotate(level2, -180, 0, 300);
		rotate(level3, -180, 0, 600);
		new Thread(){
			

			public void run() {
			SystemClock.sleep(900);
			isAnimating = false;
			MainmenuVisable = true;
			};
		}.start();
	}
	//隐藏菜单
	
	private void hideMenu(){
		if (isAnimating) {
			return;
		}
		for (int index = 0; index < level3.getChildCount(); index++) {
			level3.getChildAt(index).setClickable(false);
		}
		isAnimating = true;
		rotate(level3, 0, -180, 0);
		rotate(level2, 0, -180, 300);
		rotate(level1, 0, -180, 600);
		new Thread(){
			
			
			public void run() {
				SystemClock.sleep(900);
				isAnimating = false;
				MainmenuVisable = false;
			};
		}.start();
	}

	//修改菜单的显示状态
	public void swichMenuVisable() {
		if (MainmenuVisable) {
			hideMenu();
		}else{
			showMenu();
		}
	}

}
