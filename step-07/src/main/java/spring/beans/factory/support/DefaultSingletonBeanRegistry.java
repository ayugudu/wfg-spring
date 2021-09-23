package spring.beans.factory.support;

import spring.beans.BeansException;
import spring.beans.factory.DisposableBean;
import spring.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单列缓存
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
     private Map<String,Object> singletonObjects= new ConcurrentHashMap<>();

    private final Map<String, DisposableBean> disposableBeanMap = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
       return  singletonObjects.get(beanName);
    }

    protected   void addSingleton(String beanName,Object singletonObject){
          singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName,DisposableBean bean){
        disposableBeanMap.put(beanName, bean);
    }

    public void destroySingletons(){
        Set<String> keySet = this.disposableBeanMap.keySet() ;
        Object[] disposableBeanNames = keySet.toArray();
        for(int i=disposableBeanNames.length-1;i>=0;i--){
            Object beanName =disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try{
                disposableBean.destroy();
            }catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
