package cn.drelang.designPattern;

/**
 * Created by Drelang on 2019/08/26 21:22
 */

public class TemplateMethod {
    public static void main(String[] args) {
        Coffee coffee = new Coffee();
        coffee.prepareRecipe();

        System.out.println();

        Tea tea = new Tea();
        tea.prepareRecipe();
    }
}

abstract class CaffeineBeverage {
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    abstract void brew();
    abstract void addCondiments();

    private void boilWater() {
        System.out.println("boilWater");
    }

    private void pourInCup() {
        System.out.println("pourInCup");
    }
}

class Coffee extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("Coffee.brew");
    }

    @Override
    void addCondiments() {
        System.out.println("Coffee.addCondiments");
    }
}

class Tea extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("Tea.brew");
    }

    @Override
    void addCondiments() {
        System.out.println("Tea.addCondiments");
    }
}