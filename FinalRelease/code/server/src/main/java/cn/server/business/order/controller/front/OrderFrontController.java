package cn.server.business.order.controller.front;

import cn.server.auth.entity.User;
import cn.server.business.order.entity.Order;
import cn.server.business.order.query.OrderQuery;
import cn.server.business.order.service.IOrderService;
import cn.server.common.result.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/frontorder")
public class OrderFrontController {


    @Autowired
    private IOrderService orderService;

    @RequestMapping("/add")
    @ResponseBody
    public ResultResponse add(@RequestBody Order order){
        try {
            orderService.addOrder(order);
        }catch (Exception e){
            return ResultResponse.fail("支付失败");
        }
        return ResultResponse.ok();
    }

    /**
     * 查询所有我的订单
     */
    @RequestMapping("/queryOrderByUsername")
    @ResponseBody
    public ResultResponse queryMyOrderByUsername(User user){
        try {
           List<Order> ordersList =  orderService.queryMyOrderByUsername(user.getUsername());
           return ResultResponse.ok().put("myorders",ordersList);
        }catch (Exception e){
            return ResultResponse.fail("查询失败");
        }

    }

    /**
     * 根据 movieid  housename beginTime 查询 setVal
     * @param orderQuery
     * @return
     */
    @RequestMapping("/queryisCheckSeat")
    @ResponseBody
    public List<Order>  queryisCheckSeat(OrderQuery orderQuery){

            List<Order> ordersList =  orderService.queryisCheckSeat(orderQuery);
            return ordersList;


    }



}
