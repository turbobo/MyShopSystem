package shop.pojo;
import java.util.Map;

/**
 * 订单类
 * @author 73556
 *
 */

public class OrderEntity {
	//订单编号
	private String oid;
	//用户编号
	private int uid;
	//商品总数量
	private int totalNum;
	//商品总价格
	private float totalPrice;
	//联系方式
	private String mobile;
	//联系地址
	private String address;
	//订单支付状态  已支付paid 未支付unpaid  
	private String status;
	//map集合下放的是商品 - 商品数量的键值对，得到一个订单号，即可户得所有商品和对应数量
	private Map<Product,Integer> map;
	
	public OrderEntity(){
		
	}


	public OrderEntity(String oid) {
		super();
		this.oid = oid;
	}


	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Map<Product, Integer> getMap() {
		return map;
	}
	public void setMap(Map<Product, Integer> map) {
		this.map = map;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public OrderEntity(String oid, int uid, int totalNum, float totalPrice,
			String status) {
		super();
		this.oid = oid;
		this.uid = uid;
		this.totalNum = totalNum;
		this.totalPrice = totalPrice;
		this.status = status;
	}


	public OrderEntity(String oid, int uid, int totalNum, float totalPrice,
			String status, Map<Product, Integer> map) {
		super();
		this.oid = oid;
		this.uid = uid;
		this.totalNum = totalNum;
		this.totalPrice = totalPrice;
		this.status = status;
		this.map = map;
	}



	
	
}
