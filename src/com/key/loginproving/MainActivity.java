package com.key.loginproving;

import com.glp.test.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * 完成登录验证的算法
 * 
 * @author Glp
 * 
 */
public class MainActivity extends Activity implements OnClickListener,OnCheckedChangeListener {
	private Button button1;
	private Button button2;
	private Button button3;
	private EditText edit1;
	private EditText edit2;
	private ImageView imageview;
	private CheckBox checkbox;
	private static final int IMAGE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		button1 = (Button) this.findViewById(R.id.loginbutton);
		button2 = (Button) this.findViewById(R.id.button2);
		button3 = (Button) this.findViewById(R.id.button3);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		edit1 = (EditText) this.findViewById(R.id.username);
		edit2 = (EditText) this.findViewById(R.id.password);
		imageview = (ImageView) this.findViewById(R.id.image);
		imageview.setOnClickListener(this);
		checkbox = (CheckBox)this.findViewById(R.id.checkBox1);
		checkbox.setOnCheckedChangeListener(this);
		remember();
		image();
		
	}
	public void image(){
		SharedPreferences share = getSharedPreferences("imageview",
				Context.MODE_PRIVATE);
		String image = share.getString("image", "");
		if (image.length() > 0) {
			showImage(image);
		}
	}
public void remember(){
	SharedPreferences shares = getSharedPreferences("rememberpwd",
			Context.MODE_PRIVATE);
	String pwd = shares.getString("psd", "");
	if(pwd.length()>0){
		Intent is = new Intent(MainActivity.this,Gameview.class);
		startActivity(is);
		finish();
	}
}
	/**
	 * string类型usr与pwd有默认长为0，注意同时为0时免密登录缺陷 只需把登录条件需改为usr与pwd的长度大于0再进行判断即可
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 获取图片路径
		if (requestCode == IMAGE && resultCode == Activity.RESULT_OK
				&& data != null) {
			Uri selectedImage = data.getData();
			String[] filePathColumns = { MediaStore.Images.Media.DATA };
			Cursor c = getContentResolver().query(selectedImage,
					filePathColumns, null, null, null);
			// 指向查询结果的第一个位置，一般通过判断他的值是true还是false来确定查询是否为空
			c.moveToFirst();
			int columnIndex = c.getColumnIndex(filePathColumns[0]);
			String imagePath = c.getString(columnIndex);
			SharedPreferences share = getSharedPreferences("imageview",
					Context.MODE_PRIVATE);
			Editor editor = share.edit();
			editor.putString("image", imagePath);
			editor.commit();
			showImage(imagePath);
			c.close();
		}
	}

	// 加载图片
	private void showImage(String imaePath) {
		Bitmap bm = BitmapFactory.decodeFile(imaePath);
		((ImageView) findViewById(R.id.image)).setImageBitmap(bm);
	}

	@Override
	public void onClick(View v) {
		SharedPreferences sharedPreferences = getSharedPreferences("setting",
				Activity.MODE_PRIVATE);
		String usr = sharedPreferences.getString("usr", "");
		String pwd = sharedPreferences.getString("pwd", "");

		switch (v.getId()) {
		case R.id.loginbutton:
			if ("weiwei".equals(edit1.getText().toString())
					&& "missyou".equals(edit2.getText().toString())) {
				Intent intent = new Intent(MainActivity.this, Gameview.class);
				startActivity(intent);
			} else if (usr.length() > 0 && pwd.length() > 0&&edit1.length() > 0 && edit2.length() > 0) {
				if (usr.equals(edit1.getText().toString())
						&& pwd.equals(edit2.getText().toString())) {
					Intent intent = new Intent(MainActivity.this,
							Gameview.class);
					startActivity(intent);
				}else{
					Toast.makeText(MainActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
				}
			} else if (edit1.getText().length() == 0
					|| edit2.getText().length() == 0) {
				Toast.makeText(MainActivity.this, "请输入账号！", Toast.LENGTH_SHORT)
						.show();
			} 
			break;
		case R.id.button2:
			if (usr.length() < 1) {
				Intent ints = new Intent(MainActivity.this, Register.class);
				startActivity(ints);
			} else {
				Toast.makeText(MainActivity.this, "您已经注册了账号",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.button3:
			Intent is = new Intent(MainActivity.this, Forgivepassword.class);
			startActivity(is);
			break;
		case R.id.image:
			// 调用相册
			Intent intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
			startActivityForResult(intent, IMAGE);
			break;
		}

	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
		SharedPreferences sharedPreferences = getSharedPreferences("setting",
				Activity.MODE_PRIVATE);
		String usr = sharedPreferences.getString("usr", "");
		String pwd = sharedPreferences.getString("pwd", "");
		if (isChecked) { 
			if (usr.length() > 0 && usr.length() > 0) {
				if (usr.equals(edit1.getText().toString())
						&& pwd.equals(edit2.getText().toString())) {
					SharedPreferences shares = getSharedPreferences(
							"rememberpwd", Context.MODE_PRIVATE);
					Editor editor1 = shares.edit();
					editor1.putString("psd", "yes");
					editor1.commit();
				} 
				}else {
					Toast.makeText(MainActivity.this, "请输入正确账号与密码",
							Toast.LENGTH_SHORT).show();

			}

		}else{
			SharedPreferences shares = getSharedPreferences(
					"rememberpwd", Context.MODE_PRIVATE);
			Editor editor1 = shares.edit();
			editor1.putString("psd", "");
			editor1.commit();
			Toast.makeText(MainActivity.this, "没有记住",
					Toast.LENGTH_SHORT).show();
		}
		
	}
}
