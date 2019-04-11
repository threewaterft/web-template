package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.po.ProductInfo;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(String prodId);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(String prodId);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);
}