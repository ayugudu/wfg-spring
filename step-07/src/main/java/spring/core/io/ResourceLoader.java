package spring.core.io;

/**
 * 定义加载资源
 */
public interface ResourceLoader {


    String CLASSPATH_URL_PREFIX="classpath:";

    Resource getResource(String location);


}
