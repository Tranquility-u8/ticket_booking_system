package cn.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.movie.R;
import cn.movie.entity.OrderEntity;


public class MyOrderAdapter extends BaseAdapter {
    private Context mContext;
    private List<OrderEntity> orderEntityList;
    public MyOrderAdapter(Context context) {
        this.mContext = context;
    }
    /**
     * 设置数据更新界面
     */
    public void setData(List<OrderEntity> orderEntityList) {
        this.orderEntityList = orderEntityList;
        notifyDataSetChanged();
    }
    /**
     * 获取Item的总数
     */
    @Override
    public int getCount() {
        return orderEntityList == null ? 0 : orderEntityList.size();
    }
    /**
     * 根据position得到对应Item的对象
     */
    @Override
    public OrderEntity getItem(int position) {
        return orderEntityList == null ? null : orderEntityList.get(position);
    }
    /**
     * 根据position得到对应Item的id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        //复用convertView
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.order_item,
                    null);

            
            vh.myorder_movienameTv = (TextView) convertView.findViewById(R.id.myorder_movienameTv);
            vh.myorder_numsTv = (TextView) convertView.findViewById(R.id.myorder_numsTv);
            vh.myorder_moneyTv = (TextView) convertView.findViewById(R.id.myorder_moneyTv);
            vh.myorder_beginTimeTv = (TextView) convertView.findViewById(R.id.myorder_beginTimeTv);
            vh.myorder_housenameTv = (TextView) convertView.findViewById(R.id.myorder_housenameTv);
            vh.myorder_seatValTv = (TextView) convertView.findViewById(R.id.myorder_seatValTv);
            
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //获取position对应的Item的数据对象
        final OrderEntity bean = getItem(position);
        if (bean != null) {
            vh.myorder_movienameTv.setText("影片名:"+bean.getMoviename());
            vh.myorder_numsTv.setText("票数:"+bean.getNums());
            vh.myorder_moneyTv.setText("价格:"+bean.getPrice());
            String beginTime = bean.getBeginTime().split(":")[0]+":"
                    +bean.getBeginTime().split(":")[1]  + "  "
                    + bean.getBeginTime().split(":")[2];
            vh.myorder_beginTimeTv.setText("开始时间:"+beginTime);
            vh.myorder_housenameTv.setText("电影院:"+bean.getHousename());


            String result = "";
            String[] strVal = bean.getSeatVal().split(",");
            for (int i=0;i<strVal.length;i++) {
                String temp = strVal[i];
                String row = temp.substring(0,1);
                String col =   temp.substring(1,2);
                result += row+"排"+col+"号"+"  ";
            }
            vh.myorder_seatValTv.setText("座位:"+result);


        }
        return convertView;
    }
    class ViewHolder {
        public TextView myorder_movienameTv,myorder_numsTv,
                myorder_moneyTv, myorder_beginTimeTv,myorder_housenameTv,myorder_seatValTv;

    }
}
