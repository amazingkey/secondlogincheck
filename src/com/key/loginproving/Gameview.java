package com.key.loginproving;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.glp.test.R;

public class Gameview extends Activity implements OnClickListener{
	private Button button;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gameview);
		button = (Button)this.findViewById(R.id.ww);
		button.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		SharedPreferences shares = getSharedPreferences(
				"rememberpwd", Context.MODE_PRIVATE);
		Editor editor1 = shares.edit();
		editor1.putString("psd", "");
		editor1.commit();
		finish();
	}
}
