package shop.dao;

import java.sql.SQLException;
import java.util.List;

import shop.pojo.Product;
import shop.pojo.ShopCart;
import shop.pojo.User;

/**
 * 购物车操作接口
 * @author 73556
 *
 */

public interface IShopCartDao {
	//添加到购物车
	public void addTocart(ShopCart shopcart)  throws SQLException;
	//增加购物车数量
	public void increasecart(ShopCart shopcart)  throws SQLException;
	//减少购物车数量
	public void decreasecart(ShopCart shopcart)  throws SQLException;
	//删除购买该商品
	public void deletecart(ShopCart shopcart)  throws SQLException;
	//查询单个购物车商品
	public ShopCart select(ShopCart shopcart)  throws SQLException;
	//查询登录用户的购物车所有商品
	public List<ShopCart> selectAll(User user)  throws SQLException;

}
