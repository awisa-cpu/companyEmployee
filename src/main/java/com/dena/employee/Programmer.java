package com.dena.employee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee implements IEmployee {

    private int linesOfCode = 0;
    private int yearsOfExp = 0;
    private int iq =0;


    private final String programmerRegex = "\\w+=(?<loc>\\w+),\\w+=(?<yoe>\\w+),\\w+=(?<iq>\\w+)";
    private final Pattern programmerPat = Pattern.compile(programmerRegex);

    public Programmer(String personText) {
    super(personText);
        Matcher programmerMat = programmerPat.matcher(peopleMat.group("details"));
            if(programmerMat.find()) {
            this.linesOfCode = Integer.parseInt(programmerMat.group("loc"));
            this.yearsOfExp = Integer.parseInt(programmerMat.group("yoe"));
            this.iq = Integer.parseInt(programmerMat.group("iq"));
        }

    }

    public int getSalary(){
        return 3000 + linesOfCode * yearsOfExp * iq;
    }



}
