package com.hy.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hy.app.encryption.R;
import com.hy.features.encryption.EncryptionActivity;


public class MainActivity extends AppCompatActivity{
    Button mencryption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();  //初始化view
        initListener();  //初始化多个监听事件
    }

    private void initView(){
        mencryption = (Button) findViewById(R.id.mencryption);
    }

    private void initListener(){
        mencryption.setOnClickListener(new MyListener());
    }
    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {  //同时监听多个事件
            switch (v.getId()) {
                case R.id.mencryption:
                    newEncryptionActivity();
                    break;
                default:
                    break;
            }
        }
    }

    private void newEncryptionActivity(){
        Intent i = new Intent(MainActivity.this,EncryptionActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
