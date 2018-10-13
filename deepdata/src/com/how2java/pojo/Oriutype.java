package com.how2java.pojo;

public class Oriutype {
	private int id;
	private String link;
	private String deftype;
	private String  levelinfo;
	private String  dlevel;
	private String  protype;
	private String  datetime;
	private String  datetimestart;
	private String  datetimeend;
	private String  github;
	private String sfsc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDeftype() {
		return deftype;
	}
	public void setDeftype(String deftype) {
		this.deftype = deftype;
	}
	public String getLevelinfo() {
		return levelinfo;
	}
	public void setLevelinfo(String levelinfo) {
		this.levelinfo = levelinfo;
	}
	
	public String getProtype() {
		return protype;
	}
	public void setProtype(String protype) {
		this.protype = protype;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	public String getGithub() {
		return github;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public String getSfsc() {
		return sfsc;
	}
	public void setSfsc(String sfsc) {
		this.sfsc = sfsc;
	}
	public String getDatetimestart() {
		return datetimestart;
	}
	public void setDatetimestart(String datetimestart) {
		this.datetimestart = datetimestart;
	}
	public String getDatetimeend() {
		return datetimeend;
	}
	public void setDatetimeend(String datetimeend) {
		this.datetimeend = datetimeend;
	}
	public String getDlevel() {
		return dlevel;
	}
	public void setDlevel(String dlevel) {
		this.dlevel = dlevel;
	}
	
	/*
	 * 校验数据长度
	 */
	public boolean vifyColLen(Oriutype Oriutype){
		boolean re = vcollen(String.valueOf(Oriutype.getId()) , 10) 
				  && vcollen( Oriutype.getLink(), 1000) 
				  && vcollen( Oriutype.getLevelinfo(), 2000) 
				  && vcollen( Oriutype.getDlevel(), 1) 
				  && vcollen( Oriutype.getProtype(), 10) 
				  && vcollen( Oriutype.getGithub(), 200) 
				  && vcollen( Oriutype.getSfsc(), 1);
		return re;
	}	
	
	public boolean vcollen(String col,int len) {
		if(col != null && col.length() > len) {
			return false;
		}
		return true;	
	}
	
}
