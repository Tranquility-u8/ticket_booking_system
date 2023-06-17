package cn.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.movie.R;
import cn.movie.adapter.MovieManageAdapter;
import cn.movie.entity.MovieManageEntity;
import cn.movie.entity.MovieEntity;
import cn.movie.utils.CommonUtils;


public class MovieManageActivity extends AppCompatActivity implements View.OnClickListener {

    MovieEntity mMovieEntity;

    ImageView moviepp_image;
    TextView moviepp_nameTv,moviepp_zyactor,
            moviepp_priceTv,moviepp_dyactor,
            moviepp_totaltime,moviepp_info;
    List<MovieManageEntity> mPPEntityList;
    MovieManageAdapter mMovieManageAdapter;
    ListView ppListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_pp);
        //得到影片的信息
        mMovieEntity =(MovieEntity) getIntent().getSerializableExtra("movieitem");
        initView();
        initData();

    }

    private void initData() {
        Glide.with(getApplicationContext())
                .load(mMovieEntity.getFmUrl())
                .into(moviepp_image);
        moviepp_nameTv.setText("片名:"+mMovieEntity.getMoviename());
        moviepp_zyactor.setText("主演:"+mMovieEntity.getZy_actor());
        moviepp_dyactor.setText("导演:"+mMovieEntity.getDy_actor());
        moviepp_priceTv.setText("价格:¥"+mMovieEntity.getPrice());
        moviepp_totaltime.setText("片长:"+mMovieEntity.getTotaltime());
        moviepp_info.setText("简介:"+mMovieEntity.getInfo());

        //格式 10:10:1厅-11:20:2厅-14:00:6厅
        mPPEntityList = CommonUtils.getMoviePPEntityList(mMovieEntity);
        mMovieManageAdapter = new MovieManageAdapter(this);
        mMovieManageAdapter.setData(mPPEntityList);
        ppListView.setAdapter(mMovieManageAdapter);
        CommonUtils.setListViewHeight(ppListView);

    }

    private void initView() {
         moviepp_image = findViewById(R.id.moviepp_image);
         moviepp_nameTv = findViewById(R.id.moviepp_name);
         moviepp_zyactor= findViewById(R.id.moviepp_zyactor);
         moviepp_priceTv= findViewById(R.id.moviepp_price);
         moviepp_dyactor= findViewById(R.id.moviepp_dyactor);
         moviepp_totaltime= findViewById(R.id.moviepp_totaltime);
         moviepp_info= findViewById(R.id.moviepp_info);
         ppListView = findViewById(R.id.movieppListViewId);

    }

    @Override
    public void onClick(View v) {

    }
}