package com.dena.employee;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager implements Employee{

    private String lastName;
    private String firstName;
    private LocalDate dob;
    private int orgSize = 0;
    private int directReport = 0;

    private  final String peopleRegex = "(?<firstName>\\w+),\\s*(?<lastName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern peoplePat = Pattern.compile(peopleRegex);

    private final NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

    private final String mgrRegex = "\\w+=(?<orgSize>\\w+),\\w+=(?<dr>\\w+)";
    private final Pattern mgrPat = Pattern.compile(mgrRegex);

    private final DateTimeFormatter dtfFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    public Manager(String personText) {
        Matcher peopleMat = peoplePat.matcher(personText);
        if(peopleMat.find()) {
            this.lastName = peopleMat.group("lastName");
            this.firstName = peopleMat.group("firstName");
            this.dob = LocalDate.from(dtfFormatter.parse(peopleMat.group("dob")));

            Matcher mgrMat = mgrPat.matcher(peopleMat.group("details"));
            if(mgrMat.find()) {
                this.orgSize = Integer.parseInt(mgrMat.group("orgSize"));
                this.directReport = Integer.parseInt(mgrMat.group("dr"));
            }
        }
    }

    public int getSalary(){
        return 3500 + orgSize * directReport;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s",lastName, firstName, moneyFormat.format(getSalary()));
    }


}
