package com.neuedu.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.CategoryMapper;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Category;
import com.neuedu.pojo.PageInfo;
import com.neuedu.pojo.Product;
import com.neuedu.service.ICategoryService;
import com.neuedu.service.IProductService;
import com.neuedu.utils.DateUtils;
import com.neuedu.viewObject.ProductMessageViewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.sasl.SaslServer;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    PageInfo pageInfo;
    @Autowired
    ICategoryService iCategoryService;

    @Override
    public int addProduct(Product product) {
        return productMapper.insert(product);
    }

    @Override
    public int deleteProduct(int productId) {
        return productMapper.deleteByPrimaryKey(productId);
    }

    @Override
    public int updateProduct(Product product) {
       int rows= productMapper.updateByPrimaryKey(product);
        return rows;
    }

    @Override
    public List<Product> searchProductAll() {
        List<Product> productList= productMapper.selectAll();
        return productList;
    }

    @Override
    public Product selectByPrimaryKey(Integer id) {
        Product product=productMapper.selectByPrimaryKey(id);
        return product;
    }

    //上架
    @Override
    public int upperShelf(int id) {
        return productMapper.upperShelf(id);
    }

    //下架
    @Override
    public int downShelf(int id) {
        return productMapper.downShelf(id);
    }

    @Override
    public List<Product> pageSearch(int start) {
        System.out.println("===========================service"+productMapper.pageSearch(start));
        pageInfo.setProducts(productMapper.pageSearch(start));
        return pageInfo.getProducts();
    }




    /*springboot 增加或修改商品*/
    @Override
    public ServerResponse addOrUpdate(Product product) {
        //参数非空校验
        if(product==null){
            return ServerResponse.createServerResponseByFail("参数为空");
        }
        //设置商品的主图
        String subImages=product.getSubImages();
        if(subImages!=null&&!subImages.equals("")){
            String[] subimageArr= subImages.split(",");
            if(subimageArr.length>0){
                product.setMainImage(subimageArr[0]);
            }
        }
        //判断添加还是更新，更具id是否为空
        if(product.getId()==null){
            int rowsInsert=productMapper.insert(product);
            if(rowsInsert>0){
                return ServerResponse.createServerResponseBySucces("插入成功",product);
            }else {
                return ServerResponse.createServerResponseByFail("插入失败");
            }
        }else{
            int rowsUpdate=productMapper.updateByPrimaryKey(product);
            if(rowsUpdate>0){
                return ServerResponse.createServerResponseBySucces("修改成功",product);
            }else {
                return ServerResponse.createServerResponseByFail("修改失败");
            }
        }
        //返回结果
    }

    //产品上下架
    @Override
    public ServerResponse setStatus(Integer productId, Integer status) {
        //参数的非空校验
        if(productId ==null){
            return ServerResponse.createServerResponseByFail("productId参数为空");
        }
        if(status==null){
            return ServerResponse.createServerResponseByFail("status参数为空");
        }
        //更新商品状态
        Product product=new Product();
        product.setId(productId);
        product.setStatus(status);
        int rows= productMapper.updateByPrimaryKey(product);
        //返回结果
        if(rows>0){
            return ServerResponse.createServerResponseBySucces(null,"修改成功");
        }

        return ServerResponse.createServerResponseByFail("修改失败");
    }

    //获取产品详细信息
    @Override
    public ServerResponse getProductMessage(Integer productId){
        //参数的非空校验
        if(productId ==null){
            return ServerResponse.createServerResponseByFail("productId参数为空");
        }
        //查找商品
        Product product=productMapper.selectByPrimaryKey(productId);
        if(product==null){
            return ServerResponse.createServerResponseByFail("没有该商品");
        }
        //product转前端对象VO
        ProductMessageViewObject productMessageViewObject=productToVO(product);
        //返回信息
        return ServerResponse.createServerResponseBySucces("查询成功",productMessageViewObject);
    }

    private ProductMessageViewObject productToVO(Product product){

        ProductMessageViewObject productMessageViewObject=new ProductMessageViewObject();
        productMessageViewObject.setCategoryId(product.getId());
        productMessageViewObject.setCreateTime(DateUtils.dateToString(product.getCreateTime()));
        productMessageViewObject.setDetail(product.getDetail());
        productMessageViewObject.setName(product.getName());
        productMessageViewObject.setMainImage(product.getMainImage());
        productMessageViewObject.setId(product.getId());
        productMessageViewObject.setPrice(product.getPrice());
        productMessageViewObject.setStatus(product.getStatus());
        productMessageViewObject.setStock(product.getStock());
        productMessageViewObject.setSubtitle(product.getSubtitle());
        productMessageViewObject.setUpdateTime(DateUtils.dateToString(product.getUpdateTime()));

        String[] subpic =product.getSubImages().split(",");
        List<String> subPicList=new ArrayList<>();
        for(int j=0;j<subpic.length;j++){
            subPicList.add(subpic[j]);
        }
        productMessageViewObject.setSubImages(subPicList);
       /* Category category =categoryMapper.selectByPrimaryKey(product.getId());
        if(category!=null){
            productMessageViewObject.setP
        }*/
        return productMessageViewObject;
    }

    //分页查询所有产品列表
    @Override
    public ServerResponse searchProductList(Integer pageNum, Integer pageSize) {
        System.out.println(pageNum+"================================"+pageSize);
        // 分页查询商品数据
        if(pageNum==null){
            return ServerResponse.createServerResponseByFail("当前页位空");
        }
        if(pageSize==null){
            return ServerResponse.createServerResponseByFail("每页的个数为空");
        }

        int pageNum1=pageNum;
        int pageSize1=pageSize;
        List<Product>productList=productMapper.springbootPageSearch((pageNum1-1)*pageSize1,pageSize1);
        List<Product>productList1=productMapper.selectAll();
        int totalPage=productList1.size()/pageSize;
        if(productList1.size() % pageSize>0){
            totalPage++;
        }
        pageInfo.setProducts(productList);
        pageInfo.setTotalPage(totalPage);
        pageInfo.setNumber(pageSize);
        pageInfo.setCurrentPage(pageNum);

        if(pageInfo.getCurrentPage()==0||pageInfo.getCurrentPage()>pageInfo.getTotalPage()){
            return ServerResponse.createServerResponseByFail("不合法的页数");
        }

        return ServerResponse.createServerResponseBySucces(null,pageInfo);
    }

    //springboot 模糊查询商品
    @Override
    public ServerResponse searchProduct(Integer productId, String productName, Integer pageNum, Integer pageSize) {

        //如果productId不是空就按这个查询
        if(productName!=null&&!productName.equals("")){
            productName="%"+productName+"%";
        }
        //如果productId是空，就按照productName实现模糊查询

        int start=(pageNum-1)*pageSize;
        List<Product> productList=productMapper.searchProduct(productId,productName,start,pageSize);
        List<Product> productList1=productMapper.searchProductAll(productId,productName);
        int totalPage=productList1.size()/pageSize;
        if(productList1.size()%pageSize>0){
            totalPage++;
        }
        pageInfo.setProducts(productList);
        pageInfo.setCurrentPage(pageNum);
        pageInfo.setTotalPage(totalPage);
        pageInfo.setNumber(pageSize);
        if(pageInfo!=null){
            return ServerResponse.createServerResponseBySucces("查询成功",pageInfo);
        }
        return ServerResponse.createServerResponseByFail("查询失败");
    }

    @Override
    public ServerResponse uploadServerSponse(MultipartFile file, String path) {
        //参数非空判断
        if(file==null){
            ServerResponse.createServerResponseByFail("上传文件为空");
        }
        //获取图片名字
        String orignFileNamae=file.getOriginalFilename();
        //获取图片扩展名
        String extendName = orignFileNamae.substring(orignFileNamae.lastIndexOf("."));
        //重新生成名字
        String newFileName = UUID.randomUUID().toString()+extendName;

        File pathFile=new File(path);
        if(!pathFile.exists()){
            pathFile.setWritable(true);
            pathFile.mkdir();
        }

        File file1=new File(path,newFileName);
        try {
            file.transferTo(file1);

            Map<String,String> map= Maps.newHashMap();
            map.put("uri",newFileName);
            map.put("url","http://localhost:8080/"+newFileName);
            return ServerResponse.createServerResponseBySucces("上传成功",map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ServerResponse.createServerResponseByFail("上传失败");
    }

    //前台接口 获取商品详情
    @Override
    public ServerResponse portalGetProductMessage(Integer productId) {
        //参数的非空校验
        if(productId ==null){
            return ServerResponse.createServerResponseByFail("productId参数为空");
        }
        //查找商品
        Product product=productMapper.selectByPrimaryKey(productId);
        if(product==null){
            return ServerResponse.createServerResponseByFail("没有该商品");
        }
        //校验商品状态
        if(product.getStatus()!=1){
            return ServerResponse.createServerResponseByFail("商品已下架");
        }
        //获取productVO
        ProductMessageViewObject productMessageViewObject = productToVO(product);
        //返回结果

        return ServerResponse.createServerResponseBySucces("查询成功",productMessageViewObject);
    }

    //前台子查询 模糊查询 商品 排序 分页
    @Override
    public ServerResponse portalSerchaProduct(Integer categoryId, String productName, Integer pageNum, Integer size, String orderBy) {

        Set<Integer> integerSet= Sets.newHashSet();
        //参数校验 categoryId和productName不能同时为空
        if(categoryId==null&&(productName==null||pageNum.equals(""))){
            return ServerResponse.createServerResponseByFail("无查询条件");
        }
        //根据categoryId查询
        if(categoryId!=null){
            System.out.println("===============================================1111");
            //判断此类别有吗
            Category category=categoryMapper.selectByPrimaryKey(categoryId);
            if(category==null&&(productName==null||productName.equals(""))){
                //说明没有此商品类数据
                List<ProductMessageViewObject> productMessageViewObjects= Lists.newArrayList();
                return ServerResponse.createServerResponseBySucces("查询成功");
            }
            ServerResponse serverResponse =iCategoryService.getAllChildCategory(categoryId);
            if(serverResponse.isSuccess()){
                integerSet= (Set<Integer>) serverResponse.getData();

            }
        }

        //根据productName模糊查询
        if(productName!=null&&!productName.equals("")){
            productName="%"+productName+"%";
        }
        int start = (pageNum-1)*size;
        List<Product> productList=productMapper.portalSearchproduct(categoryId,integerSet,productName,orderBy,start,size);
       /* List<ProductMessageViewObject> productMessageViewObjectList=Lists.newArrayList();
        //转换VO
        if(productList!=null&&productList.size()>0){
            for (Product product:productList){
                productMessageViewObjectList.add(productToVO(product));
            }
        }*/
        List<Product> productList1=productMapper.portalSearchproductAll(categoryId,integerSet,productName,orderBy);
        int totalPage=productList1.size()/pageNum;
        if(productList1.size()%pageNum!=0){
            totalPage++;
        }
        //封装pageInfo
        PageInfo pageInfo =new PageInfo();
        pageInfo.setNumber(size);
        pageInfo.setCurrentPage(pageNum);
        pageInfo.setProducts(productList);
        pageInfo.setTotalPage(totalPage);
        return ServerResponse.createServerResponseBySucces("查询成功",pageInfo);
    }

    @Override
    public ServerResponse updateStock(Integer productId, Integer stock) {
        if(productId ==null){
            return ServerResponse.createServerResponseByFail("productId参数为空");
        }
        if(stock==null){
            return ServerResponse.createServerResponseByFail("stock为空");
        }
        Product product=productMapper.selectByPrimaryKey(productId);
        if(product==null){
            return ServerResponse.createServerResponseByFail("无此商品信息");
        }
        int rows=productMapper.updateStock(productId,stock);
        if(rows>0){
            return ServerResponse.createServerResponseBySucces(null,"修改库存成功");
        }
        return ServerResponse.createServerResponseByFail("跟新失败");
    }

    @Override
    public ServerResponse hotProduct() {

        List <Product> hotProduct = productMapper.selectByIsHot();
        if (hotProduct==null||hotProduct.size()==0){
            return ServerResponse.createServerResponseByFail("没有热销品");
        }
        return ServerResponse.createServerResponseBySucces(null,hotProduct);
    }

}
