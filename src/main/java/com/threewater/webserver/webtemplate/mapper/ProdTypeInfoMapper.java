package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.po.ProdTypeInfo;

public interface ProdTypeInfoMapper {
    int insert(ProdTypeInfo record);

    int insertSelective(ProdTypeInfo record);
}