package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.SimpleHashtable;

class SimpleHashtableTest {
    public static void main(String[] args) {
        // create collection:
        SimpleHashtable<String, Integer> examMarks = new SimpleHashtable<>(2);
        // fill data:
        examMarks.put("Ivana", 2);
        System.out.println("Ivana's grade is " + examMarks.get("Ivana"));
        examMarks.put("Ante", 2);
        examMarks.put("Jasna", 2);
        examMarks.put("Kristina", 5);
        System.out.println();
        examMarks.put("Ivana", 5); // overwrites old grade for Ivana
        System.out.println(examMarks);
        System.out.println();
//        examMarks.remove("Kristina");
//        examMarks.clear();
        System.out.println(examMarks);

        // query collection:
        Integer ivanaGrade = examMarks.get("Ivana");
        System.out.println("Ivana's exam grade is: " + ivanaGrade); // writes: 5
        Integer anteGrade = examMarks.get("Ante");
        System.out.println("Ante's exam grade is: " + anteGrade); // writes: 2
        Integer jasnaGrade = examMarks.get("Jasna");
        System.out.println("Jasna's exam grade is: " + jasnaGrade); // writes: 2
        Integer kristinaGrade = examMarks.get("Kristina");
        System.out.println("Kristina's exam grade is: " + kristinaGrade); // writes: 5
        // What is collection's size? Must be four!
        System.out.println("Number of stored pairs: " + examMarks.size()); // writes: 4
        System.out.println();

        SimpleHashtable<String, Integer> s1 = new SimpleHashtable<>(1);
        s1.put("1", 2);
        s1.put("a", 1);
        s1.put("b", 2);
        s1.put("c", 2);
        System.out.println(s1);
        s1.clear();
        System.out.println(s1);
    }
}
