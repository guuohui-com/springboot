package com.neuedu.service.impl;

import com.google.common.collect.Sets;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.CategoryMapper;
import com.neuedu.pojo.Category;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class CategoryImpl implements ICategoryService {


    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public int addCategory(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int deleteCategory(int categoryId) {
        return 0;
    }

    @Override
    public int updateCategory(Category category) {
        System.out.println("==============service+============"+category);
        int row= categoryMapper.updateByPrimaryKey(category);
        System.out.println("===================="+row);
        return row;
    }

    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    @Override
    public Category findByCategoryId(int categoryId) {
        return categoryMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {

        return categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ServerResponse getCategoryByParentId(int categoryId) {
        //参数非空校验
        if(categoryId==0){
            return ServerResponse.createServerResponseByFail("参数不能为空");
        }
        System.out.println("====================================="+categoryId);
        //根据categoryid查询类别
        System.out.println("======================================"+categoryMapper.selectByPrimaryKey(categoryId));
         Category category=categoryMapper.selectByPrimaryKey(categoryId);
        if(category==null){
            return ServerResponse.createServerResponseByFail("该类别不存在");
        }
        //查询子类别
         List<Category> categoryList =categoryMapper.findChildCategory(categoryId);
        //返回结构
        return ServerResponse.createServerResponseBySucces(null,categoryList);
    }

    @Override
    public ServerResponse addCategoryByParentId(int parentId, String categoryName) {

        //参数校验
        if(categoryName==null||categoryName.equals("")){
            return ServerResponse.createServerResponseByFail("类别明不能为空");
        }
        //添加节点
        Category category=new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(1);
        int rows=categoryMapper.insert(category);
        if(rows<=0){
            return ServerResponse.createServerResponseByFail("插入失败");
        }
        // 返回结果

        return ServerResponse.createServerResponseBySucces(null,"插入成功");
    }

    @Override
    public ServerResponse updateCategory(Integer categoryId, String categoryName) {
        //参数校验
        if(categoryId==null||categoryId.equals("")){
            return ServerResponse.createServerResponseByFail("类别ID不能为空");
        }
        if(categoryName==null||categoryName.equals("")){
            return ServerResponse.createServerResponseByFail("类别明不能为空");
        }
        //根据categoryId查询是否有此leibie
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category==null){
            ServerResponse.createServerResponseByFail("要修改的类别不存在");
        }
        //修改
        category.setName(categoryName);
        int rows=categoryMapper.updateByPrimaryKey(category);
        if(rows>0){
            return  ServerResponse.createServerResponseBySucces("修改成功",category);
        }
        //返回结果
        return ServerResponse.createServerResponseByFail("修改失败");
    }


    //擦讯所有子节点
    @Override
    public ServerResponse getAllChildCategory(Integer categoryId) {
        //参数非空校验
        if(categoryId==null){
            return ServerResponse.createServerResponseByFail("类别Id不能为空");
        }
        //查询
        Set<Category> categorySet= Sets.newHashSet();
        categorySet=findAllChildCategory(categorySet,categoryId);
        Set<Integer> integerSet =Sets.newHashSet();
        Iterator<Category> categoryIterator = categorySet.iterator();
        while(categoryIterator.hasNext()){
            Category category=categoryIterator.next();
            integerSet.add(category.getId());
        }
        return ServerResponse.createServerResponseBySucces(null,integerSet);
    }

    @Override
    public ServerResponse getRootCategory() {
        List<Category> categoryList=categoryMapper.getRootCategory();
        return ServerResponse.createServerResponseBySucces(null,categoryList);
    }


    private Set<Category> findAllChildCategory(Set<Category> categorySet,Integer categoryId){
        //参数校验
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if(category!=null){
            //将category加入set
             categorySet.add(category);
        }
        //查找categoryId的子节点（平级）

        List<Category> categoryList=categoryMapper.findChildCategory(categoryId);
        //遍历：ist
        if(categoryList!=null&&categoryList.size()>0){
            for(Category category1:categoryList){
                System.out.println("========================================"+category1.getId());
                findAllChildCategory(categorySet,category1.getId());
            }
        }
        return categorySet;
    }

}
