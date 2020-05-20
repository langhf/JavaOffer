package cn.drelang.java.advanced.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Drelang on 2020/05/19 11:20
 */

public class Demo {
    public static void main(String[] args) {
        /// 基本操作
        List<String> all = new ArrayList<>();
        Collections.addAll(all, "Java", "Javascript", "Json", "Python", "Ruby");
//        Stream<String> stream = all.stream();
        List<String> result = all.stream().filter((ele)->ele.toLowerCase().contains("j")).collect(Collectors.toList());
        List<String> result1 = all.stream().filter((ele)->ele.toLowerCase().contains("j")).skip(2).limit(2).collect(Collectors.toList());
        System.out.println(result);
        System.out.println(result1);

        /// MapReduce 基础模型
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("小强娃娃", 9.9, 10));
        orders.add(new Order("林若充气娃娃", 2987.9, 3));
        orders.add(new Order("不强牌笔记本电脑", 8987.9, 8));
        orders.add(new Order("若强茶杯", 2.9, 800));
        orders.add(new Order("阿强牌煎饼", 0.9, 138));
        // 分析购买商品之中带有"强"的信息数据，并且进行商品单价和数量的处理，随后分析汇总
        DoubleSummaryStatistics statistics = orders.stream().filter((ele)->ele.getName().contains("强")).mapToDouble((orderObj)->orderObj.getPrice() * orderObj.getAmount()).summaryStatistics();
        System.out.println("购买数量：" + statistics.getCount());
        System.out.println("购买总价：" + statistics.getSum());
        System.out.println("平均花费：" + statistics.getAverage());
        System.out.println("最高花费：" + statistics.getMax());
        System.out.println("最低花费：" + statistics.getMin());
    }

}

class Order {
    private String name;
    private double price;
    private int amount;

    public Order(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
