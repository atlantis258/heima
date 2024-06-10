package com.atlantis.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TestStartAndRun")
public class TestStartAndRun {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running...");
            }
        };

//        t1.run(); // main线程执行run任务，不会开启新线程
        t1.start(); // t1线程执行run任务，会开启新线程
    }
}