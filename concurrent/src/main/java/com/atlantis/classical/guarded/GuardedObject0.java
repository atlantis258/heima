package com.atlantis.classical.guarded;

import com.atlantis.util.Downloader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 * 同步模式之保护性暂停
 * 线程1 等待 线程2 的结果
 */
@Slf4j(topic = "c.GuardedObject0")
public class GuardedObject0 {

    public static void main(String[] args) {
        GuardedObject0 guardedObject0 = new GuardedObject0();
        new Thread(() -> {
            // 等待结果
            log.debug("等待结果");
            List<String> list = (List<String>) guardedObject0.get();
            log.debug("结果大小是：{}", list.size());
        }, "t1").start();

        new Thread(() -> {
            log.debug("执行下载");
            try {
                List<String> list = Downloader.download();
                guardedObject0.complete(list);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    // 结果
    private Object response;

    public Object get() {
        synchronized (this) {
            while (response == null) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    // 产生结果
    public void complete(Object response) {
        synchronized (this) {
            // 给结果成员变量赋值
            this.response = response;
            this.notifyAll();
        }
    }
}
