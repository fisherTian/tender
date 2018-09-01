package com.ziyu.tender.tender.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ziyu.tender.model.Gkzb;
import com.ziyu.tender.model.Xjgg;
import com.ziyu.tender.tender.mapper.RecordMapper;
import com.ziyu.tender.tender.model.Record;
import com.ziyu.tender.tender.service.RecordService;
import com.ziyu.tender.utils.LocalDataStorageUtils;

@Service
public class RecordServiceImpl implements RecordService{
	
	@Autowired
	private RecordMapper recordMapper;

	@Override
	public void batchAddRecord(String type) {
		List<String> listIds = recordMapper.loadRecordList(type);
		if(type.equals(Record.GKZB_TYPE)){
			List<String> listGkzbIds = LocalDataStorageUtils.getGkzbIds();
			listGkzbIds.removeAll(listIds);
			//添加到mysql
			List<Record> listRecord = new ArrayList<Record>();
			Date createTime = new Date();
			for(String id:listGkzbIds){
				Record record = new Record();
				record.setCreateTime(createTime);
				record.setId(id);
				record.setType(type);
				listRecord.add(record);
			}
			if(listRecord.size()>0)recordMapper.batchAddRecord(listRecord);
			//修改需要添加到es的list
			List<Gkzb> gkzbList = new ArrayList<Gkzb>(); 
			for(Gkzb gkzb:LocalDataStorageUtils.gkzbList){
				for(String id:listGkzbIds){
					if(gkzb.getId().equals(id)){
						gkzbList.add(gkzb);
					}
				}
			}
			LocalDataStorageUtils.gkzbList = gkzbList;
		}else if(type.equals(Record.XJGG_TYPE)){
			List<String> listXjggIds = LocalDataStorageUtils.getXjggIds();
			listXjggIds.removeAll(listIds);
			//添加到mysql
			List<Record> listRecord = new ArrayList<Record>();
			Date createTime = new Date();
			for(String id:listXjggIds){
				Record record = new Record();
				record.setCreateTime(createTime);
				record.setId(id);
				record.setType(type);
				listRecord.add(record);
			}
			if(listRecord.size()>0)recordMapper.batchAddRecord(listRecord);
			//修改需要添加到es的list
			List<Xjgg> xjggList = new ArrayList<Xjgg>(); 
			for(Xjgg xjgg:LocalDataStorageUtils.xjggList){
				for(String id:listXjggIds){
					if(xjgg.getId().equals(id)){
						xjggList.add(xjgg);
					}
				}
			}
			LocalDataStorageUtils.xjggList = xjggList;
		}
	}

	@Override
	public void deleteRecords() {
		recordMapper.deleteRecords();
	}

}
