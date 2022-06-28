package com.dena.employee;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Employee {

    protected final DateTimeFormatter dtfFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");
    protected final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();
    protected  final static String PEOPLE_REGEX = "(?<firstName>\\w+),\\s*(?<lastName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    protected final static Pattern PEOPLE_PAT = Pattern.compile(PEOPLE_REGEX);
    protected final  Matcher peopleMat;

    protected String lastName;
    protected String firstName;
    protected LocalDate dob;

    private Employee() {
        //this constructor is meant to construct a dummy employee without any field data when the code could not get any employee
        peopleMat = null;
        lastName ="N/A";
        firstName ="N/A";
        dob =null;
    }

    public Employee(String personText) {
        //User-defined constructor to PARSE out the lastName, firstName and date of Birth of each employee(subclasses that extends this superclass)
        peopleMat = Employee.PEOPLE_PAT.matcher(personText);
        if(peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtfFormatter.parse(peopleMat.group("dob")));
        }
    }

    public static  Employee createEmployee(String employeeText){
        //this factory method is meant to Return an instance of the individual subclasses(employee) as a Generic type of the Employee superclass
        //It returns each employee based on the switch construct
       Matcher peopleMat = Employee.PEOPLE_PAT.matcher(employeeText);
        if (peopleMat.find()) {
            return switch(peopleMat.group("role")){
                case "Programmer" -> new Programmer(employeeText);//this will get the entire line of the programmer details
                case "Manager" -> new Manager(employeeText);
                case "Analyst" -> new Analyst(employeeText);
                case "Tester" ->new SoftwareTester(employeeText);
                case "CEO" ->new CEO(employeeText);
                default -> new Employee() {//this is an anonymous class
                    @Override
                    public int getSalary() {
                        return 0;
                    }
                };
            };
        } else {
            return new DummyEmployee();//this is an instance of a static nested class
        }
    }

     public abstract int getSalary();
    //This pattern is called the "template method pattern" because it provides the template to be implemented by all subclasses
    //A type of design pattern

    public double getBonus(){
        return getSalary() * 0.1;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s --BONUS::%s",lastName, firstName, moneyFormat.format(getSalary()), moneyFormat.format(getBonus()));
    }

    private static final class DummyEmployee extends Employee{
        @Override
        public int getSalary() {
            return 0;
        }
    }


}













