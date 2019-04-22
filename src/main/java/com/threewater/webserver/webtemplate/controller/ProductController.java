package com.threewater.webserver.webtemplate.controller;

import com.threewater.webserver.webtemplate.exception.CommonException;
import com.threewater.webserver.webtemplate.po.ProductStoreInfo;
import com.threewater.webserver.webtemplate.service.ProductService;
import com.threewater.webserver.webtemplate.service.TokenAuthService;
import com.threewater.webserver.webtemplate.util.ResultBean;
import com.threewater.webserver.webtemplate.vo.ProductInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/prod")
@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private TokenAuthService tokenAuthService;

    @Autowired
    private ProductService productService;

    @Value("${custom.upload.path:/home/threewater/")
    private String filePath;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultBean addProd(@RequestParam("img") MultipartFile img, @RequestParam("thImg") MultipartFile thImg,
                              ProductInfoVo productInfoVo, @RequestHeader("Authorization") String token){
        Authentication authentication = tokenAuthService.getAuthentication(token);
        String userId = ((User)authentication.getPrincipal()).getPassword();
        productInfoVo.setUserId(userId);

        String oriName = img.getOriginalFilename();
        String fileName = UUID.randomUUID() + oriName.substring(oriName.lastIndexOf("."));
        File imgDest = new File(filePath + userId + File.pathSeparator+ fileName);

        String oriThImgName = img.getOriginalFilename();
        String thImgFileName = UUID.randomUUID() + oriThImgName.substring(oriThImgName.lastIndexOf("."));
        File thImgDest = new File(filePath + userId + File.pathSeparator+ thImgFileName);

        try {
            img.transferTo(imgDest);
            thImg.transferTo(thImgDest);
            productInfoVo.setImg(imgDest.getCanonicalPath());
            productInfoVo.setThImg(thImgDest.getCanonicalPath());
            if(productService.addProdInfo(productInfoVo) > 0){
                return ResultBean.getSuccessRes("添加商品成功！");
            }else{
                return ResultBean.getDefaultFailRes("添加商品失败！");
            }
        } catch (Exception e) {
            throw new CommonException("TWFT0007", e, e.getMessage());
        }
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public ResultBean delProd(@PathVariable("id") String prodId){
        if(productService.deleteByProdId(prodId) > 0){
            return ResultBean.getSuccessRes("删除商品成功！");
        }else{
            return ResultBean.getDefaultFailRes("删除商品失败！");
        }
    }

    @RequestMapping(value = "/mod", method = RequestMethod.PUT)
    public ResultBean modifyProd(@RequestBody ProductInfoVo productInfoVo, @RequestHeader("Authorization") String token){
        Authentication authentication = tokenAuthService.getAuthentication(token);
        String userId = ((User)authentication.getPrincipal()).getPassword();
        productInfoVo.setUserId(userId);
        if(productService.updateProdInfo(productInfoVo) > 0){
            return ResultBean.getSuccessRes("修改商品成功！");
        }else{
            return ResultBean.getDefaultFailRes("修改商品失败！");
        }
    }

    @RequestMapping(value = "/qry/{id}", method = RequestMethod.GET)
    public ResultBean qryProd(@PathVariable("id") String prodId){
        ProductInfoVo productInfoVo = productService.queryByProdId(prodId);
        if(productInfoVo != null){
            return ResultBean.getSuccessRes(productInfoVo);
        }else{
            return ResultBean.getDefaultFailRes("未查询到指定商品！");
        }
    }

    @RequestMapping(value = "/qryAll", method = RequestMethod.GET)
    public ResultBean qryAll(){
        //TO-DO 增加翻页
        List<ProductInfoVo> productInfoVoList = productService.queryProdInfosByWhId("1");
        if(productInfoVoList != null){
            return ResultBean.getSuccessRes(productInfoVoList);
        }else{
            return ResultBean.getDefaultFailRes("未查询到商品！");
        }
    }
}
