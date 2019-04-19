package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.po.PayInfo;
import com.threewater.webserver.webtemplate.po.PayInfoKey;

public interface PayInfoMapper {
    int deleteByPrimaryKey(PayInfoKey key);

    int insert(PayInfo record);

    int insertSelective(PayInfo record);

    PayInfo selectByPrimaryKey(PayInfoKey key);

    int updateByPrimaryKeySelective(PayInfo record);

    int updateByPrimaryKey(PayInfo record);
}