package com.ziyu.tender.tender.model;

import java.util.Date;

public class Record {
	
	public static final String GKZB_TYPE = "01";
	public static final String XJGG_TYPE = "02";
	
    private String id;

    private Date createTime;
    
    private String type;
    

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}