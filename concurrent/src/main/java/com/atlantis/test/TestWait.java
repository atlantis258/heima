package com.atlantis.test;

import lombok.extern.slf4j.Slf4j;

/**
 * wait
 */
@Slf4j(topic = "c.TestWait")
public class TestWait {
    static final Object lock = new Object();
    public static void main(String[] args) {

        // 线程之间进行协作的手段，wait,notify,notifyAll 都属于 Object 对象的方法。必须获得此对象的锁，才能调用这几个方法
        // 必须先加锁，才能再调用 wait()
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}