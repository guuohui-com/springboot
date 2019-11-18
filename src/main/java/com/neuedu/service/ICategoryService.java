package com.neuedu.service;

import com.neuedu.common.ServerResponse;
import com.neuedu.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoryService
{
    //添加类别
    public int addCategory(Category category);
    //删除类别
    public int deleteCategory(int categoryId);
    //修改类别
    public int updateCategory(Category category);
    //查询类别
    public List<Category> findAll();
    //根据类别Id查询类别信息
    public Category findByCategoryId(int category);
    //根据id删除
    public int deleteByPrimaryKey(Integer id);



    //springboot 获取品类子节点
    public ServerResponse getCategoryByParentId(int categoryId);
    //springboot 插入新的类别
    public  ServerResponse addCategoryByParentId(int parentId,String categoryName);
    //springboot 修改节点
    public  ServerResponse updateCategory(Integer categoryId,String catename);
    //springboot 查询所有子节点
    public  ServerResponse getAllChildCategory(Integer categoryId);
    //查询根节点
    public ServerResponse getRootCategory();
}
