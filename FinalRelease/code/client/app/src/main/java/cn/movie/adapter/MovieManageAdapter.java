package cn.movie.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.movie.R;
import cn.movie.activity.OrderActivity;
import cn.movie.entity.MovieManageEntity;
import cn.movie.utils.CommonUtils;


public class MovieManageAdapter extends BaseAdapter {
    //资源id
    private int resourceId;

    private List<MovieManageEntity> mMoviePPEntityList;
    private Context mContext;



    public MovieManageAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * 设置数据更新界面
     */
    public void setData(List<MovieManageEntity> mMoviePPEntityList) {
        this.mMoviePPEntityList = mMoviePPEntityList;
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return mMoviePPEntityList == null ? 0 : mMoviePPEntityList.size();
    }
    /**
     * 根据position得到对应Item的对象
     */
    @Override
    public MovieManageEntity getItem(int position) {
        return mMoviePPEntityList == null ? null : mMoviePPEntityList.get(position);
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
        MovieManageEntity moviePPItem=getItem(position); //获取当前项的Words实例

        // 加个判断，以免ListView每次滚动时都要重新加载布局，以提高运行效率
        View view;
        ViewHolder viewHolder;
        if (convertView==null){

            // 避免ListView每次滚动时都要重新加载布局，以提高运行效率
            view= LayoutInflater.from(mContext).inflate(R.layout.moviepplist_item,parent,false);


            // 避免每次调用getView()时都要重新获取控件实例
            viewHolder=new ViewHolder();
            viewHolder.moviepp_time=view.findViewById(R.id.moviepp_time);
            viewHolder.moviepp_area=view.findViewById(R.id.moviepp_area);
            viewHolder.moviepp_price=view.findViewById(R.id.moviepp_price);
            viewHolder.moviepp_xzbtn=view.findViewById(R.id.moviepp_xzbtn);

            viewHolder.moviepp_xzbtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("movieppitem",moviePPItem);
                    CommonUtils.navigateTo(mContext, OrderActivity.class, bundle);


                }
            });



            view.setTag(viewHolder);
        } else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }

        viewHolder.moviepp_time.setText(moviePPItem.getPptime());
        viewHolder.moviepp_area.setText(moviePPItem.getPparea());
        viewHolder.moviepp_price.setText("¥"+moviePPItem.getPrice()+"元");



        return view;
    }

    // 定义一个内部类，用于对控件的实例进行缓存
    class ViewHolder{
        TextView moviepp_time,moviepp_area,moviepp_price,moviepp_xzbtn;




    }


}



