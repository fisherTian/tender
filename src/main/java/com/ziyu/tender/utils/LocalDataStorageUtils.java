package com.ziyu.tender.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSONObject;
import com.ziyu.tender.model.Gkzb;
import com.ziyu.tender.model.Xjgg;

public class LocalDataStorageUtils {
	
	public static Map<String,JSONObject> gkzb = new ConcurrentHashMap<>(); 
	
	public static List<Gkzb> gkzbList = new ArrayList<Gkzb>(); 
	
	public static Map<String,JSONObject> xjgg = new ConcurrentHashMap<>(); 
	
	public static List<Xjgg> xjggList = new ArrayList<Xjgg>(); 
	
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
	
	public static void addGkzb(Gkzb gkzb){
		gkzbList.add(gkzb);
	}
	
	public static void clearGkzbList(){
		gkzbList.clear();
	}
	
	public static void putXjgg(String id,JSONObject jo){
		xjgg.put(id, jo);
	}
	
	public static void removeAllXjgg(){
		xjgg.clear();
	}
	
	public static List<String> getXjggIds(){
		List<String> list = new ArrayList<>();
		for (Map.Entry<String,JSONObject> entry : xjgg.entrySet()) { 
			list.add(entry.getKey());
		}
		return list;
	}
	
	public static List<String> getXjggHrefs(){
		List<String> list = new ArrayList<>();
		for (Map.Entry<String,JSONObject> entry : xjgg.entrySet()) { 
			JSONObject jo = entry.getValue();
			list.add(jo.getString("href"));
		}
		return list;
	}
	
	public static JSONObject getXjggValue(String id){
		return xjgg.get(id);
	}
	
	public static void addXjgg(Xjgg xjgg){
		xjggList.add(xjgg);
	}
	
	public static void clearXjggList(){
		xjggList.clear();
	}

}
