package org.example.bean;

import java.util.HashMap;
import java.util.Map;

public class UserDao {

    private static Map<String,String> hashMap = new HashMap<>();
    static {
        hashMap.put("1","w");
        hashMap.put("2","s");
    }
    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
