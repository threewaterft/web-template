package com.threewater.webserver.webtemplate.service;

import com.threewater.webserver.webtemplate.po.OrderDetailInfo;
import com.threewater.webserver.webtemplate.vo.OrderInfoVo;

import java.util.List;

public interface OrderService {
    OrderInfoVo findByOrderId(String orderId);

    List<OrderDetailInfo> findDetailByOrderId(String orderId);

    int deleteByOrderId(String orderId);

    int updateOrderInfo(OrderInfoVo orderInfoVo);

    int addOrderInfo(OrderInfoVo orderInfoVo);
}
