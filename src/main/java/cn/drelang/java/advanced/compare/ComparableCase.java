package cn.drelang.java.advanced.compare;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * Created by Drelang on 2020/05/25 11:59
 */

public class ComparableCase {
    public static void main(String[] args) {
        Person[] persons = new Person[] {
                new Person("小明", 15),
                new Person("小黑", 20),
                new Person("小花", 11),
        };
        Arrays.sort(persons);   // 排序依靠的是 Comparable 接口
        System.out.println(Arrays.toString(persons));

    }

    static class Person implements Comparable<Person> {
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

        @Override
        public int compareTo(@NotNull Person per) {
            return this.age - per.age;  // 升序。降序反过来就行。
        }
    }
}

