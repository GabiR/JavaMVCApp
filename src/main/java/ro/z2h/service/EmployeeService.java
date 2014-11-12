package ro.z2h.service;

import ro.z2h.domain.Employee;

import java.util.List;

/**
 * Created by Juvie on 12.11.2014.
 */
public interface EmployeeService {
    List<Employee> findAllEmployees();
    Employee findOneEmployee(long id);
}

