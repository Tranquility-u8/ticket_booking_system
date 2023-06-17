package cn.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import cn.movie.R;
import cn.movie.adapter.MovieAdapter;
import cn.movie.utils.ItFxqConstants;
import cn.movie.service.RemoteDatas;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static cn.movie.utils.CommonUtils.setListViewHeight;

public class MovieActivity extends AppCompatActivity implements View.OnClickListener  {

    ListView listView;
    MovieAdapter mMovieAdapter;
    List movieList;
    MovieHandler mMovieHandler;
    private String houseid;
    TextView housenameTv,addressTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        listView = findViewById(R.id.movieListViewId);
        housenameTv = findViewById(R.id.moviehouse_nameTv);
        addressTv = findViewById(R.id.moviehouse_addressTv);


        mMovieAdapter=new MovieAdapter(this);
        listView.setAdapter(mMovieAdapter);
        mMovieHandler = new MovieHandler();
        houseid = getIntent().getStringExtra("houseid");
        String housename = getIntent().getStringExtra("housename");
        String address = getIntent().getStringExtra("address");
        housenameTv.setText(housename);
        addressTv.setText(address);
        initData();
    }

    @Override
    public void onClick(View v) {

    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("id", houseid)
                .build();

        Request request = new Request.Builder()
                .url(ItFxqConstants.MOIVE_URL)
                .post(requestBody).build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Message msg = new Message();
                msg.what = ItFxqConstants.OK_STATUS;;
                msg.obj = res;
                mMovieHandler.sendMessage(msg);
            }
            @Override
            public void onFailure(Call call, IOException e) {

            }
        });
    }

    class MovieHandler extends Handler {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case ItFxqConstants.OK_STATUS:
                    if (msg.obj != null) {
                        String resultJson = (String) msg.obj;
                        movieList = RemoteDatas.INSTANCE.getMovieList(resultJson);
                        mMovieAdapter.setData(movieList);
                        mMovieAdapter.notifyDataSetChanged();
                        setListViewHeight(listView);

                    }
                    break;

            }
        }
    }
}