package shop.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户类
 * @author 73556
 *
 */

public class User {
	//用户编号  自增
	private int id;
	//用户名
	private String sname;
	//用户密码
	private String spassword;
	//用户性别
	private String ssex;
	//联系方式
	private long mobile;
	//联系地址
	private String saddress;
	//该用户所有订单
	private List<OrderEntity> orders=new ArrayList<OrderEntity>();

	//返回该用户的所有订单
	public List<OrderEntity> getOrders() {
		return orders;
	}
	//添加订单订单
	public void AddOrder(OrderEntity orderentity) {
		orders.add(orderentity);
	}
	
	
	public User(){
		
	}
	
	public int getId() {
		return id;
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
	public String getSpassword() {
		return spassword;
	}
	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}



	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public User(int id, String sname) {
		super();
		this.id = id;
		this.sname = sname;
	}

	public User(String sname, String spassword, String ssex, long mobile) {
		super();
		this.sname = sname;
		this.spassword = spassword;
		this.ssex = ssex;
		this.mobile = mobile;
	}

	public User(int id, String sname, String spassword, String ssex, long mobile) {
		super();
		this.id = id;
		this.sname = sname;
		this.spassword = spassword;
		this.ssex = ssex;
		this.mobile = mobile;
	}


	
	

}
