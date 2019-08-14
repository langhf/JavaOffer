package cn.drelang.designPattern.proxy;

/**
 * 静态代理
 * Created by Drelang on 2019/08/13 19:55
 */

public class StaticProxy {
    public static void main(String[] args) {
        RealMovie realMovie = new RealMovie();
        realMovie.play();

        Proxy proxy = new Proxy(realMovie);
        proxy.play();
    }
}

interface Movie {
    void play();
}

class RealMovie implements Movie {
    public void play() {
        System.out.println("您正在收看电影《肖申克的救赎》");
    }
}

class Proxy implements Movie {
    RealMovie realMovie;

    Proxy(RealMovie realMovie) {
        this.realMovie = realMovie;
    }

    public void play() {
        System.out.println("电影快要开始了");
        realMovie.play();
        System.out.println("电影已经结束了");
    }
}
