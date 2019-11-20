package shop.pojo;
/**
 * 管理员
 * @author 73556
 *
 */

public class Admini {
	//编号
	private int id;
	//姓名
	private String sname;
	//密码
	private String spassword;
	//描述
	private String sdesc;
	
	public Admini(){
		
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

	public String getSdesc() {
		return sdesc;
	}

	public void setSdesc(String sdesc) {
		this.sdesc = sdesc;
	}



	public Admini(String sname, String spassword, String sdesc) {
		super();
		this.sname = sname;
		this.spassword = spassword;
		this.sdesc = sdesc;
	}
	
	
}
