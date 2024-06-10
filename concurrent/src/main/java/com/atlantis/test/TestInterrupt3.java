package com.atlantis.test;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试 interrupt2
 */
@Slf4j(topic = "c.TestInterrupt3")
public class TestInterrupt3 {

        public static void main(String[] args) throws InterruptedException {
            Thread t1 = new Thread(() -> {
                while(true) {
                    boolean interrupted = Thread.currentThread().isInterrupted();
                    if(interrupted) {
                        log.debug("被打断了, 退出循环");
                        break;
                    }
                }
            }, "t1");
            t1.start();

            Thread.sleep(1000);
            log.debug("interrupt");
            t1.interrupt();
        }
    }