package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface IProductService {
    //增加
    public int addProduct(Product product);
    //删除
    public int deleteProduct(int productId);
    //更改
    public int updateProduct(Product product);
    //查询所有
    public List<Product> searchProductAll();
    //查找单个
    Product selectByPrimaryKey(Integer id);
    //上架
    public int upperShelf(int id);
    //下架
    public int downShelf(int id);
    //分页查询
    public  List<Product> pageSearch(int start);




    //springboot 增加或更新商品
    public ServerResponse addOrUpdate(Product product);
    //springboot 产品地上下架
    public ServerResponse setStatus(Integer productId,Integer status);
    //springboot 后台获取商品详细信息
    public ServerResponse getProductMessage(Integer productId);
    //springboot 分页查询所有商品信息
    public ServerResponse searchProductList(Integer pageNum,Integer pageSize);
    //springboot 模糊查询商品
    public ServerResponse searchProduct(Integer productId,String productName,Integer pageNum,Integer pageSize);
    //springboot 图片上传的实现
    public ServerResponse uploadServerSponse(MultipartFile file,String path);
    //springboot 前台获取商品详细信息
    public ServerResponse portalGetProductMessage(Integer productId);
    //springboot 前台的商品搜索
    public ServerResponse portalSerchaProduct(Integer categoryId, String productName, Integer pageNum, Integer size, String orderBy);
    //soringboot  更新商品库存
    public ServerResponse updateStock(Integer productId,Integer stock);
    //查询热销品
    public ServerResponse hotProduct();
}
