package com.jpworks.datajdbc.employee;

import com.jpworks.datajdbc.employee.function.EmployeeRepository;
import com.jpworks.datajdbc.employee.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class RepositoryTests {

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	void createDeleteEmployee() {

		long currentRecords = employeeRepository.count();
		log.info("Current number of employee records: {}", currentRecords);

		Address address1 = Address.builder()
				.fullAddress("405 Wisconsin Ave Lynn Haven, Florida(FL), 32444")
				.fromDate(LocalDate.of(2020, 2, 1))
				.toDate(LocalDate.of(2020, 4, 30))
				.build();

		Address address2 = Address.builder()
				.fullAddress("6001 Farm To Market Rd, Cattaraugus, New York(NY), 14719")
				.fromDate(LocalDate.of(2020, 5, 1))
				.toDate(null)
				.build();
		HashMap<LocalDate, Address> addressMap = new HashMap<>();
		addressMap.put(address1.getFromDate(), address1);
		addressMap.put(address2.getFromDate(), address2);

		Salary salary1 = Salary.builder()
				.fromDate(LocalDate.of(2020, 1, 1))
				.toDate(LocalDate.of(2020, 4, 30))
				.wage(150000)
				.build();

		Salary salary2 = Salary.builder()
				.fromDate(LocalDate.of(2020, 5, 1))
				.wage(160000)
				.build();

		HashMap<LocalDate, Salary> salaryMap = new HashMap<>();
		salaryMap.put(salary1.getFromDate(), salary1);
		salaryMap.put(salary2.getFromDate(), salary2);

		Employee employee = new Employee();
		employee.setFirstName("Arjun");
		employee.setLastName("Mahoney");
		employee.setBirthDate(LocalDate.of(1996,9,1));
		employee.setHireDate(LocalDate.of(1970,1,1));
		employee.setGender(Gender.M);
		employee.setAddresses(addressMap);
		employee.setSalaries(salaryMap);
		employee.setStatus(EmployeeStatus.A);

		//Create
		employeeRepository.save(employee);
		long newRecords = employeeRepository.count();
		assertThat(newRecords).isEqualTo(currentRecords + 1);
		log.info("Current number of employee records: {}", newRecords);
		Long currentId = employee.getId();
		log.info("Id for new record: {}", currentId);
		assertThat(currentId).isNotNull();

		//Update
		employee.setPhone("2222");
		employeeRepository.save(employee);
		log.info("Id for the updated record: {}", employee.getId());
		assertThat(employee.getId()).isEqualTo(currentId);

		//Delete
		employeeRepository.delete(employee);
		long recordsAfterDelete = employeeRepository.count();
		assertThat(recordsAfterDelete).isEqualTo(currentRecords);
		log.info("Current number of employee records: {}", recordsAfterDelete);
	}

	void optimisticLocking() {

		//Set the ID row for an existing testing record, 2 threads will try to update the same record
		Long testingId = 1L;

		Exception exception = assertThrows(org.springframework.data.relational.core.conversion.DbActionExecutionException.class, () -> {
			employeeRepository.findById(testingId).ifPresent( employeeFirstThread -> {
				employeeFirstThread.setPhone("222");
				employeeRepository.findById(testingId).ifPresent( employeeSecondThread -> {
					employeeSecondThread.setPhone("333");
					employeeRepository.save(employeeSecondThread);
					employeeRepository.save(employeeFirstThread);
				});
			});
		});
		log.info("Exception message: {}", exception.getMessage());
		assertTrue(exception.getMessage().contains("Failed to execute DbAction.UpdateRoot"));
	}

	@Test
	void findByLastName(){
		Employee employee1 = new Employee();
		employee1.setFirstName("Jhon 1");
		employee1.setLastName("MyFakeLastName");
		employee1.setBirthDate(LocalDate.now());
		employee1.setHireDate(LocalDate.now());
		employee1.setGender(Gender.M);
		employee1.setStatus(EmployeeStatus.I);
		employeeRepository.save(employee1);

		Employee employee2 = new Employee();
		employee2.setFirstName("Jhon 2");
		employee2.setLastName("MyFakeLastName");
		employee2.setBirthDate(LocalDate.now());
		employee2.setHireDate(LocalDate.now());
		employee2.setGender(Gender.M);
		employee2.setStatus(EmployeeStatus.I);
		employeeRepository.save(employee2);

		List<Employee> listEmployee = employeeRepository.findByLastName("MyFakeLastName");
		log.info("Employee's found: {}", listEmployee);
		assertThat(listEmployee.size()).isEqualTo(2);

		employeeRepository.delete(employee1);
		employeeRepository.delete(employee2);
	}
}
