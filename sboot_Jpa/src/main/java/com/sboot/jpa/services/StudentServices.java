package com.sboot.jpa.services;

import java.util.Scanner;



import com.sboot.jpa.model.Student;

public class StudentServices {

	
	public static Student insertdata(Student stu) {
		Scanner sd = new Scanner(System.in);
		System.out.println("Enter student name : ");
		String sname = sd.nextLine();
		stu.setSname(sname);
		System.out.println("Enter student city : ");
		String scity = sd.nextLine();
		stu.setCity(scity);
		return stu;
	}

	public static int getonedata() {
		Scanner sd = new Scanner(System.in);
		System.out.println("Enter student Id: ");
		int sid = sd.nextInt();
		return sid;
	}

	public static Student getupdatedata(Student stu) {
		Scanner sd = new Scanner(System.in);
		System.out.println("Enter student Id : ");
		int suid = sd.nextInt();
		stu.setSid(suid);
		System.out.println("Enter student name : ");
		String sname = sd.next();
		stu.setSname(sname);
		System.out.println("Enter student city : ");
		String scity = sd.next();
		stu.setCity(scity);
		return stu;
	}

	public static int deletedata() {
		Scanner sd = new Scanner(System.in);
		System.out.println("Enter student Id: ");
		int sid = sd.nextInt();
		return sid;
	}

	public static String getonedatabyname() {
		Scanner sd = new Scanner(System.in);
		System.out.println("Enter student Name: ");
		String sname = sd.next();
		return sname;
	}

	public static Student getmumbaistudent(Student stu) {
		Scanner sd = new Scanner(System.in);
		System.out.println("Enter student Name: ");
		String sname = sd.next();
		stu.setSname(sname);
		System.out.println("Enter student city : ");
		String scity = sd.next();
		stu.setCity(scity);
		return stu;
	}
}
