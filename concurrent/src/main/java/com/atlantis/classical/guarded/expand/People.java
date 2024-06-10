package com.atlantis.classical.guarded.expand;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.People")
public class People extends Thread {
    @Override
    public void run() {
        // 收信
        GuardedObject2 guardedObject2 = Mailboxes.createGuardedObject2();
        log.debug("开始收信 id:{}", guardedObject2.getId());
        Object mail = guardedObject2.get(5000);
        log.debug("收到信 id:{}, 内容:{}", guardedObject2.getId(), mail);
    }
}
