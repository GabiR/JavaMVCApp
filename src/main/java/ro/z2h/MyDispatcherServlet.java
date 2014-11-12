package ro.z2h;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.controller.DepartmentController;
import ro.z2h.controller.EmployeeController;
import ro.z2h.fmk.AnnotationScanUtils;
import ro.z2h.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;


/**
 * Created by Juvie on 11.11.2014.
 */

public class MyDispatcherServlet extends HttpServlet {
    HashMap<String, MethodAttributes> hashMap = new HashMap<String, MethodAttributes>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("POST", req, resp);
    }

    private void dispatchReply(String httpMethod, HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Dispatch
        Object r = dispatch(httpMethod, req, resp);
//        Reply
        reply(r, req, resp);
//        Check Errors
        Exception ex = null;
        sendErrors(ex, req, resp);
    }

    private void sendErrors(Exception ex, HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("There was an exception");
    }

//       send Response
    private void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.printf(r.toString());
    }

    /* delegate tasks*/
    private Object dispatch(String httpMethod, HttpServletRequest req, HttpServletResponse resp) {
        String pathInfo = req.getPathInfo();
//        if(pathInfo.startsWith("/employee")) {
//            EmployeeController employeeController = new EmployeeController();
//            return employeeController.getAllEmployees();
//        }
//        else
//            if (pathInfo.startsWith("/department")) {
//                DepartmentController departmentController = new DepartmentController();
//                return departmentController.getAllDepartments();
//        }
        MethodAttributes methodAttributes = hashMap.get(pathInfo);
        try {
            if(methodAttributes != null) {
                Class<?> appControllerClass = Class.forName(methodAttributes.getControllerClass());
                Object appControllerInstance = appControllerClass.newInstance();
                Method controllerMethod = appControllerClass.getMethod(methodAttributes.getMethodName());
                return controllerMethod.invoke(appControllerInstance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(hashMap);
        return "Hello";

    }

    @Override
    public void init() throws ServletException {

        try {
            Iterable<Class> classes = AnnotationScanUtils.getClasses("ro.z2h.controller");
            for (Class c : classes)
                if(c.isAnnotationPresent(MyController.class)) {
                    MyController myController = (MyController)c.getAnnotation(MyController.class);
                    System.out.println(myController.urlPath() + " ");
                    for(Method m : c.getMethods())
                        if(m.isAnnotationPresent(MyRequestMethod.class)) {
                            MyRequestMethod myRequestMethod = m.getAnnotation(MyRequestMethod.class);
                            System.out.println(myRequestMethod.urlPath() + " " + myRequestMethod.methodType());
                            MethodAttributes methodAttributes = new MethodAttributes();
                            methodAttributes.setControllerClass(c.getName());
                            methodAttributes.setMethodName(m.getName());
                            methodAttributes.setMethodType(myRequestMethod.methodType());
                            hashMap.put(myController.urlPath() + myRequestMethod.urlPath(), methodAttributes);
                            System.out.println(hashMap.get(myController.urlPath() + myRequestMethod.urlPath()));

                        }
                }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
