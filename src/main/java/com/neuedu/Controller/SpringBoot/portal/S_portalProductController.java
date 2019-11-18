package com.neuedu.Controller.SpringBoot.portal;

import com.neuedu.common.ServerResponse;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Product 前台接口
@RestController
@RequestMapping("/portal/product")
@CrossOrigin
public class S_portalProductController {

    @Autowired
    IProductService iProductService;
    //springboot 前台查看商品详细信息
    @RequestMapping("lookProductDetail")
    public ServerResponse lookProductDetail(Integer productId){
        return iProductService.portalGetProductMessage(productId);
    }

    //springboot 前台搜索商品并排序
    @RequestMapping("portalSerchaProduct")
    public  ServerResponse portalSerchaProduct(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                                               @RequestParam(value = "productName",required = false) String productName,
                                               @RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                                               @RequestParam(value = "size",required = false,defaultValue = "10") Integer size,
                                               @RequestParam(value = "orderBy",required = false,defaultValue = "") String orderBy){

        return iProductService.portalSerchaProduct(categoryId,productName,pageNum,size,orderBy);

    }
}
