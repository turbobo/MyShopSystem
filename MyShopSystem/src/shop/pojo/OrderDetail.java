package shop.pojo;
/**
 * 商品详情 类
 * @author 73556
 *
 */

public class OrderDetail {
	//订单详情编号
	private long orderdetail_id;
	//订单号
	private String oid;
	//商品名称
	private String pname;
	//商品单价
	private float price;
	//商品描述     
	private String sdesc;
	//商品分类     手机
	private String scategory;
	//商品数量
	private int buyamount;
	
	public OrderDetail(){
		
	}

	public long getOrderdetail_id() {
		return orderdetail_id;
	}

	public void setOrderdetail_id(long orderdetail_id) {
		this.orderdetail_id = orderdetail_id;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
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

	public int getBuyamount() {
		return buyamount;
	}

	public void setBuyamount(int buyamount) {
		this.buyamount = buyamount;
	}

	public OrderDetail(String oid, String pname, float price, String sdesc,
			String scategory, int buyamount) {
		super();
		this.oid = oid;
		this.pname = pname;
		this.price = price;
		this.sdesc = sdesc;
		this.scategory = scategory;
		this.buyamount = buyamount;
	}
	
}
