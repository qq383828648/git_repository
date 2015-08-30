package cn.com.poka.gzhquery.domain;

public class MoneyDataInfo {

	private String percode;			//清钞机具编号		
	private String coltime;	    	//记录时间
	private String mon;			    //冠字号码
	private String montype;		    //币种
	private String monval;		    //币值
	private String monver;		    //版别
	private String trueflag;		//真假（0假 1真）
	private String quanlity;		//成色
	private String imagesno;		//冠字号图片
	private String agentchid;	    //网点ID
	private String operatorid;	    //操作员ID（ATM为999999、清钞机为888888）
	private String operdate;        //日期
	
	/*显示*/
	private String atmcode;
	private String scantime;
	private String bankno;
	private String bankname;
	private String agencyno;
	private String branchname;
	private String pertype;
	private String bundleid;
	private String businesstype;
	private String imagepath;
	private String ipaddr;
	
	
	public MoneyDataInfo(){
		
	}

	public String getPercode() {
		return percode;
	}

	public void setPercode(String percode) {
		this.percode = percode;
	}

	public String getColtime() {
		return coltime;
	}

	public void setColtime(String coltime) {
		this.coltime = coltime;
	}

	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}

	public String getMontype() {
		return montype;
	}

	public void setMontype(String montype) {
		this.montype = montype;
	}

	public String getMonval() {
		return monval;
	}

	public void setMonval(String monval) {
		this.monval = monval;
	}

	public String getMonver() {
		return monver;
	}

	public void setMonver(String monver) {
		this.monver = monver;
	}

	public String getTrueflag() {
		return trueflag;
	}

	public void setTrueflag(String trueflag) {
		this.trueflag = trueflag;
	}

	public String getQuanlity() {
		return quanlity;
	}

	public void setQuanlity(String quanlity) {
		this.quanlity = quanlity;
	}

	public String getImagesno() {
		return imagesno;
	}

	public void setImagesno(String imagesno) {
		this.imagesno = imagesno;
	}

	public String getAgentchid() {
		return agentchid;
	}

	public void setAgentchid(String agentchid) {
		this.agentchid = agentchid;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getOperdate() {
		return operdate;
	}

	public void setOperdate(String operdate) {
		this.operdate = operdate;
	}

	public String getAtmcode() {
		return atmcode;
	}

	public void setAtmcode(String atmcode) {
		this.atmcode = atmcode;
	}

	public String getScantime() {
		return scantime;
	}

	public void setScantime(String scantime) {
		this.scantime = scantime;
	}

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getAgencyno() {
		return agencyno;
	}

	public void setAgencyno(String agencyno) {
		this.agencyno = agencyno;
	}

	public String getBranchname() {
		return branchname;
	}

	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	
	public String getPertype() {
		return pertype;
	}

	public void setPertype(String pertype) {
		this.pertype = pertype;
	}

	public String getBundleid() {
		return bundleid;
	}

	public void setBundleid(String bundleid) {
		this.bundleid = bundleid;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public String getImagepath() {
		return imagepath;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	
}
