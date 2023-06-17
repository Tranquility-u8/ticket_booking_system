package cn.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import cn.movie.R;
import cn.movie.entity.MovieManageEntity;
import cn.movie.entity.OrderEntity;
import cn.movie.utils.CommonUtils;
import cn.movie.utils.ItFxqConstants;
import cn.movie.service.RemoteDatas;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    private MovieManageEntity moviePPEntity;
    private TextView tv_payment;
    private EditText et_orderNumId,et_orderUserId,et_telId;
    private OrderHandler mOrderHandler;
    private SeatHandler mSeatHandler;
    TextView movienameTv,moviepptimeTv,moviehouseTv,movie_priceTv;
    TextView total_costTv;
    EditText order_numEt;//订单数量
    Double totalprice;

    TextView[][] seatArr = new TextView[6][8]; //6行8列 = 48
    Integer[][] seatVal = new Integer[6][8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mOrderHandler = new OrderHandler();
        mSeatHandler = new SeatHandler();
        //获取 排片的信息
        moviePPEntity= (MovieManageEntity) getIntent().getSerializableExtra("movieppitem");

        //查询被选中的座位
        querySeat();


        initView();
        setData();
    }

    private void querySeat() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("housename", moviePPEntity.getMoviehousename())
                .add("movieid",moviePPEntity.getMovieid()+"")
                .add("beginTime",moviePPEntity.getPptime()+":"+moviePPEntity.getPparea())
                .build();

        Request request = new Request.Builder()
                .url(ItFxqConstants.MOIVESEAT_URL)
                .post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            Message msg = new Message();
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取返回数据
                String res = response.body().string();

                msg.what = ItFxqConstants.OK_STATUS;
                msg.obj = res;
                mSeatHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Call call, IOException e) {
                msg.what = ItFxqConstants.ERROR_STATUS;
                mSeatHandler.sendMessage(msg);
            }
        });
    }

    /**
     * 初始化界面控件
     */
    private void initView(){
        //设置订单号
        et_orderNumId =  findViewById(R.id.orderNumId);
        et_orderNumId.setText(CommonUtils.getOrderNum());
        et_orderNumId.setKeyListener(null);
        //设置当前用户名
        et_orderUserId = findViewById(R.id.orderUserId);
        et_orderUserId.setText(CommonUtils.getLoginUser(OrderActivity.this).getUsername());
        et_orderUserId.setKeyListener(null);

        et_telId = findViewById(R.id.telId);
        et_telId.setText(CommonUtils.getLoginUser(OrderActivity.this).getTel());
        et_telId.setKeyListener(null);

        movienameTv = findViewById(R.id.moviename);
        moviepptimeTv = findViewById(R.id.moviepptime);
        moviehouseTv = findViewById(R.id.moviehouse);
        movie_priceTv = findViewById(R.id.movie_price);
        //tv_total_cost
        total_costTv = findViewById(R.id.tv_total_cost);
        order_numEt = findViewById(R.id.order_num);

        movienameTv.setText("影片名称:"+moviePPEntity.getMoviename());
        moviehouseTv.setText("电影院:"+moviePPEntity.getMoviehousename());
        moviepptimeTv.setText("开始时间:"+moviePPEntity.getPptime()+":"+moviePPEntity.getPparea());
        movie_priceTv.setText(" x "+moviePPEntity.getPrice()+"元");



        //获取座位设置
        for (int i = 1; i <= 6 ; i++) {
            String str = "s"+i;

            for (int j = 1; j <= 8; j++) {
                str = str + j;//s11
                int resID = getResources().getIdentifier(str, "id", getPackageName());
                seatArr[i-1][j-1] = findViewById(resID);
                seatArr[i-1][j-1].setOnClickListener(this);
                seatArr[i-1][j-1].setTag(i+"-"+j);
                str ="s"+i;
            }
        }
        //计算总价
        order_numEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){}else{
                    //失去焦点
                    String ordernum = order_numEt.getText().toString();
                    String price = moviePPEntity.getPrice() ;
                    totalprice = Double.valueOf(ordernum) * Double.valueOf(price);
                    total_costTv.setText(totalprice+"");
                }
            }
        });

        tv_payment = findViewById(R.id.tv_payment);

        tv_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //支付订单按钮
                String orderNum = et_orderNumId.getText().toString();
                String username = et_orderUserId.getText().toString();
                String tel = et_telId.getText().toString();

                if(TextUtils.isEmpty(tel) ){
                    Toast.makeText(OrderActivity.this,"联系电话不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

                //封装参数
                OrderEntity orderEntity = new OrderEntity();
                orderEntity.setIsPay("1");
                orderEntity.setOrdernum(orderNum);
                orderEntity.setTel(tel);
                orderEntity.setUsername(username);
                orderEntity.setPrice(totalprice);
                orderEntity.setNums(Long.valueOf(order_numEt.getText().toString()));
                orderEntity.setMovieid(moviePPEntity.getMovieid());
                orderEntity.setMoviename(moviePPEntity.getMoviename());
                orderEntity.setHousename(moviePPEntity.getMoviehousename());
                orderEntity.setBeginTime(moviePPEntity.getPptime()+":"+moviePPEntity.getPparea());
                //获取客户选座的位置 如 11  11,12
                String seatValStr = "" ;
                for (int i = 0; i < 6 ; i++) {
                    for (int j = 0; j < 8 ; j++) {
                        if(seatVal[i][j] != null){
                            seatValStr += seatVal[i][j]+",";
                        }
                    }
                }
                if(!"".equals(seatValStr)){
                    seatValStr = seatValStr.substring(0, seatValStr.length()-1);
                }
                orderEntity.setSeatVal(seatValStr);



                //发送请求 存入订单表
                OkHttpClient okHttpClient = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
                RequestBody requestBody = RequestBody.create(mediaType,new Gson().toJson(orderEntity));

                Request request = new Request.Builder()
                        .url(ItFxqConstants.ORDER_URL)
                        .post(requestBody)
                        .addHeader("Content-Type", "application/json")
                        .build();
                Call call = okHttpClient.newCall(request);
                // 开启异步线程访问网络
                call.enqueue(new Callback() {
                    Message msg = new Message();
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res = response.body().string();
                        msg.what = ItFxqConstants.OK_STATUS;
                        msg.obj = res;
                        mOrderHandler.sendMessage(msg);
                    }
                    @Override
                    public void onFailure(Call call, IOException e) {
                        msg.what = ItFxqConstants.ERROR_STATUS;

                    }
                });




            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    assert v != null;
                    if (order_numEt != null) {
                        order_numEt.clearFocus();
                    }
                }

            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }



    /**
     * 设置界面数据
     */
    private void setData() {
    }

    public void setSeatVal(int i,int j){
        Resources resources=getResources();
        Drawable seatDw = resources.getDrawable(R.mipmap.seat);
        Drawable unSeatDw = resources.getDrawable(R.mipmap.unseat);
        TextView stv = (TextView) seatArr[i-1][j-1];
        if(seatVal[i-1][j-1] == null){
            seatVal[i-1][j-1] = Integer.valueOf(i+""+j);
            stv.setBackground(seatDw);
        }else{
            seatVal[i-1][j-1] = null;
            stv.setBackground(unSeatDw);
        }
    }

    public void setCheckedSeatVal(int i,int j){
        Resources resources=getResources();
        Drawable seatDw = resources.getDrawable(R.mipmap.otherseat);
        TextView stv = (TextView) seatArr[i-1][j-1];
           // seatVal[i-1][j-1] = Integer.valueOf(i+""+j);
            stv.setBackground(seatDw);
            stv.setClickable(false);

    }

    //点击设值
    @Override
    public void onClick(View v) {
        String tag = (String)v.getTag();
        for (int i = 1; i <= 6 ; i++) {
            for (int j = 1; j <= 8 ; j++) {
                String tagVal=i+"-"+j;
                if(tagVal.equals(tag)){
                    setSeatVal(i,j);
                    break;
                }
            }
        }

    }


    class OrderHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case ItFxqConstants.OK_STATUS:
                    //解析获取的JSON数据
                    Gson gson = new Gson();
                    //通过反射得到type对象
                    Type listType = new TypeToken<Map>() {
                    }.getType();
                    Map resultMap = gson.fromJson((String)msg.obj, listType);
                    Boolean isSuccess =  (Boolean)resultMap.get("isSuccess");

                    if(isSuccess){
                        AlertDialog.Builder dlog = new AlertDialog.Builder(OrderActivity.this);
                        dlog.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dlg,int arg1) {
                                Intent intent = new Intent(OrderActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                        dlog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dlg,int arg1) {
                                dlg.dismiss();;
                            }
                        });
                        dlog.setMessage("恭喜您,您的订单支付成功。");
                        dlog.setTitle("温馨提示");
                        dlog.show();
                    }
                    break;
                case ItFxqConstants.ERROR_STATUS:
                    Toast.makeText(OrderActivity.this,"操作失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


    class SeatHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case ItFxqConstants.OK_STATUS:

                    if (msg.obj != null) {
                        String resultJson = (String) msg.obj;
                        //解析获取的JSON数据
                        List<OrderEntity> orderSeatList = RemoteDatas.INSTANCE.getOrderList(resultJson);
                        for (int i = 0; i < orderSeatList.size(); i++) {
                            OrderEntity orderEntity = orderSeatList.get(i);
                            String seatVal = orderEntity.getSeatVal();
                            // 11   12,13
                            String[] seatTempArr = seatVal.split(",");
                            for (int i1 = 0; i1 < seatTempArr.length; i1++) {
                                //11  12 13
                                String temp = seatTempArr[i1];
                               int row = Integer.valueOf( temp.substring(0, 1));
                               int col = Integer.valueOf(temp.substring(1, 2));
                                setCheckedSeatVal(row,col);
                            }
                        }


                    }
                    

                    break;
                case ItFxqConstants.ERROR_STATUS:
                    Toast.makeText(OrderActivity.this,"操作失败",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}