package com.neuedu.Controller;

import com.neuedu.pojo.Category;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/user/category/")
public class CategoryController {

    @Autowired
    ICategoryService iCategoryService;
    @RequestMapping("find")
    public  String findAll(HttpSession session){
        List<Category> categoryList=iCategoryService.findAll();
        session.setAttribute("categoryList",categoryList);
        return "list.ftl";
    }
    @RequestMapping(value = "update/{id}",method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer categoryId, HttpServletRequest request){
        Category category =iCategoryService.findByCategoryId(categoryId);
        System.out.println(category);
        request.setAttribute("category",category);
        return "index.ftl";
    }

    @RequestMapping(value = "update/{id}",method = RequestMethod.POST)
    public String update(Category category,HttpServletRequest request){
        System.out.println("================hello=="+category+"-===================================");
        int count =  iCategoryService.updateCategory(category);
        System.out.println(count+"===========================");
        if(count>0){
            return "redirect:/user/category/find";
        }else{
            String msg="修改失败";
            request.setAttribute("msg",msg);
            return "error.ftl";
        }

    }

    @RequestMapping(value = "insertcategory",method = RequestMethod.GET)
    public String insertCategroy(){
        return "insertCategory.ftl";
    }

    @RequestMapping(value = "insertcategory",method = RequestMethod.POST)
    public String insertCategroy(Category category, HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("======================"+category.getName());
        int rows=iCategoryService.addCategory(category);
        System.out.println("==============rows=========="+rows);
        if(rows>0){
            return "redirect:/user/category/find";
        }else{
            String msg="插入失败";
            return "error.ftl";
        }
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable("id") int categoryId, HttpServletRequest request){
        int rows = iCategoryService.deleteByPrimaryKey(categoryId);
        if(rows>0){
            return "redirect:/user/category/find";
        }else{
            String msg="删除category错误";
            request.setAttribute("msg",msg);
            return "error.ftl";
        }
    }
}
