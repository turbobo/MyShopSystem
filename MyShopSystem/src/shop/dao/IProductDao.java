package shop.dao;

import java.sql.SQLException;
import java.util.List;
import shop.pojo.Product;
/*
 *	商品操作接口
 */

public interface IProductDao {
	//添加商品
	public void add(Product product) throws SQLException;
	//删除商品
	public void delete(Product product) throws SQLException;
	//修改商品
	public void update(Product product) throws SQLException;
	//查询单个商品
	public Product select(Product p) throws SQLException;
	//查询所有商品
	public List<Product> selectAll() throws SQLException;
	//根据条件查询商品
	public List<Product> selectByParaProds(Product product) throws SQLException;

}
