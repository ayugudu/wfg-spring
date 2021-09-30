package org.example.util;

public class ClassUtils {

    public static ClassLoader getDefaultClassLoader(){
        ClassLoader cl = null;
        try{
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex){

        }
        if(cl==null){
            cl = ClassUtils.class.getClassLoader();
        }
        return  cl;



    }


    public static boolean isCglibProxyClass(Class<?> clazz){
        return  (clazz!=null && isCglibProxyClassName(clazz.getName()));
    }

    private static boolean isCglibProxyClassName(String name) {
        return (name!= null && name.contains("$$"));
    }


}
