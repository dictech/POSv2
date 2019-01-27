package com.pos.database;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.pos.account.model.Attendant;
import com.pos.order.model.Purchase;

public class AttendantCache {
	
	public static Attendant attendant;
	public static LoadingCache<String, Attendant> cache;
	
	static {
		cache = CacheBuilder.newBuilder()
									 .maximumSize(1)
									 .expireAfterAccess(30,TimeUnit.MINUTES)
									 .build(new CacheLoader<String,Attendant>(){
										 
										public Attendant load(String attendantId) {
											return getAttendantById(attendantId);
										}
									 });
	}
	
	public static LoadingCache<String, Attendant> getCache(){
		return cache;
	}
	
	public static void setAttendant(Attendant loggedinAttendant) {
		attendant =  loggedinAttendant;
	}

	public static Attendant getAttendantById(String attendantId) {
		return attendant;
	}

}
