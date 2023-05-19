package cn.itfxq.business.order.controller;

import cn.itfxq.business.order.query.OrderQuery;
import cn.itfxq.business.order.service.IOrderService;
import cn.itfxq.common.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/index")
    public String index(String menuid, Model model){
        model.addAttribute("menuid",menuid);
        //订单首页
        return "views/order/order_list";
    }



    @GetMapping("/listpage")
    @ResponseBody
    public PageList listpage(OrderQuery orderQuery){

        PageList listpage = orderService.listpage(orderQuery);
        return listpage;
    }
}
