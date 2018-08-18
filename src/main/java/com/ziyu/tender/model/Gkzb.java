package com.ziyu.tender.model;

public class Gkzb {

	private String id;
	
	private String title;
	
	private String time;
	
	private String cgr;
	
	private String dljg;
	
	private String province;
	
	private String type;
	
	public Gkzb(String id,String title,String time,String cgr,String dljg,String province,String type){
		this.id = id;
		this.title = title;
		this.time = time;
		this.cgr = cgr;
		this.dljg = dljg;
		this.province = province;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCgr() {
		return cgr;
	}

	public void setCgr(String cgr) {
		this.cgr = cgr;
	}

	public String getDljg() {
		return dljg;
	}

	public void setDljg(String dljg) {
		this.dljg = dljg;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
