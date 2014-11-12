package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Employee;
import ro.z2h.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juvie on 11.11.2014.
 */
@MyController(urlPath = "/employees")
public class EmployeeController {
    @MyRequestMethod(urlPath = "/all")
    public List<Employee> getAllEmployees() {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        List<Employee> list = employeeService.findAllEmployees();
        return list;
    }

    @MyRequestMethod(urlPath = "/one")
    public Employee getOneEmployee(long id) {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        Employee employee = employeeService.findOneEmployee(100l);
        return employee;
    }
}
