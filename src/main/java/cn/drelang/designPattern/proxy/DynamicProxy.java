package cn.drelang.designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理: 根据真实业务对象创建新的伪对象。
 * Created by Drelang on 2019/08/13 19:49
 */


public class DynamicProxy {
    public static void main(String[] args) {
        Wine wine = (Wine) new MyProxy().bind(new MaoTai());
        Wine wine1 = (Wine) new MyProxy().bind(new BaiJiu());
        Juice juice = (Juice) new MyProxy().bind(new OrangeJuice());

        wine.sell();
        wine.sell1();
        wine1.sell();
        wine1.sell1();
        juice.sale();
    }
}

// 需要用到一个实现了 InvocationHandler 的类
class MyProxy implements InvocationHandler {
    private Object target;

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public boolean connect() {
        System.out.println("销售开始 销售者：" + this.target.getClass().getSimpleName());
        return true;
    }

    public void close() {
        System.out.println("销售结束");
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res = null;
        if (this.connect()) {
            res = method.invoke(this.target, args);
            this.close();
        }
        return res;
    }
}

/* ----------------- interface 1 ------------------- */
interface Wine {
    void sell();
    void sell1();
}

class MaoTai implements Wine {
    public void sell() {
        System.out.println("我卖的是茅台酒");
    }
    public void sell1() {
        System.out.println("我卖的是茅台酒1");
    }
}

class BaiJiu implements Wine {
    public void sell() {
        System.out.println("我卖的是小白酒");
    }
    public void sell1() {
        System.out.println("我卖的是小白酒1");
    }
}

/* ----------------- interface 2 ------------------- */
interface Juice {
    void sale();
}

class OrangeJuice implements Juice {
    public void sale() {
        System.out.println("我卖的是橙汁");
    }
}

