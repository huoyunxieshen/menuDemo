package com.szskAppMainmenu.Activity;



import com.szskAppMainmenu.Activity.Mainmenu.MainmenuItemClickedListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;



public class MainActivity extends Activity {
	
	private Mainmenu mm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mm = (Mainmenu) findViewById(R.id.mm);
		
		mm.setMainmenuItemClickedListener(new MainmenuItemClickedListener() {
			
			@Override
			public void onItemClicked(View view) {
				switch (view.getId()) {
				case R.id.channel1:
					Toast.makeText(getApplicationContext(),
							"1被点击", Toast.LENGTH_SHORT).show();
					break;

				case R.id.channel2:
					Toast.makeText(getApplicationContext(),
							"2被点击", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getKeyCode()==KeyEvent.KEYCODE_MENU) {
			mm.swichMenuVisable();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
