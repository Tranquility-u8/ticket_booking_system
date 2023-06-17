package cn.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.movie.R;
import cn.movie.activity.MovieActivity;
import cn.movie.entity.MovieHouseEntity;

public class MovieHouseAdapter extends BaseAdapter {
    //资源id
    private int resourceId;

    private List<MovieHouseEntity> mMovieHouseList;
    private Context mContext;



    public MovieHouseAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 设置数据更新界面
     */
    public void setData(List<MovieHouseEntity> mMovieHouseList) {
        this.mMovieHouseList = mMovieHouseList;
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return mMovieHouseList == null ? 0 : mMovieHouseList.size();
    }
    /**
     * 根据position得到对应Item的对象
     */
    @Override
    public MovieHouseEntity getItem(int position) {
        return mMovieHouseList == null ? null : mMovieHouseList.get(position);
    }
    /**
     * 根据position得到对应Item的id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    // convertView 参数用于将之前加载好的布局进行缓存
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        MovieHouseEntity movieItem=getItem(position); //获取当前项的Words实例

        // 加个判断，以免ListView每次滚动时都要重新加载布局，以提高运行效率
        View view;
        ViewHolder viewHolder;
        if (convertView==null){

            // 避免ListView每次滚动时都要重新加载布局，以提高运行效率
            view= LayoutInflater.from(mContext).inflate(R.layout.moviehouselist_item,parent,false);

            // 避免每次调用getView()时都要重新获取控件实例
            viewHolder=new ViewHolder();
            viewHolder.movieHouse_name=view.findViewById(R.id.moviehouse_name);
            viewHolder.movieHouse_address=view.findViewById(R.id.moviehouse_address);
            viewHolder.movieHouse_price=view.findViewById(R.id.moviehouse_price);


            //进行电影院列表
            viewHolder.movieHouse_name.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,
                            MovieActivity.class);
                    intent.putExtra("houseid", movieItem.getId()+"");
                    intent.putExtra("housename", movieItem.getName()+"");
                    intent.putExtra("address", movieItem.getAddress()+"");
                    mContext.startActivity(intent);
                }
            });





            view.setTag(viewHolder);
        } else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }


        viewHolder.movieHouse_name.setText(movieItem.getName());
        viewHolder.movieHouse_address.setText(movieItem.getAddress());
        viewHolder.movieHouse_price.setText(""+movieItem.getPrice()+"");
        viewHolder.movieHouse_price.setTextColor(Color.RED);

        return view;
    }

    // 定义一个内部类，用于对控件的实例进行缓存
    class ViewHolder{

        TextView movieHouse_name,movieHouse_address,movieHouse_price;

    }


}



