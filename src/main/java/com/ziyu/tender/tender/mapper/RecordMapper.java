package com.ziyu.tender.tender.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ziyu.tender.tender.model.Record;

public interface RecordMapper {
	
	void batchAddRecord(List<Record> list);
	
	List<String> loadRecordList(@Param("type") String type);
	
    int insert(Record record);

    int insertSelective(Record record);
}