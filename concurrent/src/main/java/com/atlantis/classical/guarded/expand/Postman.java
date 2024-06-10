package com.atlantis.classical.guarded.expand;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Postman")
public class Postman extends Thread {
    private int id;
    private String mail;

    public Postman(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedObject2 guardedObject2 = Mailboxes.getGuardedObject2(id);
        log.debug("送信 id:{}, 内容:{}", id, mail);
        guardedObject2.complete(mail);
    }
}
