package com.atlantis.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 状态测试
 */
@Slf4j(topic = "c.TestStartAndRun")
public class TestState {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running...");
            }
        };

        log.debug(String.valueOf(t1.getState()));
        t1.start();
//        t1.start(); // 无法多次调用 start()
        log.debug(String.valueOf(t1.getState()));
    }
}
