package com.atlantis.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试daemon
 */
@Slf4j(topic = "c.TestDaemon")
public class TestDaemon {
        public static void main(String[] args) throws InterruptedException {
            Thread t1 = new Thread(() -> {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        break;
                    }
                }
                log.debug("结束");
            }, "t1");
            t1.setDaemon(true); // 其它非守护线程运行结束了，即使守护线程的代码没有执行完，也会强制结束。
            t1.start();

            Thread.sleep(1000);
            log.debug("结束");
        }
    }
