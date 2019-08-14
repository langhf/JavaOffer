package cn.drelang.designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * Created by Drelang on 2019/08/13 19:49
 */


public class DynamicProxy {
    public static void main(String[] args) {
        MaoTai maoTai = new MaoTai();
        BaiJiu baiJiu = new BaiJiu();
        OrangeJuice orangeJuice = new OrangeJuice();

        InvocationHandler sell1 = new Handler(maoTai);
        InvocationHandler sell2 = new Handler(baiJiu);
        InvocationHandler sell3 = new Handler(orangeJuice);

        Wine dynamicProxy1 = (Wine) Proxy.newProxyInstance(MaoTai.class.getClassLoader(), MaoTai.class.getInterfaces(), sell1);
        Wine dynamicProxy2 = (Wine) Proxy.newProxyInstance(BaiJiu.class.getClassLoader(), BaiJiu.class.getInterfaces(), sell2);
        Juice dynamicProxy3 = (Juice) Proxy.newProxyInstance(OrangeJuice.class.getClassLoader(), OrangeJuice.class.getInterfaces(), sell3);

        dynamicProxy1.sell();
        dynamicProxy2.sell();
        dynamicProxy3.sale();
    }
}

// 需要用到一个实现了 InvocationHandler 的类
class Handler implements InvocationHandler {
    private Object object;

    Handler(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("销售开始 销售者是：" + this.getClass().getSimpleName());
        method.invoke(this.object, args);
        System.out.println("销售结束");
        return null;
    }
}

/* ----------------- interface 1 ------------------- */
interface Wine {
    void sell();
}

class MaoTai implements Wine {
    public void sell() {
        System.out.println("我卖的是茅台酒");
    }
}

class BaiJiu implements Wine {
    public void sell() {
        System.out.println("我卖的是小白酒");
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

