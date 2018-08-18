package com.ziyu.tender.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONObject;

public class LocalDataStorageUtils {
	
	public static Map<String,JSONObject> gkzb = new ConcurrentHashMap<>(); 
	
	public static void putGkzb(String id,JSONObject jo){
		gkzb.put(id, jo);
	}
	
	public static void removeAllGkzb(){
		gkzb.clear();
	}
	
	public static List<String> getGkzbIds(){
		List<String> list = new ArrayList<>();
		for (Map.Entry<String,JSONObject> entry : gkzb.entrySet()) { 
			list.add(entry.getKey());
		}
		return list;
	}
	
	public static List<String> getGkzbHrefs(){
		List<String> list = new ArrayList<>();
		for (Map.Entry<String,JSONObject> entry : gkzb.entrySet()) { 
			JSONObject jo = entry.getValue();
			list.add(jo.getString("href"));
		}
		return list;
	}
	
	public static JSONObject getGkzbValue(String id){
		return gkzb.get(id);
	}

}
