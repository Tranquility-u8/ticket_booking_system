package cn.movie.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.movie.R;
import cn.movie.activity.MovieManageActivity;
import cn.movie.entity.MovieEntity;
import cn.movie.utils.CommonUtils;



public class MovieAdapter extends BaseAdapter {

    private int resourceId;

    private List<MovieEntity> mMovieEntityList;
    private Context mContext;



    public MovieAdapter(Context context) {
        this.mContext = context;
    }


    public void setData(List<MovieEntity> mMovieEntityList) {
        this.mMovieEntityList = mMovieEntityList;
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return mMovieEntityList == null ? 0 : mMovieEntityList.size();
    }

    @Override
    public MovieEntity getItem(int position) {
        return mMovieEntityList == null ? null : mMovieEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        MovieEntity movieItem=getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(mContext).inflate(R.layout.movielist_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.movie_image=view.findViewById(R.id.movie_image);
            viewHolder.movie_nameTv=view.findViewById(R.id.movie_name);
            viewHolder.movie_zyactor=view.findViewById(R.id.movie_zyactor);
            viewHolder.movie_dyactor=view.findViewById(R.id.movie_dyactor);
            viewHolder.movie_totaltime=view.findViewById(R.id.movie_totaltime);
            viewHolder.movie_info=view.findViewById(R.id.movie_info);
            viewHolder.movie_priceTv=view.findViewById(R.id.movie_price);
            viewHolder.movie_ydbtn = view.findViewById(R.id.movie_ydbtn);

            viewHolder.movie_ydbtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("movieitem",movieItem);
                    CommonUtils.navigateTo(mContext, MovieManageActivity.class, bundle);


                }
            });

            viewHolder.movie_image.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("item",movieItem);
                    CommonUtils.navigateTo(mContext, MovieManageActivity.class, bundle);


                }
            });

            viewHolder.movie_nameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("item",movieItem);
                    CommonUtils.navigateTo(mContext, MovieManageActivity.class, bundle);

                }
            });

            viewHolder.movie_nameTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("item",movieItem);
                    CommonUtils.navigateTo(mContext, MovieManageActivity.class, bundle);

                }
            });

            view.setTag(viewHolder);
        } else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }

        viewHolder.movie_nameTv.setText("影片名:"+movieItem.getMoviename());
        viewHolder.movie_zyactor.setText("主演:"+movieItem.getZy_actor());
        viewHolder.movie_dyactor.setText("导演:"+movieItem.getDy_actor());
        viewHolder.movie_priceTv.setText("价格:¥"+movieItem.getPrice());
        viewHolder.movie_totaltime.setText("片长:"+movieItem.getTotaltime());
        viewHolder.movie_info.setText("简介:"+movieItem.getInfo());

        Glide.with(mContext)
                .load(movieItem.getFmUrl())
                .into(viewHolder.movie_image);

        return view;
    }

    class ViewHolder{
        ImageView movie_image;
        TextView movie_nameTv,movie_zyactor,movie_priceTv,movie_dyactor,movie_totaltime,movie_info,movie_ydbtn;


    }


}



