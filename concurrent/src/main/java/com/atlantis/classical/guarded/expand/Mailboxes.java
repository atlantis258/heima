package com.atlantis.classical.guarded.expand;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Mailboxes {
    private static Map<Integer, GuardedObject2> boxes = new Hashtable<>();

    private static int id = 1;

    // 产生唯一 id
    private static synchronized int generateId() {
        return id++;
    }

    public static GuardedObject2 getGuardedObject2(int id) {
        return boxes.remove(id);
    }

    public static GuardedObject2 createGuardedObject2() {
        GuardedObject2 go = new GuardedObject2(generateId());
        boxes.put(go.getId(), go);
        return go;
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }
}
