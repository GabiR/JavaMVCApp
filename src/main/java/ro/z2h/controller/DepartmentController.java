package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Department;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Juvie on 11.11.2014.
 */
@MyController(urlPath = "/department")
public class DepartmentController {
    @MyRequestMethod(urlPath = "/all")
    public List<Department> getAllDepartments (){
        List<Department> departmentList = new LinkedList<Department>();
        Department department = new Department();
        department.setId(222l);
        Department department1 = new Department();
        department1.setId(22l);
        departmentList.add(department);
        departmentList.add(department1);
        return departmentList;
    }
}
