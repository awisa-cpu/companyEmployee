package com.dena.employee;

public interface Employee {
    /**
     * Interfaces describe some or all of the public methods of a classes or group of classes.
     * An interface abstracts/hides away the details of on or more classes that all has a relationship to an interface.
     * When a class has a relationship with an interface, the class is said to implement the interface.
     * The name of your interface should reflect all the classes you are trying to hide behind the interface
     * So it should reflect a name that would be common to all the classes you want to abstract behind the interface
     * Any method defined in an interface are understood to be public by default so we don't need to specify it again.
     */

     int getSalary();

}
