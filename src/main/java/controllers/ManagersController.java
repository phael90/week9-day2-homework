package controllers;

import db.DBHelper;
import models.Department;
import models.Manager;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.redirect;

public class ManagersController {
    public ManagersController() {
        setupEndpoints();
    }

    private void setupEndpoints() {
        get("/managers", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/managers/index.vtl");
            List<Manager> managers = DBHelper.getAll(Manager.class);
            model.put("managers", managers);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/managers/:id", (req, res) ->{
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/managers/show.vtl");
            int id = Integer.parseInt(req.params("id"));
            Manager manager = DBHelper.find(id, Manager.class);
            model.put("manager", manager);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        get("/managers/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/managers/new.vtl");
            List<Department> departments = DBHelper.getAll(Department.class); //NEW
            model.put("departments", departments);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/managers", (req, res) -> {
            String firstName = req.queryParams("firstName");
            String lastName = req.queryParams("lastName");
            int salary = Integer.parseInt(req.queryParams("salary"));
            int budget = Integer.parseInt(req.queryParams("budget"));
            int departmentId = Integer.parseInt(req.queryParams("department"));
            Department department = DBHelper.find(departmentId, Department.class);
            Manager manager1 = new Manager(firstName, lastName, salary, department, budget);
            DBHelper.save(manager1);
            res.redirect("/managers");
            return null;
        });


    }
}
