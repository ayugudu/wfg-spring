package org.example.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("1", "王富贵");
        hashMap.put("2", "八杯水");
        hashMap.put("3", "阿毛怪");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }

}
