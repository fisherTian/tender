package com.ziyu.tender.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.alibaba.fastjson.JSONObject;
import com.ziyu.tender.utils.LocalDataStorageUtils;

@Document(indexName = "tender",type = "gkzb")
public class Gkzb implements Serializable{

	private static final long serialVersionUID = -5486342676464419079L;

	@Id
	private String id;
	
	private String title;//采购项目名称
	
	private String pm;//品目
	
	private String ggsj;//公告时间
	
	private String cgdw;//采购单位(采购人)
	
	private String xzqy;//行政区域
	
	private String province;//省份
	
	private String hqzbwjsj;//获取招标文件时间
	
	private String zbwjsj;//招标文件售价
	
	private String hqzbwjdd;//获取招标文件的地点

	private String kbsj;//开标时间
	
	private String kbdd;//开标地点
	
	private String ysje;//预算金额
	
	
	private String xmlxr;//项目联系人
	
	private String xmlxdh;//项目联系电话
	
	private String cgdwdz;//采购单位地址
	
	private String cgdwlxfs;//采购单位联系方式
	
	private String dljgmc;//代理机构名称
	
	private String dljgdz;//代理机构地址
	
	private String dljglxfs;//代理机构联系方式
	
	private String createTime;//创建时间
	
	private String href;//链接
	
	public Gkzb(JSONObject jo){
		this.title = jo.getString("采购项目名称");
		this.pm = jo.getString("品目");
		this.ggsj = jo.getString("公告时间");
		this.cgdw = jo.getString("采购单位");
		this.xzqy = jo.getString("行政区域");
		this.hqzbwjsj = jo.getString("获取招标文件时间");
		this.zbwjsj = jo.getString("招标文件售价");
		this.hqzbwjdd = jo.getString("获取招标文件的地点");
		this.kbsj = jo.getString("开标时间");
		this.kbdd = jo.getString("开标地点");
		this.ysje = jo.getString("预算金额");
		this.xmlxr = jo.getString("项目联系人");
		this.xmlxdh = jo.getString("项目联系电话");
		this.cgdwdz = jo.getString("采购单位地址");
		this.cgdwlxfs = jo.getString("采购单位联系方式");
		this.dljgmc = jo.getString("代理机构名称");
		this.dljgdz = jo.getString("代理机构地址");
		this.dljglxfs = jo.getString("代理机构联系方式");
		JSONObject _jo = LocalDataStorageUtils.getGkzbValue(jo.getString("id"));
		if(jo.getString("id")!=null && _jo!=null){
			this.id = jo.getString("id");
			this.province = _jo.getString("province");
			this.createTime = _jo.getString("createTime");
			this.href = _jo.getString("href");
		}
	}

	public String getCgdwdz() {
		return cgdwdz;
	}



	public void setCgdwdz(String cgdwdz) {
		this.cgdwdz = cgdwdz;
	}



	public String getHref() {
		return href;
	}



	public void setHref(String href) {
		this.href = href;
	}



	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getGgsj() {
		return ggsj;
	}

	public void setGgsj(String ggsj) {
		this.ggsj = ggsj;
	}

	public String getCgdw() {
		return cgdw;
	}

	public void setCgdw(String cgdw) {
		this.cgdw = cgdw;
	}

	public String getXzqy() {
		return xzqy;
	}

	public void setXzqy(String xzqy) {
		this.xzqy = xzqy;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getHqzbwjsj() {
		return hqzbwjsj;
	}

	public void setHqzbwjsj(String hqzbwjsj) {
		this.hqzbwjsj = hqzbwjsj;
	}

	public String getZbwjsj() {
		return zbwjsj;
	}

	public void setZbwjsj(String zbwjsj) {
		this.zbwjsj = zbwjsj;
	}

	public String getHqzbwjdd() {
		return hqzbwjdd;
	}

	public void setHqzbwjdd(String hqzbwjdd) {
		this.hqzbwjdd = hqzbwjdd;
	}

	public String getKbsj() {
		return kbsj;
	}

	public void setKbsj(String kbsj) {
		this.kbsj = kbsj;
	}

	public String getKbdd() {
		return kbdd;
	}

	public void setKbdd(String kbdd) {
		this.kbdd = kbdd;
	}

	public String getYsje() {
		return ysje;
	}

	public void setYsje(String ysje) {
		this.ysje = ysje;
	}

	public String getXmlxr() {
		return xmlxr;
	}

	public void setXmlxr(String xmlxr) {
		this.xmlxr = xmlxr;
	}

	public String getXmlxdh() {
		return xmlxdh;
	}

	public void setXmlxdh(String xmlxdh) {
		this.xmlxdh = xmlxdh;
	}

	public String getCgdwlxfs() {
		return cgdwlxfs;
	}

	public void setCgdwlxfs(String cgdwlxfs) {
		this.cgdwlxfs = cgdwlxfs;
	}

	public String getDljgmc() {
		return dljgmc;
	}

	public void setDljgmc(String dljgmc) {
		this.dljgmc = dljgmc;
	}

	public String getDljgdz() {
		return dljgdz;
	}

	public void setDljgdz(String dljgdz) {
		this.dljgdz = dljgdz;
	}

	public String getDljglxfs() {
		return dljglxfs;
	}

	public void setDljglxfs(String dljglxfs) {
		this.dljglxfs = dljglxfs;
	}
	
	
	
	
	
}
