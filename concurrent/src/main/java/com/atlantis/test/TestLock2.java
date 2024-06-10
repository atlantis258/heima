package com.atlantis.test;

import lombok.extern.slf4j.Slf4j;

import static com.atlantis.util.Sleeper.sleep;

@Slf4j(topic = "c.TestLock2")
public class TestLock2 {

    static final Object lock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("获得锁");
                try {
//                    Thread.sleep(20000);
                    lock.wait(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();

        sleep(1);
        synchronized (lock) {
            log.debug("获得锁");
        }
    }
}
