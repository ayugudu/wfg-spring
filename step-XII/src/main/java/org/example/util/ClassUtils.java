package org.example.util;

public class ClassUtils {

    public static ClassLoader getDefaultClassLoader(){
        ClassLoader cl = null;

        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    /**
     * 检查指定的类是否是cglib生成的类
     */

    public static  boolean isCglibProxyClass(Class<?> clazz){
        return (clazz != null && isCglibProxyClassName(clazz.getName()));

    }

   public static boolean isCglibProxyClassName(String className){
        return  (className!=null && className.contains("$$"));
   }



}
