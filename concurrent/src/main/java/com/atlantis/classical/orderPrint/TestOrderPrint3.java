package com.atlantis.classical.orderPrint;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * 固定运行顺序：park/unpark 先2再1
 */
@Slf4j(topic = "c.TestOrderPrint3")
public class TestOrderPrint3 {
    public static void main(String[] args) {

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
