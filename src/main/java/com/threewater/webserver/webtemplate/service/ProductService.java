package com.threewater.webserver.webtemplate.service;

import com.threewater.webserver.webtemplate.vo.ProductInfoVo;

import java.util.List;

public interface ProductService {

    List<ProductInfoVo> queryProdInfosByWhId(String wareHouseId);

    ProductInfoVo queryByProdId(String prodId);

    int deleteByProdId(String prodId);

    int updateProdInfo(ProductInfoVo productInfoVo);

    int addProdInfo(ProductInfoVo productInfoVo);
}
