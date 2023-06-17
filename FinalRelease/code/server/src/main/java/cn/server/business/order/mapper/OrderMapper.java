package cn.server.business.order.mapper;

import cn.server.business.order.entity.Order;
import cn.server.business.order.query.OrderQuery;
import cn.server.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface OrderMapper  extends BaseMapper<Order> {


    @Insert("insert into t_orders(ordernum,isPay,createTime," +
            "price,address,username,tel,movieid,moviename,housename,beginTime,nums,seatVal) " +
            "values(#{ordernum},#{isPay},#{createTime}," +
            "#{price},#{address},#{username},#{tel},#{movieid},#{moviename},#{housename},#{beginTime},#{nums},#{seatVal})")
    void addOrder(Order order);




    @Select("select * from t_orders where username=#{username}")
    List<Order> queryMyOrderByUsername(String username);

    @Select("select * from t_orders where housename=#{housename} and movieid=#{movieid} and beginTime=#{beginTime}")
    List<Order> queryisCheckSeat(OrderQuery orderQuery);
}
