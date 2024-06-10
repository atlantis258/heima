package com.atlantis.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试Thread线程
 */
@Slf4j(topic = "c.com.atlantis.test.TestThread")
public class TestThread {

    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                log.debug("running");
            }
        };
        t.setName("t1");
        t.start();
    }
}
