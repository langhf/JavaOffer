package cn.drelang.java.advanced.reflectAndPOJO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 利用反射，进行属性赋值，生成Java对象
 * Created by Drelang on 2020/05/18 18:17
 */

public class Demo {
    public static void main(String[] args) throws Exception {
        String value = "ename:Smith|job:Clerk|salary:1000|empno:333|hiredate:1995-01-21|" +
                        "dept.dname:财务部|dept.loc:上海|dept.company.name:DRE";
        Emp empObj = ClassInstanceFactory.create(Emp.class, value);
        System.out.println(empObj);
    }
}

class ClassInstanceFactory {
    private ClassInstanceFactory() {}
    public static <T> T create(Class<?> clazz, String value) {
        Object object = null;
        try {
            object = clazz.getDeclaredConstructor().newInstance();
            BeanUtils.setValue(object, value);
            return (T) object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) object;
    }
}

class StringUtils {
    public static String initCap(String str) {
        if (str == null || "".equals(str)) {
            return str;
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        } else {
            return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }
}

class BeanUtils {
    private BeanUtils() {}

    public static void setValue(Object object, String value) {
        String[] props = value.split("\\|");
        for (String prop : props) {
            String[] args = prop.split(":");
            try {
                String key = args[0];
                Object currentObject = object;
                if (args[0].contains(".")) {    // 多级配置
                    String[] temp = args[0].split("\\.");
                    // 最后一位肯定是指定类中的属性名称
                    int x = 0;
                    for (; x < temp.length - 1; x++) {
                        // 调用 getter 方法，如果 getter 方法返回 null，则进行实例化
                        Method getMothod = currentObject.getClass().getDeclaredMethod("get" + StringUtils.initCap(temp[x]));
                        Object tempObject = getMothod.invoke(currentObject);
                        if (tempObject == null) {
                            Field field = currentObject.getClass().getDeclaredField(temp[x]);
                            Method setMethod = currentObject.getClass().getDeclaredMethod("set" + StringUtils.initCap(temp[x]), field.getType());
                            Object newObject = field.getType().getDeclaredConstructor().newInstance();
                            setMethod.invoke(currentObject, newObject);
                            currentObject = newObject;
                        } else {
                            currentObject = tempObject;
                        }
                    }
                    key = temp[x];
                }

                Field field = currentObject.getClass().getDeclaredField(key);
                Method method = currentObject.getClass().getDeclaredMethod("set" + StringUtils.initCap(key), field.getType());
                Object convertValue = BeanUtils.convertAttributeValue(field.getType().getName(), args[1]);
                method.invoke(currentObject, convertValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现属性类型转换处理
     * @param type 属性类型，通过Field获取
     * @param value 属性的内容，传入的都是字符串，需要将其变为指定类型
     * @return 转换后的数据
     */
    private static Object convertAttributeValue(String type, String value) {
        if ("long".equals(type) || "java.lang.Long".equals(type)) {
            return Long.parseLong(value);
        } else if ("int".equals(type) || "java.lang.Integer".equals(type)) {
            return Integer.parseInt(value);
        } else if ("double".equals(type) || "java.lang.Double".equals(type)) {
            return Double.parseDouble(value);
        } else if ("java.util.Date".equals(type)) {
            SimpleDateFormat sdf = null;
            if (value.matches("\\d{4}-\\d{2}-\\d{2}")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else if (value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            } else {
                return new Date();
            }
            try {
                return sdf.parse(value);
            } catch (ParseException e) {
                return new Date();
            }
        } else {    // 默认是字符串
            return value;
        }
    }
}

class Company {
    private String name;
    private Date createdate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", createdate=" + createdate +
                '}';
    }
}

class Dept {
    private String dname;
    private String loc;
    private Company company;

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                ", company=" + company +
                '}';
    }
}

class Emp {
    private String ename;
    private String job;
    private double salary;
    private Long empno;
    private Date hiredate;
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Long getEmpno() {
        return empno;
    }

    public void setEmpno(Long empno) {
        this.empno = empno;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEname() {
        return ename;
    }

    public String getJob() {
        return job;
    }

    public String toString() {
        return "姓名：" + this.ename + ", "
                + "职业：" + this.job + ", "
                + "薪水：" + this.salary + ", "
                + "工号：" + this.empno + ", "
                + "雇用日期：" + this.hiredate + ", "
                + "部门：" + dept;
    }
}

