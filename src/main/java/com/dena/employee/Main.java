package com.dena.employee;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String peopleText = """
                Flinstone, Fred, 1/1/1900, Programmer, {locpd=2000,yoe=10,iq=140}
                Flinstone2, Fred2, 1/1/1900, Programmer, {locpd=1300,yoe=14,iq=100}
                Flinstone3, Fred3, 1/1/1900, Programmer, {locpd=2300,yoe=8,iq=105}
                Flinstone4, Fred4, 1/1/1900, Programmer, {locpd=1630,yoe=3,iq=115}
                Flinstone5, Fred5, 1/1/1900, Programmer, {locpd=5,yoe=10,iq=100}
                Rubble, Barney, 2/2/1905, Manager, {orgSize=300,dr=10}
                Rubble2, Barney2, 2/2/1905, Manager, {orgSize=100,dr=4}
                Rubble3, Barney3, 2/2/1905, Manager, {orgSize=200,dr=2}
                Rubble4, Barney4, 2/2/1905, Manager, {orgSize=500,dr=8}
                Rubble5, Barney5, 2/2/1905, Manager, {orgSize=175,dr=20}
                Flinstone, Wilma, 3/3/1910, Analyst, {projectCount=3}
                Flinstone2, Wilma2, 3/3/1910, Analyst, {projectCount=4}
                Flinstone3, Wilma3, 3/3/1910, Analyst, {projectCount=5}
                Flinstone4, Wilma4, 3/3/1910, Analyst, {projectCount=6}
                Flinstone5, Wilma5, 3/3/1910, Analyst, {projectCount=9}
                Rubble, Betty, 4/4/1925, CEO, {avgStockPrice=300}
                 """;
//        String peopleRegex = "(?<firstName>\\w+),\\s*(?<lastName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
        String peopleRegex = "(?<firstName>\\w+),\\s*(?<lastName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+),\\s*\\{(?<details>.*)\\}\\n";
        Pattern peoplePat = Pattern.compile(peopleRegex);
        Matcher peopleMat = peoplePat.matcher(peopleText);

        double totalProgrammerSalary = 0;
        double totalManagerSalary = 0;
        double totalAnalystSalary = 0;
        double totalCeoSalary = 0;
        int totalSalaries = 0;
        while (peopleMat.find()) {
            totalSalaries += switch(peopleMat.group("role")){
                case "Programmer" -> {
                    Programmer programmer = new Programmer(peopleMat.group());
                    System.out.println(programmer);
                   totalProgrammerSalary += programmer.getSalary();
                    yield programmer.getSalary();
                }
                case "Manager" -> {
                    Manager manager = new Manager(peopleMat.group());
                    System.out.println(manager);
                    totalManagerSalary += manager.getSalary();
                    yield manager.getSalary();
                }
                case "Analyst" -> {
                    Analyst analyst = new Analyst(peopleMat.group());
                    System.out.println(analyst);
                    totalAnalystSalary += analyst.getSalary();
                    yield analyst.getSalary();
                }
                case "CEO" ->{
                    CEO ceo = new CEO(peopleMat.group());
                    System.out.println(ceo);
                    totalCeoSalary += ceo.getSalary();
                    yield ceo.getSalary();
                }
                default -> 0;
            };
        }

        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        DecimalFormat moneyDf = new DecimalFormat("$#,###.00");

        System.out.printf("Total Programmers Salary = %s%n", currencyInstance.format(totalProgrammerSalary));
        System.out.printf("Total Manager's Salary = %s%n", currencyInstance.format(totalManagerSalary));
        System.out.printf("Total Analyst's Salary = %s%n", moneyDf.format(totalAnalystSalary));
        System.out.printf("Total CEO's Salary = %s%n", moneyDf.format(totalCeoSalary));
        System.out.printf("The total Salary Payout should be %s%n", currencyInstance.format(totalSalaries));
    }
}
