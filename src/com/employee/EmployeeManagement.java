package com.employee;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagement {

    static Scanner sc = new Scanner(System.in);

    static final String FILE_NAME = "employees.dat";

    static ArrayList<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {

        loadEmployees();

        while (true) {

            System.out.println("Main Menu");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addEmployee();
                    break;

                case 2:
                    displayAll();
                    break;

                case 3:
                    saveEmployees();
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // Add Employee
    public static void addEmployee() {

        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Employee Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Employee Salary: ");
        double salary = sc.nextDouble();

        Employee emp = new Employee(id, name, age, salary);

        employees.add(emp);

        saveEmployees();
    }

    // Display All Employees
    public static void displayAll() {

        System.out.println("----Report-----");

        for (Employee emp : employees) {
            System.out.println(emp);
        }

        System.out.println("----End of Report----");
    }

    // Save employees into file
    public static void saveEmployees() {

        try {

            FileOutputStream fos =
                    new FileOutputStream(FILE_NAME);

            ObjectOutputStream oos =
                    new ObjectOutputStream(fos);

            oos.writeObject(employees);

            oos.close();
            fos.close();

        }
        catch (IOException e) {
            System.out.println("Error while saving employee data");
        }
    }

    // Load employees from file
    @SuppressWarnings("unchecked")
    public static void loadEmployees() {

        try {

            FileInputStream fis =
                    new FileInputStream(FILE_NAME);

            ObjectInputStream ois =
                    new ObjectInputStream(fis);

            employees =
                    (ArrayList<Employee>) ois.readObject();

            ois.close();
            fis.close();

        }
        catch (Exception e) {

            employees = new ArrayList<>();
        }
    }
}