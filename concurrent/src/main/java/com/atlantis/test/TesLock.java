package com.atlantis.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.TesLock")
public class TesLock {
    static final Object lock = new Object();
    public static void main(String[] args) {

        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
