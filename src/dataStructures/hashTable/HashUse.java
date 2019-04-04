package dataStructures.hashTable;

import java.util.HashMap;

public class HashUse {

    public static void main(String[] args) {

        //Declaring HashMap
        HashMap<String, Person> contacts = new HashMap<>(100, 2);
        
        Person p1 = new Person("Ana Paula", "Centeno", "anapaula@cs.rutgers.edu");
        Person p2 = new Person("Sesh", "Venugopal", "venugopal@cs.rutgers.edu");

        //Inserting into hash table
        contacts.put("anapaula@cs.rutgers.edu", p1);
        contacts.put("venugopal@cs.rutgers.edu", p2);

        //Retrieving from hash table
        System.out.println(contacts.get("venugopal@cs.rutgers.edu"));

        System.out.println("Iterating over keys");
        for(String k : contacts.keySet()){
            System.out.println(contacts.get(k));
        }

        System.out.println("Iterating over values");
        for(Person p : contacts.values()){
            System.out.println(p);
        }

    }

}
