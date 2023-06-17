package cn.movie.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import cn.movie.R;
import cn.movie.utils.ItFxqConstants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RegActivity extends AppCompatActivity {
    private TextView goLoginTv ;

    private EditText usernameEt ;

    private EditText pwdEt ;

    private EditText emailEt ;

    private EditText telEt ;

    private RegHandler mRegHandler;

    private RadioButton female,male;
    private RadioGroup rg;
    private boolean sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        mRegHandler=new RegHandler();
        usernameEt = findViewById(R.id.username);
        pwdEt = findViewById(R.id.pwd);
        emailEt = findViewById(R.id.email);
        telEt = findViewById(R.id.tel);
        rg = findViewById(R.id.rg);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if(checkedId==R.id.male){
                    sex = true;
                }else {
                    sex =false;
                }
            }
        });

        goLoginTv = findViewById(R.id.goLoginTv);
        goLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void saveUser(View view){
        String username = usernameEt.getText().toString().trim();
        String pwd = pwdEt.getText().toString().trim();
        String tel = telEt.getText().toString().trim();
        String email = emailEt.getText().toString().trim();
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)){
            Toast.makeText(this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
        }else{
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password",pwd)
                    .add("email",email)
                    .add("tel",tel)
                    .add("sex",sex+"")
                    .build();

            Request request = new Request.Builder()
                    .url(ItFxqConstants.REG_URL)
                    .post(requestBody).build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                Message msg = new Message();
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String res = response.body().string();
                    msg.what = ItFxqConstants.OK_STATUS;
                    msg.obj = res;
                    mRegHandler.sendMessage(msg);
                }
                @Override
                public void onFailure(Call call, IOException e) {
                    msg.what = ItFxqConstants.ERROR_STATUS;
                    mRegHandler.sendMessage(msg);
                }
            });
        }
    }

    class RegHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case ItFxqConstants.OK_STATUS:
                    Gson gson = new Gson();
                    Type listType = new TypeToken<Map>() {
                    }.getType();
                    Map resultMap = gson.fromJson((String)msg.obj, listType);
                    Boolean isSuccess =  (Boolean)resultMap.get("isSuccess");
                    String resultMsg =  (String)resultMap.get("msg");
                    if(isSuccess){
                        AlertDialog.Builder dlog = new AlertDialog.Builder(RegActivity.this);
                        dlog.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dlg,int arg1) {
                                Intent intent = new Intent(RegActivity.this,LoginActivity.class);
                                startActivity(intent);
                            }
                        });
                        dlog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dlg,int arg1) {
                                dlg.dismiss();;
                            }
                        });
                        dlog.setMessage("返回登录。");
                        dlog.setTitle("温馨提示");
                        dlog.show();
                    }else{
                        Toast.makeText(RegActivity.this,resultMsg,Toast.LENGTH_SHORT).show();
                    }
                    break;
                case ItFxqConstants.ERROR_STATUS:
                    Toast.makeText(RegActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


}