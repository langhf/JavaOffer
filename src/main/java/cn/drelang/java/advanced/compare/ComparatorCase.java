package cn.drelang.java.advanced.compare;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Comparator 作为比较的一种挽救方式，一般情况下还是用 Comparable 好一些。
 * Created by Drelang on 2020/05/25 17:04
 */

public class ComparatorCase {
    public static void main(String[] args) {
        Person[] persons = new Person[] {
                new Person("小小", 14),
                new Person("哈哈", 11),
                new Person("嘻嘻", 20)
        };
        Arrays.sort(persons, new PersonComparator());
//        Arrays.sort(persons, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.age - o2.age;
//            }
//        });
        System.out.println(Arrays.toString(persons));
    }

    static class Person {
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    static class PersonComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.age - o2.age;
        }
    }
}

