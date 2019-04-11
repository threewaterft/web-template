package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.po.PayInfo;

public interface PayInfoMapper {
    int deleteByPrimaryKey(String payId);

    int insert(PayInfo record);

    int insertSelective(PayInfo record);

    PayInfo selectByPrimaryKey(String payId);

    int updateByPrimaryKeySelective(PayInfo record);

    int updateByPrimaryKey(PayInfo record);
}