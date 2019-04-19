package com.threewater.webserver.webtemplate.service;

import com.threewater.webserver.webtemplate.vo.ProductInfoVo;

import java.util.List;

public interface ProductService {

    List<ProductInfoVo> queryProdInfosByWhId(String wareHouseId);

    ProductInfoVo queryByProdId(String id);

    int deleteByProdId(String id);

    int updateProdInfo(ProductInfoVo productInfoVo);

    int addProdInfo(ProductInfoVo productInfoVo);
}
