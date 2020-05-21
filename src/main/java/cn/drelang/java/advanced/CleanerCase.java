package cn.drelang.java.advanced;

import java.lang.ref.Cleaner;

/**
 * Created by Drelang on 2020/05/20 23:13
 */

public class CleanerCase {

    public static void main(String[] args) throws Exception {
        try (MemberCleaning mc = new MemberCleaning()) {

        } catch (Exception e) {

        }

    }

    static class Member implements Runnable {
        public Member() {
            System.out.println("【构造】xxx");
        }

        @Override
        public void run() {
            System.out.println("【回收】xxx");
        }
    }

    static class MemberCleaning implements AutoCloseable {
        private static final Cleaner cleaner = Cleaner.create(); // 创建一个清除处理
        private Member member;
        private Cleaner.Cleanable cleanable;

        public MemberCleaning() {
            this.member = new Member();
            this.cleanable = this.cleaner.register(this, this.member);
        }
        @Override
        public void close() throws Exception {
            this.cleanable.clean(); // 启动多线程
        }
    }
}

