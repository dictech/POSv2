package com.pos.org;

import java.math.BigDecimal;
import java.util.List;

public class Organization{
	private BigDecimal org_id;
	private String     org_name;
	private String     org_addrs;
	private String     org_phone;
	private String     org_email;
	private String     org_logo;
	private List<Shop> org_shops;
	
	

	public BigDecimal getOrg_id() {
		return org_id;
	}
	public void setOrg_id(BigDecimal org_id) {
		this.org_id = org_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getOrg_addrs() {
		return org_addrs;
	}
	public void setOrg_addrs(String org_addrs) {
		this.org_addrs = org_addrs;
	}
	public String getOrg_phone() {
		return org_phone;
	}
	public void setOrg_phone(String org_phone) {
		this.org_phone = org_phone;
	}
	public String getOrg_email() {
		return org_email;
	}
	public void setOrg_email(String org_email) {
		this.org_email = org_email;
	}
	public String getOrg_logo() {
		return org_logo;
	}
	public void setOrg_logo(String org_logo) {
		this.org_logo = org_logo;
	}
	
	public List<Shop> getOrg_shops() {
		return org_shops;
	}
	public void setOrg_shops(List<Shop> org_shops) {
		this.org_shops = org_shops;
	}
}
