package org.example;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    private static Map<String,String> hashMap =new HashMap<>();
    static{
        hashMap.put("1","w");
        hashMap.put("2","f");
    }
    public String queryUserName(String uid){
        return hashMap.get(uid); }
}
