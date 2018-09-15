package com.pos.org;

public class TestOrg {
	public static void main(String args[]) {
		Organization org = new Organization();
		org.setOrg_name("Shop Rite");
		org.setOrg_phone("08030754375");
		org.setOrg_logo("logo.png");
		org.setOrg_email("info@shoprite.com");
		org.setOrg_addrs("11,James Gichuru Street");
		
		OrganizationDAO.createOrg(org);
	}
}
