package cn.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.movie.R;
import cn.movie.entity.UserEntity;
import cn.movie.utils.CommonUtils;


public class MyActivity extends AppCompatActivity implements View.OnClickListener {

    TextView logout_tv;
    TextView myOrder_tv,my_orderTv;
    TextView my_name_tv,my_email_tv,my_tel_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);



        initView();
        initData();
        initEvent();
    }

    private void initView(){
        logout_tv = findViewById(R.id.logout);
        myOrder_tv = findViewById(R.id.myOrder);
        my_name_tv =  findViewById(R.id.my_name);
        my_email_tv =  findViewById(R.id.my_email);
        my_tel_tv =  findViewById(R.id.my_tel);

        my_orderTv =  findViewById(R.id.my_orderTv);


        logout_tv.setOnClickListener(this);
        myOrder_tv.setOnClickListener(this);
        my_orderTv.setOnClickListener(this);
    }

    private void initData(){
        UserEntity loginUser = CommonUtils.getLoginUser(MyActivity.this);
        my_name_tv.setText(loginUser.getUsername());
        my_email_tv.setText(loginUser.getEmail());
        my_tel_tv.setText(loginUser.getTel());



    }

    private void initEvent(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.logout:
                CommonUtils.clearStoreUser(this);

                CommonUtils.navigateTo(this,LoginActivity.class);
                break;
            case R.id.my_orderTv:
                Intent myOrderIntent1=new Intent();
                myOrderIntent1.setClass(MyActivity.this,MyOrderActivity.class);
                startActivity(myOrderIntent1);
                break;

        }
    }
}