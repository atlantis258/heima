package com.atlantis.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试多线程
 */
@Slf4j(topic = "c.com.atlantis.test.TestMultiThread")
public class TestMultiThread {

    public static void main(String[] args) {
        new Thread(() -> {
            while(true) {
                log.debug("running");
            }
        },"t1").start();
        new Thread(() -> {
            while(true) {
                log.debug("running");
            }
        },"t2").start();
    }
}
