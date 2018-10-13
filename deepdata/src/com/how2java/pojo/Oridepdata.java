package com.how2java.pojo;

public class Oridepdata {

	
	private int id;
	private String  bskey;
	private String  bugdes;
	private String  bugtype;
	private String  link;
	private String  line;
	private String  bugexp;
	private String  buginfo;
	private String  dkey;
	private String  filename;
	private String  otherexp;
	private String protype;
	private String  github;
	private String git_bs;
    private String deflink;
	private String  dlevel;	
	private String  realLink;
	private String  bugcor;
	private String  bugerr;
	private String datetime;
	private String bugdes_cn;
	private String bugtype_cn;
	private String defno;
	private String defdl;
	private String pronum;
	private String mdvalue;
	private String datetimeStart;
	private String datetimeEnd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBskey() {
		return bskey;
	}
	public void setBskey(String bskey) {
		this.bskey = bskey;
	}
	public String getBugdes() {
		return bugdes;
	}
	public void setBugdes(String bugdes) {
		this.bugdes = bugdes;
	}
	public String getBugtype() {
		return bugtype;
	}
	public void setBugtype(String bugtype) {
		this.bugtype = bugtype;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getBugexp() {
		return bugexp;
	}
	public void setBugexp(String bugexp) {
		this.bugexp = bugexp;
	}
	public String getBuginfo() {
		return buginfo;
	}
	public void setBuginfo(String buginfo) {
		this.buginfo = buginfo;
	}
	public String getDkey() {
		return dkey;
	}
	public void setDkey(String dkey) {
		this.dkey = dkey;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getOtherexp() {
		return otherexp;
	}
	public void setOtherexp(String otherexp) {
		this.otherexp = otherexp;
	}
	public String getProtype() {
		return protype;
	}
	public void setProtype(String protype) {
		this.protype = protype;
	}
	public String getGithub() {
		return github;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public String getGit_bs() {
		return git_bs;
	}
	public void setGit_bs(String git_bs) {
		this.git_bs = git_bs;
	}
	public String getDeflink() {
		return deflink;
	}
	public void setDeflink(String deflink) {
		this.deflink = deflink;
	}
	
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getRealLink() {
		return realLink;
	}
	public void setRealLink(String realLink) {
		this.realLink = realLink;
	}
	public String getBugcor() {
		return bugcor;
	}
	public void setBugcor(String bugcor) {
		this.bugcor = bugcor;
	}
	public String getBugerr() {
		return bugerr;
	}
	public void setBugerr(String bugerr) {
		this.bugerr = bugerr;
	}
	public String getDlevel() {
		return dlevel;
	}
	public void setDlevel(String dlevel) {
		this.dlevel = dlevel;
	}
	public String getBugdes_cn() {
		return bugdes_cn;
	}
	public void setBugdes_cn(String bugdes_cn) {
		this.bugdes_cn = bugdes_cn;
	}
	public String getBugtype_cn() {
		return bugtype_cn;
	}
	public void setBugtype_cn(String bugtype_cn) {
		this.bugtype_cn = bugtype_cn;
	}
	public String getDefno() {
		return defno;
	}
	public void setDefno(String defno) {
		this.defno = defno;
	}
	public String getDefdl() {
		return defdl;
	}
	public void setDefdl(String defdl) {
		this.defdl = defdl;
	}
	
	

	public boolean vifyColLen(Oridepdata oridepdata) {
		boolean re = vcollen(oridepdata.getBugdes(), 500)       //描述500
				&& vcollen(oridepdata.getBugtype(), 500)      //类型500
				&& vcollen(oridepdata.getLink(), 1000)         //原链接数据1000
				&& vcollen(oridepdata.getLine(), 10)         //行数10
				&& vcollen(oridepdata.getBugexp(), 2000)      //示例文字2000
				&& vcollen(oridepdata.getOtherexp(), 400)    //其他解释400
				&& vcollen(oridepdata.getBuginfo(), 5000)     //示例html5000
				&& vcollen(oridepdata.getDkey(), 200)       //关键字200
				&& vcollen(oridepdata.getFilename(), 200)
				&& vcollen(oridepdata.getBugcor(), 2000)
				&& vcollen(oridepdata.getBugerr(), 2000)
				&& vcollen(oridepdata.getMdvalue(), 32);    //处理文件名200	
		return re;
	}

	public boolean vcollen(String col,int len) {
		if(col != null && col.length() > len) {
			return false;
		}
		return true;	
	}
	public String getPronum() {
		return pronum;
	}
	public void setPronum(String pronum) {
		this.pronum = pronum;
	}
	public String getMdvalue() {
		return mdvalue;
	}
	public void setMdvalue(String mdvalue) {
		this.mdvalue = mdvalue;
	}
	public String getDatetimeStart() {
		return datetimeStart;
	}
	public void setDatetimeStart(String datetimeStart) {
		this.datetimeStart = datetimeStart;
	}
	public String getDatetimeEnd() {
		return datetimeEnd;
	}
	public void setDatetimeEnd(String datetimeEnd) {
		this.datetimeEnd = datetimeEnd;
	}
}
