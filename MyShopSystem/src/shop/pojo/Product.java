package shop.pojo;
/**
 * 商品类
 * @author 73556
 *
 */

public class Product {
	//商品编号    自增
	private int id;
	//商品名称
	private String sname;
	//商品单价
	private float price;
	//商品描述     苹果
	private String sdesc;
	//商品分类     手机
	private String scategory;
	//查询价格上限
	private float upperPrice;
	//查询价格下限
	private float lowerPrice;
	
	public Product(){
		
	}

	public int getId() {
		return id;
	}

	public Product(int id) {
		super();
		this.id = id;
	}

	public Product(String sname, float price, String sdesc, String scategory) {
		super();
		this.sname = sname;
		this.price = price;
		this.sdesc = sdesc;
		this.scategory = scategory;
	}

	public Product(int id, String sname, float price, String sdesc,
			String scategory) {
		super();
		this.id = id;
		this.sname = sname;
		this.price = price;
		this.sdesc = sdesc;
		this.scategory = scategory;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}



	public String getSdesc() {
		return sdesc;
	}



	public void setSdesc(String sdesc) {
		this.sdesc = sdesc;
	}



	public String getScategory() {
		return scategory;
	}



	public void setScategory(String scategory) {
		this.scategory = scategory;
	}



	public float getLowerPrice() {
		return lowerPrice;
	}



	public void setLowerPrice(float lowerPrice) {
		this.lowerPrice = lowerPrice;
	}



	public float getUpperPrice() {
		return upperPrice;
	}



	public void setUpperPrice(float upperPrice) {
		this.upperPrice = upperPrice;
	}

}
