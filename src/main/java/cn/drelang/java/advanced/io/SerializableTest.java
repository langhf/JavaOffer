package cn.drelang.java.advanced.io;

import java.io.*;

/**
 * 对象序列化和反序列化。
 * 用到 ObjectInputStream 和 ObjectOutputStream 两个类。
 * Created by Drelang on 2020/05/28 21:48
 */

public class SerializableTest {
    private static final String path = "/Users/drelang/Documents/repository/Java/offer/src/main/java/cn/drelang/java/advanced/io/files";
    private static final File file = new File(path, "drelang.person");    // 文件不存在的话，可以自动创建

    public static void main(String[] args) throws Exception {
        Person person = new Person("drelang", 18);
        System.out.println("保存对象");
        saveObject(person);
        System.out.println("读取对象：" + getObject());
    }

    public static void saveObject(Person person) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(person);
        oos.close();
    }

    public static Object getObject() throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object object = ois.readObject();
        ois.close();
        return object;
    }

    static class Person implements Serializable {
        private String name;
        private int age;

        public Person(String name, int age) {
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
}

