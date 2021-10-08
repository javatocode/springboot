package com.sboot.jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.sboot.jpa.dao.StudentRepository;
import com.sboot.jpa.model.Student;
import com.sboot.jpa.services.StudentServices;

@SpringBootApplication
public class SbootJpaApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SbootJpaApplication.class, args);
		StudentRepository studentRepository = context.getBean(StudentRepository.class);
		int c1;
		do {
			System.out.println("Enter your choice : 1. Insert 2.update  3.show all record  4.show one record 5.delete 6.deleteall 7.get by specific details");
			Scanner sc = new Scanner(System.in);
			int ch = sc.nextInt();
			Student stu = new Student();
			switch (ch) {
			case 1:
				
				Student insertstu = StudentServices.insertdata(stu);
				studentRepository.save(insertstu);
				System.out.println("Data is insert successfully");
				break;
				
			case 2:
				Student stud= StudentServices.getupdatedata(stu);
				int suid = stud.getSid();
				Optional<Student> su = studentRepository.findById(suid);
				Student s = su.get();
				s.setSname(stud.getSname());
				s.setCity(stud.getCity());
				Student res = studentRepository.save(s);
				System.out.println("Student Id : " + res.getSid());
				System.out.println("Student Name : " + res.getSname());
				System.out.println("Student City : " + res.getCity());
				break;
				
			case 3:
				
				Iterable<Student> si= studentRepository.findAll();
				si.forEach(studs->{
					System.out.println("Student Id : " + studs.getSid());
					System.out.println("Student Name : " + studs.getSname());
					System.out.println("Student City : " + studs.getCity());
					System.out.println("++++++++++++++++++++++++++++++++++++");
				});
				break;
				
			case 4:
				
				int stu_id = StudentServices.getonedata();
				Optional<Student> so = studentRepository.findById(stu_id);
				System.out.println("Student Id : " + so.get().getSid());
				System.out.println("Student Name : " + so.get().getSname());
				System.out.println("Student City : " + so.get().getCity());
				break;
				
			case 5:
				
				int stud_id = StudentServices.deletedata();
				studentRepository.deleteById(stud_id);
				System.out.println("Id " + stud_id  +  " is deleted successfully.");
				break;
				
			case 6:
				Iterable<Student> sf= studentRepository.findAll();
				studentRepository.deleteAll(sf);
				System.out.println("All Data is deleted successfully");
				break;
				
			case 7:
				String stu_name = StudentServices.getonedatabyname();
				List<Student> st = studentRepository.findBySname(stu_name);
				for (Student sm : st) {
					 System.out.println("Student Id : " + sm.getSid());
					 System.out.println("Student Name : " + sm.getSname());
					 System.out.println("Student City : " + sm.getCity());
					 
				}
				break;
				
			case 8:
				Student stm= StudentServices.getmumbaistudent(stu);
				String sname = stm.getSname();
				String scity = stm.getCity();
				List<Student> sy = studentRepository.findBySnameAndCity(sname, scity);
				for (Student sm : sy) {
					 System.out.println("Student Id : " + sm.getSid());
					 System.out.println("Student Name : " + sm.getSname());
					 System.out.println("Student City : " + sm.getCity());
					 
				}
				break;
				
			case 9:
				List<Student> allstudent = studentRepository.getAllStudent();
				allstudent.forEach(e -> {
					System.out.println("Student Id : " + e.getSid());
					System.out.println("Student Name : " + e.getSname());
					System.out.println("Student City : " + e.getCity());
				});
				break;
				
			case 10:
				List<Student> studentname = studentRepository.getStudentNaame("Ajay");
				studentname.forEach(e -> {
					System.out.println("Student Id : " + e.getSid());
					System.out.println("Student Name : " + e.getSname());
					System.out.println("Student City : " + e.getCity());
				});
				break;
			case 11:
				List<Student> getallstudent = studentRepository.getStudents();
				getallstudent.forEach(e -> {
					System.out.println("Student Id : " + e.getSid());
					System.out.println("Student Name : " + e.getSname());
					System.out.println("Student City : " + e.getCity());
				});
				break;

			default:
				System.out.println("You have enter wrong choice");
			}
			System.out.println("Do you want to continue (y/n)");
			Scanner ss = new Scanner(System.in);
			c1 = ss.nextInt();
		} while (c1 == 1);
	}
}
