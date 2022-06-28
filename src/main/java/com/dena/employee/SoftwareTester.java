package com.dena.employee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftwareTester extends Employee{

    private int loct;
    private int tw;

    String testerReg ="\\w+=(?<loct>\\w+),\\w+=(?<tW>\\w+)";
    Pattern testerPat = Pattern.compile(testerReg);

    public SoftwareTester(String personText) {
        super(personText);
        Matcher testerMat = testerPat.matcher(peopleMat.group("details"));
        if (testerMat.find()) {
            this.loct = Integer.parseInt(testerMat.group("loct"));
            this.tw = Integer.parseInt(testerMat.group("tW"));
        }
    }

    public int getSalary(){
        return loct * tw;
    }


}
