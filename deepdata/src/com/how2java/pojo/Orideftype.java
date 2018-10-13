package com.how2java.pojo;

public class Orideftype {
	private int id;
	private String deftype;
	private String dlevel;
	private String  datetime;
	private String  sfjc;
	private String  deftype_cn;
	private String  defno;
	private String  defdl;
	
	
	private String    URL;
	private String    SIVA;
	private String    FILE_COUNT;
	private String    LANGS;
	private String    LANGS_BYTE_COUNT;
	private String    LANGS_LINES_COUNT;
	private String    LANGS_FILE_COUNT;
	private String    COMMIT_COUNT;
	private String    BRANCHES_COUNT;
	private String    FORK_COUNT;
	private String    EMPTY_LINES_COUNT;
	private String    CODE_LINES_COUNT;
	private String    COMMENT_LINES_COUNT;
	private String    LICENSE;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getDlevel() {
		return dlevel;
	}
	public void setDlevel(String dlevel) {
		this.dlevel = dlevel;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getDeftype() {
		return deftype;
	}
	public void setDeftype(String deftype) {
		this.deftype = deftype;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSfjc() {
		return sfjc;
	}
	public void setSfjc(String sfjc) {
		this.sfjc = sfjc;
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
	public String getDeftype_cn() {
		return deftype_cn;
	}
	public void setDeftype_cn(String deftype_cn) {
		this.deftype_cn = deftype_cn;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getSIVA() {
		return SIVA;
	}
	public void setSIVA(String sIVA) {
		SIVA = sIVA;
	}
	public String getFILE_COUNT() {
		return FILE_COUNT;
	}
	public void setFILE_COUNT(String fILE_COUNT) {
		FILE_COUNT = fILE_COUNT;
	}
	public String getLANGS() {
		return LANGS;
	}
	public void setLANGS(String lANGS) {
		LANGS = lANGS;
	}
	public String getLANGS_BYTE_COUNT() {
		return LANGS_BYTE_COUNT;
	}
	public void setLANGS_BYTE_COUNT(String lANGS_BYTE_COUNT) {
		LANGS_BYTE_COUNT = lANGS_BYTE_COUNT;
	}
	public String getLANGS_LINES_COUNT() {
		return LANGS_LINES_COUNT;
	}
	public void setLANGS_LINES_COUNT(String lANGS_LINES_COUNT) {
		LANGS_LINES_COUNT = lANGS_LINES_COUNT;
	}
	public String getLANGS_FILE_COUNT() {
		return LANGS_FILE_COUNT;
	}
	public void setLANGS_FILE_COUNT(String lANGS_FILE_COUNT) {
		LANGS_FILE_COUNT = lANGS_FILE_COUNT;
	}
	public String getCOMMIT_COUNT() {
		return COMMIT_COUNT;
	}
	public void setCOMMIT_COUNT(String cOMMIT_COUNT) {
		COMMIT_COUNT = cOMMIT_COUNT;
	}
	public String getBRANCHES_COUNT() {
		return BRANCHES_COUNT;
	}
	public void setBRANCHES_COUNT(String bRANCHES_COUNT) {
		BRANCHES_COUNT = bRANCHES_COUNT;
	}
	public String getFORK_COUNT() {
		return FORK_COUNT;
	}
	public void setFORK_COUNT(String fORK_COUNT) {
		FORK_COUNT = fORK_COUNT;
	}
	public String getEMPTY_LINES_COUNT() {
		return EMPTY_LINES_COUNT;
	}
	public void setEMPTY_LINES_COUNT(String eMPTY_LINES_COUNT) {
		EMPTY_LINES_COUNT = eMPTY_LINES_COUNT;
	}
	public String getCODE_LINES_COUNT() {
		return CODE_LINES_COUNT;
	}
	public void setCODE_LINES_COUNT(String cODE_LINES_COUNT) {
		CODE_LINES_COUNT = cODE_LINES_COUNT;
	}
	public String getCOMMENT_LINES_COUNT() {
		return COMMENT_LINES_COUNT;
	}
	public void setCOMMENT_LINES_COUNT(String cOMMENT_LINES_COUNT) {
		COMMENT_LINES_COUNT = cOMMENT_LINES_COUNT;
	}
	public String getLICENSE() {
		return LICENSE;
	}
	public void setLICENSE(String lICENSE) {
		LICENSE = lICENSE;
	}
	
	
	
	public boolean vifyColLen(Orideftype orideftype){
		boolean re = vcollen(orideftype.getURL() , 200) 
					&& vcollen(orideftype.getSIVA() , 3000)
					&& vcollen(orideftype.getFILE_COUNT() , 100)
					&& vcollen(orideftype.getLANGS() , 2000)
					&& vcollen(orideftype.getLANGS_BYTE_COUNT() , 1000)
					&& vcollen(orideftype.getLANGS_LINES_COUNT() , 1000)
					&& vcollen(orideftype.getLANGS_FILE_COUNT() , 1000)
					&& vcollen(orideftype.getCOMMIT_COUNT() , 1000)
					&& vcollen(orideftype.getBRANCHES_COUNT() , 1000)
					&& vcollen(orideftype.getFORK_COUNT() , 1000)
					&& vcollen(orideftype.getEMPTY_LINES_COUNT() , 1000)
					&& vcollen(orideftype.getCODE_LINES_COUNT() , 1000)
					&& vcollen(orideftype.getCOMMENT_LINES_COUNT() , 1000)
					&& vcollen(orideftype.getLICENSE() , 1000);
		return re;
	}	
	
	public boolean vcollen(String col,int len) {
		if(col != null && col.length() > len) {
			return false;
		}
		return true;	
	}
	
}
