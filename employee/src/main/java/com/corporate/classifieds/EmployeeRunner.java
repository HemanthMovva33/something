//package com.corporate.classifieds;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.corporate.classifieds.entity.Employee;
//import com.corporate.classifieds.repository.EmployeeRepository;
//
//@Component
//public class EmployeeRunner implements CommandLineRunner {
//	@Autowired
//	EmployeeRepository empRepo;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Employee emp = new Employee();
//		emp.setEmployeeid(120);
//		emp.setFirstname("Nikhil");
//		emp.setLastname("Manjunath");
//		emp.setEmail("nik@gmail.com");
//		empRepo.save(emp);
//		
//		Employee emp1 = new Employee();
//		emp1.setEmployeeid(121);
//		emp1.setFirstname("Natasha");
//		emp1.setLastname("Nanda");
//		emp1.setEmail("natty@gmail.com");
//		empRepo.save(emp1);
//		
//	}
//
//}
