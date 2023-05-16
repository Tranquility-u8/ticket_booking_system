package cn.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.io.IOException;
import java.util.List;

import cn.movie.R;
import cn.movie.adapter.MovieHouseAdapter;
import cn.movie.entity.MovieHouseEntity;
import cn.movie.utils.CommonUtils;
import cn.movie.utils.ItFxqConstants;
import cn.movie.service.RemoteDatas;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    List<MovieHouseEntity> movieHouseList;
    ListView listView;
    Button myMenuBtn;
    MovieHouseHandler mMovieHouseHandler;
    PopupWindow popupWindow;

    private MovieHouseAdapter mMovieHouseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.movieHouseListViewId);

        mMovieHouseAdapter=new MovieHouseAdapter(this);
        listView.setAdapter(mMovieHouseAdapter);
        mMovieHouseHandler = new MovieHouseHandler();

        initView();
        initEvent();
        initData();
    }

    public void OnMenu(View v){
        View popupWindow_view=getLayoutInflater().inflate(R.layout.menu,null,false);
        popupWindow=new PopupWindow(popupWindow_view, ActionBar.LayoutParams.WRAP_CONTENT,ActionBar.LayoutParams.WRAP_CONTENT,true);
        popupWindow.showAsDropDown(findViewById(R.id.btn_menu),0,0);
        myMenuBtn= popupWindow_view.findViewById(R.id.myMenuBtn);
        popupWindow_view.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if (popupWindow!=null&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                    popupWindow=null;
                }
                return false;
            }
        });
        myMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CommonUtils.navigateTo(MainActivity.this,MyActivity.class);
            }
        });
    }

    public void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1))
                + 15;
        listView.setLayoutParams(params);
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(ItFxqConstants.MOIVEHOUSE_URL).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Message msg = new Message();
                msg.what = ItFxqConstants.OK_STATUS;;
                msg.obj = res;
                mMovieHouseHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }

    public void initView() {

    }

    public void initEvent() {



    }



    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setClass(MainActivity.this, MovieActivity.class);
    }


    class MovieHouseHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case ItFxqConstants.OK_STATUS:
                    if (msg.obj != null) {
                        String resultJson = (String) msg.obj;
                        movieHouseList = RemoteDatas.INSTANCE.getMovieHouseList(resultJson);
                        mMovieHouseAdapter.setData(movieHouseList);
                        mMovieHouseAdapter.notifyDataSetChanged();
                        setListViewHeight(listView);

                    }
                    break;

            }
        }
    }
}