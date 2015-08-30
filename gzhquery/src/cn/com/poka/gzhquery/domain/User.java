package cn.com.poka.gzhquery.domain;

/**
 * 用户信息
 * @author finder
 */
public class User {

	private String usercode;			  //用户代码
	private String agencyno;		      //网点号
	private String username;		      //用户姓名
	private String userpassword;		  //用户密码
	private String usertype;			  //用户类别（0：系统管理员  1：业务人员）
	private String ustatus;               //状态（0：正常  1：注销）
	public String getUsercode() {         
		return usercode;
	}
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	public String getAgencyno() {
		return agencyno;
	}
	public void setAgencyno(String agencyno) {
		this.agencyno = agencyno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUstatus() {
		return ustatus;
	}
	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}			
}
