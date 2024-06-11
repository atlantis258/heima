package com.atlantis.classical.orderPrint;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * reentrantLock 方法实现 先2再1
 */
@Slf4j(topic = "c.TestOrderPrint2")
public class TestOrderPrint2 {
    public static void main(String[] args) {

        // todo
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("1");
        }, "t1");
        t1.start();

        new Thread(() -> {
            log.debug("2");
            LockSupport.unpark(t1);
        },"t2").start();
    }
}
