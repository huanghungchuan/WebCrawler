package com.how2java.pojo;

public class Origithub {
	
	private int id;//��������
	private String github;//��Ŀ��
	private String protype;//����
	private String fnamexls;
	private String datatime;//����
	private String datetimestart;//��ʼ����
	private String datetimeend;//��������
	private String getdata;//�Ƿ��ѻ�ȡ����
	private String gitdepurl;
	private String flag;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getGithub() {
		return github;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public String getProtype() {
		return protype;
	}
	public void setProtype(String protype) {
		this.protype = protype;
	}
	public String getDatatime() {
		return datatime;
	}
	public void setDatatime(String datatime) {
		this.datatime = datatime;
	}
	public String getFnamexls() {
		return fnamexls;
	}
	public void setFnamexls(String fnamexls) {
		this.fnamexls = fnamexls;
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
	public String getGetdata() {
		return getdata;
	}
	public void setGetdata(String getdata) {
		this.getdata = getdata;
	}
	public String getGitdepurl() {
		return gitdepurl;
	}
	public void setGitdepurl(String gitdepurl) {
		this.gitdepurl = gitdepurl;
	}
	/*
	 * У�����ݳ���
	 */
	public boolean vifyColLen(Origithub origithub){
		boolean re = vcollen( String.valueOf(origithub.getId()), 10)
				  && vcollen(origithub.getGithub()  , 200)
				  && vcollen(origithub.getProtype() , 10)
				  && vcollen(origithub.getGetdata() , 1)
				  && vcollen(origithub.getGitdepurl() , 400);
		return re;
	}	
	
	public boolean vcollen(String col,int len) {
		if(col != null && col.length() > len) {
			return false;
		}
		return true;	
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
