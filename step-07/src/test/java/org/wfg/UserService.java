package org.wfg;

import spring.beans.factory.DisposableBean;
import spring.beans.factory.InitializingBean;

import java.lang.reflect.InvocationTargetException;

public class UserService implements InitializingBean, DisposableBean {
    private String uId;
    private String company;
    private String location;
    private UserDao userDao;




    @Override
    public void destroy() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, Exception {
        System.out.println("摧毁");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("执行初始化后的扩展");
    }
    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
