package cn.drelang.juc;

/**
 * leetcode-1114
 * 典型执行屏障问题
 * 题目的意思是如论线程执行的顺序是啥，都要保证实际执行顺序为 one() > two() > three()
 * 这个与保证线程执行顺序的题目有些区别。
 *
 * Created by Drelang on 2019/9/3 16:40
 */

class PrintInOrder {
    public static void main(String[] args) {
        Foo foo = new Foo();
        Thread t1 = new Thread(()->{
            foo.one(()->{
                System.out.println("one");
            });
        });

        Thread t2 = new Thread(()->{
            try {
                foo.two(()->{
                    System.out.println("two");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(()->{
            try {
                foo.three(()->{
                    System.out.println("three");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t3.start();
        t1.start();
        t2.start();
    }
}

class Foo {

    private boolean firstFinished;
    private boolean secondFinished;

    void one(Runnable printOne) {
        synchronized (this) {
            printOne.run();
            firstFinished = true;
            notifyAll();
        }
    }

    void two(Runnable printTwo) throws InterruptedException {
        synchronized (this) {
            while(!firstFinished) {
                System.out.println("two() wait.");
                wait();
            }
            printTwo.run();
            secondFinished = true;
            notifyAll();
        }
    }

    void three(Runnable printThree) throws InterruptedException {
        synchronized (this) {
            while (!secondFinished) {
                System.out.println("three() wait.");
                wait();
            }
            printThree.run();
            reset();
            notifyAll();
        }
    }

    void reset() {
        synchronized (this) {
            this.firstFinished = false;
            this.secondFinished = false;
        }
    }
}
