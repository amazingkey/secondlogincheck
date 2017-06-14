package com.key.loginproving;

import com.glp.test.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Forgivepassword extends Activity implements OnClickListener{
	private Button button;
	private EditText edit;
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.forgivepassword);
        button = (Button)this.findViewById(R.id.yanzhen);
        edit = (EditText)this.findViewById(R.id.inputphonenum);
        button.setOnClickListener(this);
        
    }
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.yanzhen:
			SharedPreferences sharepreferences = getSharedPreferences("setting", Activity.MODE_PRIVATE);
			String phonenumber = sharepreferences.getString("phonenumber", "");
			if(phonenumber.length()>0&&phonenumber.equals(edit.getText().toString())){
				Intent it = new Intent(Forgivepassword.this,Register.class);
				startActivity(it);
				this.finish();
			}else{
				Toast.makeText(Forgivepassword.this, "璇疯緭鍏ユ偍棰勭暀鐨勬墜鏈哄彿鐮�", Toast.LENGTH_SHORT).show();
			}
		}
		
	}

}
