package com.pos.org;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.pos.account.Attendant;

public class Shop {
	private BigDecimal shopId;
	private String shopName;
	private String shopLocation;
	private String shopBranch;
	private String shopRCNo;
	private String shopMgr;
	private ArrayList<Attendant> shopAttendant;
	
	public void open() {}
	public void close() {}
	
	public BigDecimal getShopId() {
		return shopId;
	}
	public void setShopId(BigDecimal shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopLocation() {
		return shopLocation;
	}
	public void setShopLocation(String shopLocation) {
		this.shopLocation = shopLocation;
	}
	public String getShopBranch() {
		return shopBranch;
	}
	public void setShopBranch(String shopBranch) {
		this.shopBranch = shopBranch;
	}
	public String getShopRCNo() {
		return shopRCNo;
	}
	public void setShopRCNo(String shopRCNo) {
		this.shopRCNo = shopRCNo;
	}
	public String getShopMgr() {
		return shopMgr;
	}
	public void setShopMgr(String shopMgr) {
		this.shopMgr = shopMgr;
	}
	public ArrayList<Attendant> getShopAttendant() {
		return shopAttendant;
	}
	public void setShopAttendant(ArrayList<Attendant> shopAttendant) {
		this.shopAttendant = shopAttendant;
	}
	
}
