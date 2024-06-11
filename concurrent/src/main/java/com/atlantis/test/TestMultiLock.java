package com.atlantis.test;

import com.atlantis.util.Sleeper;
import lombok.extern.slf4j.Slf4j;

/**
 * 多把锁
 */
public class TestMultiLock {
    public static void main(String[] args) {
        BigRoom bigRoom = new BigRoom();
        new Thread(() -> {
            bigRoom.study();
        },"小南").start();
        new Thread(() -> {
            bigRoom.sleep();
        },"小女").start();
    }
}

// 并行
@Slf4j(topic = "c.BigRoom")
class BigRoom {
    private final Object studyRoom = new Object();
    private final Object bedRoom = new Object();

    public void sleep() {
        synchronized (bedRoom) {
            log.debug("sleeping 2 小时");
            Sleeper.sleep(2);
        }
    }
    public void study() {
        synchronized (studyRoom) {
            log.debug("study 1 小时");
            Sleeper.sleep(1);
        }
    }
}


//// 串行
//@Slf4j(topic = "c.BigRoom")
//class BigRoom {
//    public void sleep() {
//        synchronized (this) {
//            log.debug("sleeping 2 小时");
//            Sleeper.sleep(2);
//        }
//    }
//    public void study() {
//        synchronized (this) {
//            log.debug("study 1 小时");
//            Sleeper.sleep(1);
//        }
//    }
//}