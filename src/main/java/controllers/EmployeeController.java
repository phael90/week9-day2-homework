package controllers;

import db.DBHelper;
import db.Seeds;
import models.Employee;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.get;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeController {
        public EmployeeController(){
         setupEndpoints();
        }

    private void setupEndpoints(){
        Seeds.seedData();
        // build get root for /employees

        get("/employees",  (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            List<Employee> employees = DBHelper.getAll(Employee.class);
            model.put("template", "templates/employees/index.vtl");
            model.put("employees", employees);
            return  new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

    }
}
