package com.key.loginproving;

import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.glp.test.R;
/**
 * 注册设置：验证注册信息时候为空，验证预留的手机号码是否为空
 * 注册数据存入sharedpreferences
 * @author Glp
 *
 */
public class Register extends Activity implements OnClickListener {
	private Button confirm;
	private EditText username;
	private EditText password;
	private EditText yuliu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		confirm = (Button) this.findViewById(R.id.confirm);
		username = (EditText) this.findViewById(R.id.editText1);
		password = (EditText) this.findViewById(R.id.editText2);
		yuliu = (EditText) this.findViewById(R.id.yuliu);
		confirm.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Pattern p = Pattern.compile("1\\d{10}");
		if (username.length() < 1 || password.length() < 1
				|| yuliu.length() < 1) {
			Toast.makeText(Register.this, "请完整输入注册信息", Toast.LENGTH_SHORT)
					.show();
		} else if (!p.matcher((yuliu.getText().toString())).matches()) {
			Toast.makeText(Register.this, "输入的手机号码非法", Toast.LENGTH_SHORT)
					.show();
		} else {
			SharedPreferences sharedPreferences = getSharedPreferences(
					"setting", Context.MODE_PRIVATE);
			Editor editor = sharedPreferences.edit();
			editor.putString("usr", username.getText().toString());
			editor.putString("pwd", password.getText().toString());
			editor.putString("phonenumber", yuliu.getText().toString());
			editor.commit();
			Intent in = new Intent(Register.this, MainActivity.class);
			startActivity(in);
			
		}
	}
}
