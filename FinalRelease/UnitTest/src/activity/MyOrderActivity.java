package cn.movie.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.movie.R;
import cn.movie.adapter.MyOrderAdapter;
import cn.movie.entity.OrderEntity;
import cn.movie.utils.CommonUtils;
import cn.movie.utils.ItFxqConstants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MyOrderActivity extends AppCompatActivity {


    ListView myOrderLv;
    MyOrderAdapter myOrderAdapter;
    List<OrderEntity> myOrderDatas;
    private MyOrderHandler mMyOrderHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        mMyOrderHandler = new MyOrderHandler();
        initView();
        initBaseData();


    }

    private void initView(){
        myOrderLv = findViewById(R.id.myorder_lv);
    }
    private void initBaseData(){
        myOrderDatas = new ArrayList<>();

        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", CommonUtils.getLoginUser(MyOrderActivity.this).getUsername())
                .build();

        Request request = new Request.Builder()
                .url(ItFxqConstants.MYORDER_URL)
                .post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string(); //获取店铺数据
                Message msg = new Message();
                msg.what = ItFxqConstants.OK_STATUS;
                msg.obj = res;
                mMyOrderHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        });


    }

    class MyOrderHandler extends Handler {
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
                    if(isSuccess){
                       List<LinkedTreeMap> myOrders = (ArrayList) resultMap.get("myorders");
                       List<OrderEntity> orderEntities = new ArrayList();
                       myOrders.forEach(myOrder->{
                           OrderEntity orderEntity = new OrderEntity();
                           orderEntity.setOrdernum((String)myOrder.get("ordernum"));
                           orderEntity.setPrice((Double)myOrder.get("price"));
                           orderEntity.setAddress((String)myOrder.get("address"));
                           orderEntity.setUsername((String)myOrder.get("username"));
                           orderEntity.setTel((String)myOrder.get("tel"));
                           orderEntity.setSeatVal((String)myOrder.get("seatVal"));
                           orderEntity.setBeginTime((String)myOrder.get("beginTime"));
                           orderEntity.setHousename((String)myOrder.get("housename"));
                           orderEntity.setMoviename((String)myOrder.get("moviename"));
                           orderEntity.setNums(((Double)myOrder.get("nums")).longValue());
                           orderEntities.add(orderEntity);

                       });
                        myOrderDatas = orderEntities;
                        myOrderAdapter = new MyOrderAdapter(MyOrderActivity.this);
                        myOrderAdapter.setData(myOrderDatas);
                        myOrderLv.setAdapter(myOrderAdapter);
                        myOrderAdapter.notifyDataSetChanged();

                    }else{

                    }
                    break;
                case ItFxqConstants.ERROR_STATUS:
                    break;
            }
        }
    }


    public List getMyOrderList() {
        return this.myOrderDatas;
    }
}