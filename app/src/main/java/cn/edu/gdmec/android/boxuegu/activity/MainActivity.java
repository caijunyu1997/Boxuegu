package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.edu.gdmec.android.boxuegu.R;

public class MainActivity extends AppCompatActivity {

    private TextView tv_hw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_hw = (TextView)findViewById(R.id.tv_hw);
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            boolean isLogin = data.getBooleanExtra("isLogin",false);
            String userName = data.getStringExtra("userName");
            if (isLogin){
                tv_hw.setText(userName+"登录成功");
            }
        }
    }
}
