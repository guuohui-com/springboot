package com.neuedu.service.impl;

import com.google.common.collect.Lists;
import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.dao.CartMapper;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Cart;
import com.neuedu.pojo.Product;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICartService;
import com.neuedu.utils.BigDecimalUtils;
import com.neuedu.viewObject.CartViewObject;
import com.neuedu.viewObject.CartproductViewObject;
import com.neuedu.viewObject.CheckedProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;

    private CheckedProductVo CheckedProductToVo(Integer userId,Integer ptoductId){
        Product product=productMapper.selectByPrimaryKey(ptoductId);
        Cart cart=cartMapper.searchProductBuUserAndPId(userId,ptoductId);
        CheckedProductVo checkedProductVo=new CheckedProductVo();
        checkedProductVo.setCategoryId(product.getCategoryId());
        checkedProductVo.setMainImage(product.getMainImage());
        checkedProductVo.setId(product.getId());
        checkedProductVo.setName(product.getName());
        checkedProductVo.setPrice(product.getPrice());
        checkedProductVo.setBuyNum(cart.getQuantity());
        checkedProductVo.setTotalPrice(BigDecimalUtils.mul(product.getPrice().doubleValue(),cart.getQuantity()));
        return checkedProductVo;
    }

    private CartViewObject cartToVO(Integer userId){


        /*
        说明：CartViewObject是整个购物车前台对象;
        cartsPrduct是后台购物车商品列表对象，包含该用户购车所又得商品对象;
        cartproductViewObjectsList是前台购物车商品列表对象，包含该用户购车所又得商品对象;
        cartproductViewObject是前台购物车商品对象，只包含一个商品,是购物车中的一行; */


        CartViewObject cartViewObject =new CartViewObject();
        //这是整个购物车的总价格
        BigDecimal cartTotalPrice=new BigDecimal("0");
        //购物车中商品的总数量
        int totalNum=0;
        //根据userid查寻购物车中商品信息
        List<Cart> cartsPrduct=cartMapper.selectCartByUserId(userId);
        //封装productList
        List<CartproductViewObject> cartproductViewObjectsList= Lists.newArrayList();
        if(cartsPrduct!=null&cartsPrduct.size()>0){
            for(Cart cart:cartsPrduct){
                //将每一项的product转化为productVO
                CartproductViewObject cartproductViewObject=new CartproductViewObject();
                cartproductViewObject.setId(cart.getId());
                cartproductViewObject.setQuantity(cart.getQuantity());
                cartproductViewObject.setUserId(cart.getUserId());
                cartproductViewObject.setProductChecked(cart.getChecked());
                //查询商品
                Product product = productMapper.selectByPrimaryKey(cart.getProductId());
                if(product!=null){
                    cartproductViewObject.setProductId(cart.getProductId());
                    cartproductViewObject.setProductMainImage(product.getMainImage());
                    cartproductViewObject.setProductName(product.getName());
                    cartproductViewObject.setProductPrice(product.getPrice());
                    cartproductViewObject.setProductStatus(product.getStatus());
                    cartproductViewObject.setProductStock(product.getStock());
                    cartproductViewObject.setProductSubtitle(product.getSubtitle());
                    int stock=product.getStock();
                    int limitproductCount=0;
                    if(stock>cart.getQuantity()){
                        limitproductCount=cart.getQuantity();
                        cartproductViewObject.setLimitedQuantity("库存充足");
                    }else{
                        limitproductCount=stock;
                        //跟新购物车车上牌数量
                        Cart cart1=new Cart();
                        cart1.setId(cart.getId());
                        cart1.setQuantity(stock);
                        cart1.setProductId(cart.getProductId());
                        cart1.setChecked(cart.getChecked());
                        cart1.setUserId(cart.getUserId());
                        cartMapper.updateByPrimaryKey(cart1);
                        cartproductViewObject.setLimitedQuantity("库存不足");
                    }
                    cartproductViewObject.setQuantity(limitproductCount);
                    //设置cartproductViewObject的总价格,也就是说购物项的总价格，也就是一行的总价格
                    cartproductViewObject.setProductTotalPrice(BigDecimalUtils.mul(product.getPrice().doubleValue(),cartproductViewObject.getQuantity()));
                }

                //计算cartViewObject的总价格，也就是整个购物车总价格
                System.out.println("================="+cartproductViewObject+"==================="+cartTotalPrice);
                if(cartproductViewObject.getProductChecked()==1){
                    cartTotalPrice=BigDecimalUtils.add(cartproductViewObject.getProductTotalPrice().doubleValue(),cartTotalPrice.doubleValue());
                }
                cartproductViewObjectsList.add(cartproductViewObject);
                totalNum=cartproductViewObject.getQuantity()+totalNum;
            }

        }

        //封装cartVo
        //设置cartVO的总价格
        cartViewObject.setCartTotalPrice(cartTotalPrice);
        cartViewObject.setTotalNum(totalNum);
        cartViewObject.setCartproductViewObjectList(cartproductViewObjectsList);

        int rows=cartMapper.isAllChecked(userId);
        boolean flag=false;
        if(rows==0){
            flag=true;
        }
        cartViewObject.setAllChecked(flag);
        //返回结果
        return cartViewObject;
    }
    //想购物车添加商品
    @Override
    public ServerResponse addProductToCart(Integer userId,Integer productId, Integer count) {
        //参数校验
        if(productId==null){
            return ServerResponse.createServerResponseByFail("参数为空");
        }
        //根据productId和userId查询看购物车中是否此商品
        Cart cart=cartMapper.selectProductFromCart(userId,productId);
        int rows=0;
        if(cart==null){
            //添加
            Cart cart1=new Cart();
            cart1.setUserId(userId);
            cart1.setProductId(productId);
            cart1.setQuantity(count);
            cart1.setChecked(1);
            System.out.println("=====================service"+cart1.getUserId());
            rows=cartMapper.insert(cart1);
        }else {
            //更新
            Cart cart1=new Cart();
            cart1.setId(cart.getId());
            cart1.setUserId(userId);
            cart1.setProductId(productId);
            cart1.setQuantity(count);
            cart1.setChecked(1);
            System.out.println("=====================service"+cart1.getUserId());
            rows=cartMapper.updateByPrimaryKey(cart1);
        }
        if(rows>0){
            CartViewObject cartViewObject=cartToVO(userId);
            return ServerResponse.createServerResponseBySucces("成功",cartViewObject);
        }
        return ServerResponse.createServerResponseBySucces("失败");
    }

    //查看购物车列表信息
    @Override
    public ServerResponse searchCartProductList(Integer userId) {
        CartViewObject cartViewObject=cartToVO(userId);
        if(cartViewObject==null){
            return ServerResponse.createServerResponseBySucces("您还没有购物车信息");
        }
        return ServerResponse.createServerResponseBySucces(null,cartViewObject);
    }

    //Springboot 购物车中删除某个商品
    @Override
    public ServerResponse deleteCartProductList(Integer userId, String productIds) {

        //参数非空校验
        if(userId==null){
            return ServerResponse.createServerResponseByFail("请您先登录");
        }
        if(productIds==null||productIds.equals("")){
            return ServerResponse.createServerResponseByFail("您指出您要删除的产品");
        }
        //productIds转为集合
        String[] productIdsArray=productIds.split(",");
        List<Integer> productIdList=Lists.newArrayList();
        if(productIdsArray!=null&&productIdsArray.length>0){
            for(String productIdStr:productIdsArray){
                Integer productId=Integer.parseInt(productIdStr);
                productIdList.add(productId);
            }
        }
        //删除
        int rows=cartMapper.deleteByUserIdAndProductIds(userId,productIdList);
        //返回结果
        System.out.println(userId+"============"+productIdList);
        if(rows==0){
            return ServerResponse.createServerResponseByFail("删除失败");
        }
        return ServerResponse.createServerResponseBySucces("删除成功");
    }

    //Springboot 购物车中选中某个商品
    @Override
    public ServerResponse checkCartProduct(Integer userId, Integer productId, Integer check) {
        //参数非空校验
        /*if(productId==null){
            return ServerResponse.createServerResponseByFail("参数为空");
        }*/
        //dao
        int rows=cartMapper.makeCheckedProduct(userId,productId,check);
        //返回结果
        if(rows==0){
            return ServerResponse.createServerResponseByFail("选中状态修改失败");
        }
        return ServerResponse.createServerResponseBySucces("选中状态修改成功");
    }

    @Override
    public ServerResponse searchCartCheckedProduct(Integer userId) {
        List<Cart> productList=cartMapper.searchCartCheckedProduct(userId);
        List<CheckedProductVo> checkedProductVoList=new ArrayList<>();
        if (productList.size() == 0 || productList.equals(null)) {
            return ServerResponse.createServerResponseByFail("查询失败");
        }
        for(Cart product:productList){
            CheckedProductVo checkedProductVo= CheckedProductToVo(userId,product.getProductId());
            System.out.println(product.getId());
            checkedProductVoList.add(checkedProductVo);
        }
        return ServerResponse.createServerResponseBySucces("查询成功",checkedProductVoList);
    }

    /*@Override
    public ServerResponse notCheckCartProduct(Integer userId, Integer productId) {
        //参数非空校验
        if(productId==null){
            return ServerResponse.createServerResponseByFail("参数为空");
        }
        //dao
        int rows=cartMapper.makeCheckedProduct(userId,productId,0);
        //返回结果
        if(rows==0){
            return ServerResponse.createServerResponseByFail("取消选择失败");
        }
        return ServerResponse.createServerResponseBySucces("取消选择成功");
    }*/
}
