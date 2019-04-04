package dataStructures.hashTable;

import java.util.HashMap;

public class Person {

    String firstName, lastName;
    String email;

    public Person(String fN, String lN, String email){
        firstName = fN;
        lastName = lN;
        this.email = email;
    }

    public boolean equal(Object o){
        if(o == null || !(o instanceof Person)){
            return false;
        }
        Person other = (Person)o;
        return other.email.equals(email);
    }

    @Override
    public String toString() {
        return "[" + firstName + ", " + lastName + "] - " + email;
    }
}
