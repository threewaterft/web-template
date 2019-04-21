package com.threewater.webserver.webtemplate.service.impl;

import com.threewater.webserver.webtemplate.mapper.ProductInfoMapper;
import com.threewater.webserver.webtemplate.mapper.ProductInfoVoMapper;
import com.threewater.webserver.webtemplate.mapper.ProductStoreInfoMapper;
import com.threewater.webserver.webtemplate.po.ProductInfo;
import com.threewater.webserver.webtemplate.po.ProductStoreInfo;
import com.threewater.webserver.webtemplate.service.ProductService;
import com.threewater.webserver.webtemplate.util.sequence.twitter.SnowFlakeSequence;
import com.threewater.webserver.webtemplate.vo.ProductInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoVoMapper productInfoVoMapper;
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private ProductStoreInfoMapper productStoreInfoMapper;
    @Override
    public List<ProductInfoVo> queryProdInfosByWhId(String wareHouseId) {
        return productInfoVoMapper.queryProdInfosByWhId(wareHouseId);
    }

    @Override
    public ProductInfoVo queryByProdId(String prodId) {
        return productInfoVoMapper.queryByProdId(prodId);
    }

    @Override
    @Transactional
    public int deleteByProdId(String prodId) {
        if(productStoreInfoMapper.deleteByProdId(prodId) > 0 && productInfoMapper.deleteByPrimaryKey(prodId) > 0){
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional
    public int updateProdInfo(ProductInfoVo productInfoVo) {
        ProductStoreInfo productStoreInfo = new ProductStoreInfo(SnowFlakeSequence.genPKId(), productInfoVo.getWhId(), productInfoVo.getProdPos(), productInfoVo.getUserId(),
                productInfoVo.getProdAmount(), productInfoVo.getUnit(), productInfoVo.getProdId());
        ProductInfo productInfo = new ProductInfo(SnowFlakeSequence.genPKId(), productInfoVo.getProdName(),productInfoVo.getProdTypeId(), productInfoVo.getSinglePrice(),
                productInfoVo.getThImg(), productInfoVo.getImg(), productInfoVo.getProdDesc(), new Date());
        if(productStoreInfoMapper.updateByPrimaryKey(productStoreInfo) > 0 && productInfoMapper.updateByPrimaryKey(productInfo) > 0){
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional
    public int addProdInfo(ProductInfoVo productInfoVo) {
        ProductStoreInfo productStoreInfo = new ProductStoreInfo(SnowFlakeSequence.genPKId(), productInfoVo.getWhId(), productInfoVo.getProdPos(), productInfoVo.getUserId(),
                productInfoVo.getProdAmount(), productInfoVo.getUnit(), productInfoVo.getProdId());
        ProductInfo productInfo = new ProductInfo(SnowFlakeSequence.genPKId(), productInfoVo.getProdName(),productInfoVo.getProdTypeId(), productInfoVo.getSinglePrice(),
                productInfoVo.getThImg(), productInfoVo.getImg(), productInfoVo.getProdDesc(), new Date());
        if(productStoreInfoMapper.insert(productStoreInfo) > 0 && productInfoMapper.insert(productInfo) > 0){
            return 1;
        }

        return 0;
    }
}
