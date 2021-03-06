package com.threewater.webserver.webtemplate.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Value("${custom.upload.path:/home/threewater/}")
    private String filePath;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultBean addProd(ProductInfoVo productInfoVo,
                              @RequestHeader("Authorization") String token){
        Authentication authentication = tokenAuthService.getAuthentication(token);
        String userId = (String) authentication.getDetails();
        productInfoVo.setUserId(userId);

        if(productService.addProdInfo(productInfoVo) > 0){
            return ResultBean.getSuccessRes("添加商品成功！");
        }else{
            return ResultBean.getDefaultFailRes("添加商品失败！");
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResultBean addProd(@RequestParam("img") MultipartFile img, @RequestParam("thImg") MultipartFile thImg, @RequestHeader("Authorization") String token){
        Authentication authentication = tokenAuthService.getAuthentication(token);
        String userId = (String) authentication.getDetails();

        String oriName = img.getOriginalFilename();
        String thOriName = thImg.getOriginalFilename();
        String fileName = UUID.randomUUID() + oriName.substring(oriName.lastIndexOf("."));
        String thFileName = UUID.randomUUID() + thOriName.substring(thOriName.lastIndexOf("."));
        File imgDestDir = new File(filePath+userId);
        if(!imgDestDir.exists()){
            imgDestDir.mkdirs();
        }
        File imgDest = new File(imgDestDir, fileName);
        File thImgDest = new File(imgDestDir, thFileName);
        try {
            img.transferTo(imgDest);
            thImg.transferTo(thImgDest);
            return ResultBean.getSuccessRes("上传成功！");
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
        String userId = (String) authentication.getDetails();
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
    public ResultBean qryAll(int pageNum, int pageSize){
        //使用分页插件,核心代码就这一行
        PageHelper.startPage(pageNum, pageSize);
        List<ProductInfoVo> productInfoVoList = productService.queryProdInfosByWhId("1");
        if(productInfoVoList != null){
            PageInfo<ProductInfoVo> productPageInfo = new PageInfo<>(productInfoVoList);
            return ResultBean.getSuccessRes(productPageInfo);
        }else{
            return ResultBean.getDefaultFailRes("未查询到商品！");
        }
    }
}
