package com.neuedu.Controller;

import com.neuedu.pojo.PageInfo;
import com.neuedu.pojo.Product;
import com.neuedu.service.IProductService;
import com.neuedu.service.IuploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired
    IuploadService iuploadService;
    @Autowired
    IProductService iProductService;
    @Autowired
    PageInfo pageInfo;
    @RequestMapping("selectProductAll/{currentPage}")
    public String selectAll(@PathVariable("currentPage") int currentPage, HttpServletRequest request){
        pageInfo.setNumber(2);
        List<Product> allProductList=iProductService.searchProductAll();
        List<Product> productList=iProductService.pageSearch((currentPage-1)*pageInfo.getNumber());
        System.out.println("======================"+productList);
       for(Product product:productList){
          String[] subpic =product.getSubImages().split(" ");
          List<String> subPicList=new ArrayList<>();
          for(int j=0;j<subpic.length;j++){
              subPicList.add(subpic[j]);
          }
          product.setSubPic(subPicList);
       }

       int pageNum=allProductList.size();
       if(pageNum % pageInfo.getNumber()==0){
           pageInfo.setTotalPage(pageNum/pageInfo.getNumber());
       }else {
           pageInfo.setTotalPage((pageNum/pageInfo.getNumber())+1);
       }
       pageInfo.setCurrentPage(currentPage);

       pageInfo.setProducts(productList);
       request.setAttribute("pageInfo",pageInfo);
       request.setAttribute("productInfo",productList);
        return "/product/list";
    }

    @RequestMapping("deleteproduct/{id}")
    public String deleteProduct(@PathVariable("id") int productId){
        int rows=iProductService.deleteProduct(productId);
        if(rows>0){
           return "redirect:/selectProductAll/1";
        }else {
            //String msg ="===================删除失败====";
            return "error.ftl";
        }
    }

    @RequestMapping(value = "insertproduct",method = RequestMethod.POST)
    public String insertProduct(@RequestParam("name") String name, @RequestParam("categoryId") int categoryId,
                                @RequestParam("price") BigDecimal price, @RequestParam("stock") int stock,
                                @RequestParam("status") int status, @RequestParam("mainImage") MultipartFile[] mainImage){
        String newmainfileName=null;
        String newsubfileName =null;
        String allPicName="";
        for(int i=0;i<mainImage.length;i++){
            if(i!=0){
                System.out.println("=============================="+mainImage[i].getOriginalFilename());
                String subuuid= UUID.randomUUID().toString();
                String subfileName=mainImage[i].getOriginalFilename();
                //截取后缀名
                String subextendName=mainImage[i].getOriginalFilename().substring(subfileName.lastIndexOf("."));
                newsubfileName=subuuid+subextendName;
                allPicName+=newsubfileName+",";
                File file=new File("/neuedu");
                if(!file.exists()){
                    file.mkdir();
                }
                File newsubfile=new File(file,newsubfileName);
                try {
                    mainImage[i].transferTo(newsubfile);
                    iuploadService.uploadFile(newsubfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                //获取后缀名
                String mainfileName=mainImage[0].getOriginalFilename();
                String mainextendName=mainfileName.substring(mainfileName.lastIndexOf("."));
                //uuid
                String mainuuid=UUID.randomUUID().toString();
                //System.out.println("===========mainImage=========="+mainImage.length);
                newmainfileName=mainuuid+mainextendName;
                allPicName+=newmainfileName+",";
                File file=new File("E:\\实训视频\\springMVC\\upload");
                if(!file.exists()){
                    file.mkdir();
                }
                File newmainfile=new File(file,newmainfileName);
                try {
                    mainImage[i].transferTo(newmainfile);
                    iuploadService.uploadFile(newmainfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Product product=new Product();
        product.setCategoryId(categoryId);
        product.setName(name);
        product.setStock(stock);
        product.setPrice(price);
        product.setStatus(status);
        product.setMainImage(newmainfileName);
        product.setSubImages(allPicName);
        //System.out.println("=============================================="+allPicName);

        int rows =iProductService.addProduct(product);
        if(rows>0){
            return "forward:selectProductAll/1";
        }else{
            System.out.println("=======插入失败=====");
            return "error.ftl";
        }
    }

    @RequestMapping(value = "insertproduct",method = RequestMethod.GET)
    public String insert(){
        return "product/insert";
    }

    @RequestMapping(value = "updateProduct/{id}",method = RequestMethod.POST)
    public String updateProduct(Product product){
        //System.out.println("==============updateproductA==============="+product);
        int rows=iProductService.updateProduct(product);
        //System.out.println("====================================="+rows);
        if(rows>0){
            return "redirect:/selectProductAll/1";
        }else{
            String msg="修改失败";
            return "error.ftl";
        }
    }
    @RequestMapping(value = "updateProduct/{id}",method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, HttpServletRequest request){
        Product product =iProductService.selectByPrimaryKey(id);
        request.setAttribute("product",product);
        return "/product/index";
    }

    //上架
    @RequestMapping("upperShlef/{id}")
    public String upperShelf(@PathVariable("id") int id, HttpServletRequest request) {
        int rows=iProductService.upperShelf(id);
        if(rows>0){
            //上架成功
            return "redirect:/selectProductAll/1";
        }else {
            //上架失败
            String msg="上架失败";
            request.setAttribute("msg",msg);
            return "error.ftl";
        }
    }

    //下架
    @RequestMapping("downShlef/{id}")
    public String downShelf(@PathVariable("id") int id, HttpServletRequest request) {
        int rows=iProductService.downShelf(id);
        if(rows>0){
            //下架成功
            return "redirect:/selectProductAll/1";
        }else {
            //上架失败
            String msg="下架失败";
            request.setAttribute("msg",msg);
            return "error.ftl";
        }
    }

}
