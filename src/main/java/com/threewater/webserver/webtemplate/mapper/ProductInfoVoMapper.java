package com.threewater.webserver.webtemplate.mapper;

import com.threewater.webserver.webtemplate.vo.ProductInfoVo;

import java.util.List;

public interface ProductInfoVoMapper {
    List<ProductInfoVo> queryProdInfosByWhId(String wareHouseId);

    ProductInfoVo queryByProdId(String prodId);
}
