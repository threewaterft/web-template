package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.po.OrderDetailInfo;
import com.threewater.webserver.webtemplate.vo.OrderInfoVo;

import java.util.List;

public interface OrderInfoVoMapper {
    OrderInfoVo findByOrderId(String orderId);

    List<OrderDetailInfo> findDetailByOrderId(String orderId);
}
