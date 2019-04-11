package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.po.ProductStoreInfo;

public interface ProductStoreInfoMapper {
    int deleteByPrimaryKey(String prodStoreId);

    int insert(ProductStoreInfo record);

    int insertSelective(ProductStoreInfo record);

    ProductStoreInfo selectByPrimaryKey(String prodStoreId);

    int updateByPrimaryKeySelective(ProductStoreInfo record);

    int updateByPrimaryKey(ProductStoreInfo record);
}