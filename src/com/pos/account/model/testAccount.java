package com.pos.account.model;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class testAccount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Attendant att = new Attendant();
        att.setfName("Stanley");
        att.setSurname("Okafor");
        att.setAddress("Iyadunni st");
        att.setDob(LocalDate.parse("2017-08-16"));
        Clock c = Clock.systemDefaultZone();
        System.out.println(LocalTime.now(c));
        
        AttendantDAO.createNewAttendant(att);
	}

}
