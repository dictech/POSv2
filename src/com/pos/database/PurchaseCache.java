package com.pos.database;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.pos.order.model.Purchase;

public class PurchaseCache {
	
	public static Purchase purchase;
	public static LoadingCache<String, Purchase> cache;
	
	static {
             cache = CacheBuilder.newBuilder()
							  			.maximumSize(50)
							  			.expireAfterAccess(30, TimeUnit.MINUTES)
							            .build(new CacheLoader<String, Purchase>(){
							            	  
							            	  public Purchase load(String purchaseId) {
							            		  return getPurchaseById(purchaseId);
							            	  }
							              });
	    }
	
	
	public static LoadingCache<String, Purchase> getCache(){
		return cache;
	}
	

	public static void setPurchase(Purchase selectedPurchase) {
		purchase = selectedPurchase;
	}
	
	public static Purchase getPurchaseById(String purchaseId) {
		return purchase;
	}
	

}
