package com.atlantis.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试Runnable线程
 */
@Slf4j(topic = "c.com.atlantis.test.TestRunnable")
public class TestRunnable {
    public static void main(String[] args) {
        Runnable r = () -> {log.debug("running");};

        Thread t = new Thread(r, "t2");

        t.start();
    }
}
