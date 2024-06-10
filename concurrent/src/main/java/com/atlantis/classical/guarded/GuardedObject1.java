package com.atlantis.classical.guarded;

import lombok.extern.slf4j.Slf4j;

import static com.atlantis.util.Sleeper.sleep;

// 增加超时效果
@Slf4j(topic = "c.GuardedObject1")
public class GuardedObject1 {
    public static void main(String[] args) {
        GuardedObject1 guardedObject1 = new GuardedObject1();
        new Thread(() -> {
            log.debug("begin");
            Object response = guardedObject1.get(2000);
            log.debug("结果是：{}", response);
        }).start();

        new Thread(() -> {
            log.debug("begin");
            sleep(1);
            guardedObject1.complete(new Object());
        }).start();
    }

    // 结果
    private Object response;

    // 获取结果
    // timeout 表示要等待多久 2000
    public Object get(long timeout) {
        synchronized (this) {
            // 开始时间 15:00:00
            long begin = System.currentTimeMillis();
            // 经历的时间
            long passedTime = 0;
            while (response == null) {
                // 这一轮循环应该等待的时间
                long waitTime = timeout - passedTime;
                // 经历的时间超过了最大等待时间时，退出循环
                if (timeout - passedTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime); // 虚假唤醒 15:00:01
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 求得经历时间
                passedTime = System.currentTimeMillis() - begin; // 15:00:02  1s
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
