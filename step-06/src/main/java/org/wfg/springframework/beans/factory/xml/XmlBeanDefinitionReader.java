package org.wfg.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.wfg.springframework.beans.BeansException;
import org.wfg.springframework.beans.PropertyValue;
import org.wfg.springframework.beans.factory.config.BeanDefinition;
import org.wfg.springframework.beans.factory.config.BeanReference;
import org.wfg.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.wfg.springframework.beans.factory.support.BeanDefinitionReader;
import org.wfg.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.wfg.springframework.core.io.DefaultResourceLoader;
import org.wfg.springframework.core.io.Resource;
import org.wfg.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry){
         super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader){
        super(registry,resourceLoader);
    }


    @Override
    public void loadBeanDefinitions(Resource resource) {
       try{
           try(InputStream inputStream = resource.getInputStream()){
                doLoadBeanDefinitions(inputStream);
           } }catch (IOException | ClassNotFoundException e) {
               e.printStackTrace();
           }

    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
                for(Resource res : resources){
                    loadBeanDefinitions(res);
                }
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource= resourceLoader.getResource(location);
         loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) {
               for(String loc:locations){
                   loadBeanDefinitions(loc);
               }
    }
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes= root.getChildNodes();
        for(int i =0 ;i< childNodes.getLength();i++){
            //判断元素
            if(!(childNodes.item(i) instanceof Element)){
                continue;
            }
            //判断对象
            if(!"bean".equals(childNodes.item(i).getNodeName())) continue;
            //解析标签
            Element bean = (Element) childNodes.item(i);
            String id= bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className= bean.getAttribute("class");
            //获取class ，方便获取类中的名称
            Class<?> clazz =Class.forName(className);
            //优先级 id>name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if(StrUtil.isEmpty(beanName)){
                beanName=StrUtil.lowerFirst(clazz.getSimpleName());
            }
            //定义bean
            BeanDefinition beanDefinition =new BeanDefinition(clazz);
            //属性读取
            for(int j = 0;j<bean.getChildNodes().getLength();j++){
                if(!(bean.getChildNodes().item(j) instanceof  Element)) continue;
                if(!"property".equals(bean.getChildNodes().item(j).getNodeName()))continue;
                //解析标签： property
                Element  property = (Element) bean.getChildNodes().item(j);
                String   attrName = property.getAttribute("name");
                String   attrValue  = property.getAttribute("value");
                String    attRef = property.getAttribute("ref");
                //获取属性值，引入对象，值对象
                Object value   = StrUtil.isNotEmpty(attRef) ? new BeanReference(attRef) : attrValue;

                //创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName,value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
                if(getRegistry().containsBeanDefinition(beanName)){
                    throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
                }
            // 注册BeanDefinition
            getRegistry().registerBeanDefinition(beanName,beanDefinition);
        }

    }


}
