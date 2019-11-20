package shop.pojo;

import java.sql.SQLException;

import shop.dao.ProductDao;

/**
 *  购物车类：商品信息、数量、金额
 * @author 73556
 *
 */

public class ShopCart {
	//购物车编号   主键 无意义  自增
	private int cart_id;
	//用户id
	private int uid;
	//商品对象
//	private Product buyp;	
	//商品id
	private int pid;
	//商品单价
	private float price;
//	//购买商品数量
	private int buyamount;
	//商品总金额
	private float totalPrice;
	
	public ShopCart(){
		
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getBuyamount() {
		return buyamount;
	}

	public void setBuyamount(int buyamount) {
		this.buyamount = buyamount;
	}

	public float getTotalPrice() {
		return totalPrice;
	}
	//直接计算总价
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ShopCart(int uid, int pid, float price, int buyamount) {
		super();
		this.uid = uid;
		this.pid = pid;
		this.price = price;
		this.buyamount = buyamount;
	}
	
	
	
	public ShopCart(int cart_id) {
		super();
		this.cart_id = cart_id;
	}

	public ShopCart(int uid, int pid, float price, int buyamount,
			float totalPrice) {
		super();
		this.uid = uid;
		this.pid = pid;
		this.price = price;
		this.buyamount = buyamount;
		this.totalPrice = totalPrice;
	}

	public ShopCart(int cart_id, int uid, int pid, float price, int buyamount,
			float totalPrice) {
		super();
		this.cart_id = cart_id;
		this.uid = uid;
		this.pid = pid;
		this.price = price;
		this.buyamount = buyamount;
		this.totalPrice = totalPrice;
	}

	//根据用户和商品id判断是同一个购物车
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShopCart other = (ShopCart) obj;
		if (pid != other.pid)
			return false;
		if (uid != other.uid)
			return false;
		return true;
	}

	//直接提供get方法,EL表达式即可获得对应值
	public String getPname() throws SQLException {
		return new ProductDao().select(new Product(this.pid)).getSname();
	} 
	public String getPdesc() throws SQLException {
		return new ProductDao().select(new Product(this.pid)).getSdesc();
	}
	public String getPcategory() throws SQLException {
		return new ProductDao().select(new Product(this.pid)).getScategory();
	}
}
