package controllers;

import db.Seeds;

public class MainController {
    public static void main(String[] args) {
        Seeds.seedData();
        ManagersController managersController = new ManagersController();
        EmployeeController employeeController = new EmployeeController();
    }
}
