package org.wfg.springframework.util;

public class ClassUtils {

   public static ClassLoader getDefaultClassLoader(){
    ClassLoader cl =null;
    try{
        //获取当前线程的类加载器
        cl =  Thread.currentThread().getContextClassLoader();
    }catch (Throwable ex){}
     if(cl == null){
         cl = ClassUtils.class.getClassLoader();
     }

    return  cl;


   }




}
