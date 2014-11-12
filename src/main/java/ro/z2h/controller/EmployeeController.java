package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;

/**
 * Created by Juvie on 11.11.2014.
 */
@MyController(urlPath = "/employees")
public class EmployeeController {
    @MyRequestMethod(urlPath = "/all")
    public String getAllEmployees() {
        return "allEmployees";
    }

    @MyRequestMethod(urlPath = "/one")
    public String getOneEmployee() {
        return "oneRandomEmployee";
    }
}
