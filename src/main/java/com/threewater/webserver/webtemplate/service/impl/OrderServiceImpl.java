package com.threewater.webserver.webtemplate.service.impl;

import com.threewater.webserver.webtemplate.mapper.OrderDetailInfoMapper;
import com.threewater.webserver.webtemplate.mapper.OrderInfoMapper;
import com.threewater.webserver.webtemplate.mapper.OrderInfoVoMapper;
import com.threewater.webserver.webtemplate.po.OrderDetailInfo;
import com.threewater.webserver.webtemplate.po.OrderInfo;
import com.threewater.webserver.webtemplate.service.OrderService;
import com.threewater.webserver.webtemplate.util.sequence.twitter.SnowFlakeSequence;
import com.threewater.webserver.webtemplate.vo.OrderInfoVo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderDetailInfoMapper orderDetailInfoMapper;
    @Autowired
    private OrderInfoVoMapper orderInfoVoMapper;

    @Override
    public OrderInfoVo findByOrderId(String orderId) {
        return orderInfoVoMapper.findByOrderId(orderId);
    }

    @Override
    public List<OrderDetailInfo> findDetailByOrderId(String orderId) {
        return orderInfoVoMapper.findDetailByOrderId(orderId);
    }

    @Override
    @Transactional
    public int deleteByOrderId(String orderId) {
        List<OrderDetailInfo> orderDetailInfoList = findDetailByOrderId(orderId);
        if(orderDetailInfoList != null){
            for(OrderDetailInfo orderDetailInfo: orderDetailInfoList){
                orderDetailInfoMapper.deleteByPrimaryKey(orderDetailInfo.getOrderDetailId());
            }
        }
        if(orderInfoMapper.deleteByPrimaryKey(orderId) > 0){
            return 1;
        }
        return 0;
    }

    @Override
    public int updateOrderInfo(OrderInfoVo orderInfoVo) {
        OrderInfo orderInfo = new OrderInfo(SnowFlakeSequence.genPKId(), new Date(), orderInfoVo.getUserId(), orderInfoVo.getCustomerName(), orderInfoVo.getTotalPrice(), "", new Date(),
                orderInfoVo.getCustomerTel(), orderInfoVo.getState(), orderInfoVo.getOrderDesc());
        List<OrderDetailInfo> orderDetailInfoList = orderInfoVo.getOrderDetailInfoList();

        if(orderInfoMapper.updateByPrimaryKey(orderInfo) > 0){
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional
    public int addOrderInfo(OrderInfoVo orderInfoVo) {
        OrderInfo orderInfo = new OrderInfo(SnowFlakeSequence.genPKId(), new Date(), orderInfoVo.getUserId(), orderInfoVo.getCustomerName(), orderInfoVo.getTotalPrice(), "", new Date(),
                orderInfoVo.getCustomerTel(), orderInfoVo.getState(), orderInfoVo.getOrderDesc());
        List<OrderDetailInfo> orderDetailInfoList = orderInfoVo.getOrderDetailInfoList();

        if(orderInfoMapper.insert(orderInfo) > 0){
            for(OrderDetailInfo orderDetailInfo: orderDetailInfoList){
                orderDetailInfoMapper.insert(orderDetailInfo);
            }
            return 1;
        }
        return 0;
    }
}
