package org.example.bean;

public class UserService {
    private String uid;
    private  UserDao userDao;
    public String queryUserInfo(){
        return userDao.queryUserName(uid);
    }


}
