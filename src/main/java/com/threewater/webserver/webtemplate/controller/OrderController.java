package com.threewater.webserver.webtemplate.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.threewater.webserver.webtemplate.service.OrderService;
import com.threewater.webserver.webtemplate.service.TokenAuthService;
import com.threewater.webserver.webtemplate.util.ResultBean;
import com.threewater.webserver.webtemplate.vo.OrderInfoVo;
import com.threewater.webserver.webtemplate.vo.ProductInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private TokenAuthService tokenAuthService;

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/add")
    public ResultBean addOrder(@RequestBody OrderInfoVo orderInfoVo, @RequestHeader("Authorization") String token){
        Authentication authentication = tokenAuthService.getAuthentication(token);
        String userId = (String) authentication.getDetails();
        orderInfoVo.setUserId(userId);
        if(orderService.addOrderInfo(orderInfoVo) > 0){
            return ResultBean.getSuccessRes("下单成功！");
        }else{
            return ResultBean.getDefaultFailRes("下单失败！");
        }
    }

    @DeleteMapping(value = "/del/{id}")
    public ResultBean delOrder(@PathVariable("id") String orderId){
        if(orderService.deleteByOrderId(orderId) > 0){
            return ResultBean.getSuccessRes("删除订单成功！");
        }else{
            return ResultBean.getDefaultFailRes("删除订单失败！");
        }
    }

    @PutMapping(value = "/mod")
    public ResultBean modifyOrder(@RequestBody OrderInfoVo orderInfoVo, @RequestHeader("Authorization") String token){
        Authentication authentication = tokenAuthService.getAuthentication(token);
        String userId = (String) authentication.getDetails();
        orderInfoVo.setUserId(userId);
        if(orderService.updateOrderInfo(orderInfoVo) > 0){
            return ResultBean.getSuccessRes("修改订单成功！");
        }else{
            return ResultBean.getDefaultFailRes("修改订单失败！");
        }
    }

    @GetMapping(value = "/qry/{id}")
    public ResultBean qryOrder(@PathVariable("id") String orderId){
        OrderInfoVo orderInfoVo = orderService.findByOrderId(orderId);
        if(orderInfoVo != null){
            return ResultBean.getSuccessRes(orderInfoVo);
        }else{
            return ResultBean.getDefaultFailRes("未查询到指定订单！");
        }
    }

    @GetMapping(value = "/qryAll")
    public ResultBean qryAll(int pageNum, int pageSize){
        //使用分页插件,核心代码就这一行
        PageHelper.startPage(pageNum, pageSize);
        List<OrderInfoVo> orderInfoVoList = orderService.findAllOrders();
        if(orderInfoVoList != null){
            PageInfo<OrderInfoVo> orderPageInfo = new PageInfo<>(orderInfoVoList);
            return ResultBean.getSuccessRes(orderPageInfo);
        }else{
            return ResultBean.getDefaultFailRes("未查询到订单！");
        }
    }
}
