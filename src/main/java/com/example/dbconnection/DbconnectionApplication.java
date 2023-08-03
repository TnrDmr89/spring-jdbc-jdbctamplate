package com.example.dbconnection;

import com.example.dbconnection.dao.EmployeeRepository;
import com.example.dbconnection.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbconnectionApplication {


	private static EmployeeRepository employeeDao;

	public DbconnectionApplication(EmployeeRepository employeeDao) {
		this.employeeDao = employeeDao;
	}

	public static void main(String[] args) {

		SpringApplication.run(DbconnectionApplication.class, args);

	/*	System.out.println("\nOne Course -------------------------------------\n");
		List<Course> courses = dao.list();
		courses.forEach(System.out::println);


	 */
		//Course springVue = new Course(7,"Spring Boot + Vue","New Course","http://www.danvega.dev/courses");
		//dao.create(springVue);

		//dao.delete("S");

		System.out.println("\nAll Employee -------------------------------------\n");
		employeeDao.getAllEmployee().forEach(employee -> {
			System.out.println(employee.getEmployeeId() + " " + employee.getName() + " "
					+ employee.getAge() + " " + employee.getAddress());
		});

		System.out.println("\nOne Employee -------------------------------------\n");
		Employee employee = employeeDao.getEmployee(7).get();
		System.out.println(employee.getEmployeeId() + " " + employee.getName() + " "
				+ employee.getAge() + " " + employee.getAddress());

       /*
		System.out.println("\nSave Employee -------------------------------------\n");
        Employee employee1 = new Employee(8,"Hasan",40,"KLJ");
		employeeDao.save(employee1);
       */

		/*
		System.out.println("\nUpdate Employee -------------------------------------\n");
		employeeDao.update(8,new Employee("Caner",44,"STL"));
		 */

		/*
		System.out.println("\nDelete Employee -------------------------------------\n");
		employeeDao.delete("C");
		 */

        /*
		System.out.println("\nMore than one employee save -------------------------------------\n");
		employeeDao.saveListEmployee(Arrays.asList(new Employee(9,"Kerim",31,"hhh")
				,new Employee(10,"Burcu",24,"uuu")
		        ,new Employee(11,"Tarık",37,"lll")
				));

       */

		System.out.println("\nGrater age than Employee -------------------------------------\n");
		employeeDao.getGraterThanAge(30).forEach(s -> {
			System.out.println(s.getName() + " " + s.getAge());
		});

		System.out.println("\nNamed Employee than Employee -------------------------------------\n");
		employeeDao.getNameWith("Taner").forEach(s -> {
			System.out.println(s.getName() + " " + s.getAge());
		});

		System.out.println("\nNamed and grater than age Employee than Employee -------------------------------------\n");
		employeeDao.getSameNameAndAge("Ayşegül",25).forEach(s -> {
			System.out.println(s.getName() + " " + s.getAge());
		});



	}

}
