package com.atlantis.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static com.atlantis.util.Sleeper.sleep;

/**
 * 可打断特性
 */
@Slf4j(topic = "c.TestReentrantLock2")
public class TestReentrantLock2 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log.debug("启动...");
            try {
                // 如果没有竞争，那么此方法就会获取lock对象锁
                // 如果有竞争，那么就进入阻塞队列，可以被其它线程用 interrupt 方法打断
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.debug("等锁的过程中被打断");
                return;
            }
            try {
                log.debug("获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");
        lock.lock();
        log.debug("获得了锁");
        t1.start();
        try {
            sleep(1);
            t1.interrupt();
            log.debug("执行打断");
        } finally {
            lock.unlock();
        }
    }
}
