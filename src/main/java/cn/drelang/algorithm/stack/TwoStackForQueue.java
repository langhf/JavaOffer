package cn.drelang.algorithm.stack;

import java.util.Stack;

/**
 * 用两个栈组成一个队列，线程不安全版本。
 *
 * Created by Drelang on 2019/08/18 08:19
 */

public class TwoStackForQueue {

    public static void main(String[] args) {
        MyQueue1<Integer> queue1 = new MyQueue1<>();
        queue1.offer(1);
        queue1.offer(3);
        queue1.offer(5);
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
    }
}

class MyQueue1<T> {
    private Stack<T> stack1 = new Stack<>();
    private Stack<T> stack2 = new Stack<>();

    public void offer(T t) {
        stack1.push(t);
    }

    public T poll() {
        if (stack2.isEmpty()) { // 典型的 "先判断-再执行" 过程，肯定是线程不安全的
            while (!stack1.isEmpty()) {  // 此处也是 "先判断-再执行"
                stack2.push(stack1.pop());
            }
        }
        return stack2.isEmpty() ? null : stack2.pop();
    }
}

class MyQueue2<T> {
    private Stack<T> stack1 = new Stack<>();
    private Stack<T> stack2 = new Stack<>();

    public void offer(T t) {
        stack1.push(t);
    }

    public T poll() {
        synchronized (this) {
            if (stack2.isEmpty()) { // 典型的 "先判断-再执行" 过程，肯定是线程不安全的
                while (!stack1.isEmpty()) {  // 此处也是 "先判断-再执行"
                    stack2.push(stack1.pop());
                }
            }
        }
        return  stack2.isEmpty() ? null : stack2.pop();
    }
}