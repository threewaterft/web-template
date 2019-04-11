package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.po.OrderDetailInfo;

public interface OrderDetailInfoMapper {
    int deleteByPrimaryKey(String orderDetailId);

    int insert(OrderDetailInfo record);

    int insertSelective(OrderDetailInfo record);

    OrderDetailInfo selectByPrimaryKey(String orderDetailId);

    int updateByPrimaryKeySelective(OrderDetailInfo record);

    int updateByPrimaryKey(OrderDetailInfo record);
}