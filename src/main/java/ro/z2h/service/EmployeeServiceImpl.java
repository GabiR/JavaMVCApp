package ro.z2h.service;

import ro.z2h.dao.EmployeeDao;
import ro.z2h.domain.Employee;
import ro.z2h.utils.DatabaseManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juvie on 12.11.2014.
 */
public class EmployeeServiceImpl implements EmployeeService {

    public List<Employee> findAllEmployees() {
        List<Employee> employeeList = new ArrayList<Employee>();
        EmployeeDao employeeDao = new EmployeeDao();
        Connection con = DatabaseManager.getConnection("ZTH_20", "passw0rd");
        try {
             employeeList = employeeDao.getAllEmployees(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public Employee findOneEmployee(long id) {
        EmployeeDao employeeDao = new EmployeeDao();
        Connection con = DatabaseManager.getConnection("ZTH_20", "passw0rd");
        Employee employee = null;
        try {
            employee = employeeDao.getEmployeeById(con, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

}
