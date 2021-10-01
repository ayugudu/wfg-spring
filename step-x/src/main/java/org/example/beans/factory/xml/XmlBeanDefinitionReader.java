package org.example.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.example.beans.BeansException;
import org.example.beans.PropertyValue;
import org.example.beans.factory.config.BeanDefinition;
import org.example.beans.factory.config.BeanReference;
import org.example.beans.factory.support.AbstractBeanDefinitionReader;
import org.example.beans.factory.support.BeanDefinitionRegister;
import org.example.core.io.Resource;
import org.example.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {
    protected XmlBeanDefinitionReader(BeanDefinitionRegister register) {
        super(register);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegister register, ResourceLoader resourceLoader) {
        super(register, resourceLoader);
    }




    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for(int i =0 ;i<childNodes.getLength();i++){

            //判断元素
            if(!(childNodes.item(i) instanceof  Element)){
                continue;
            }
            //判断对象
            if(!"bean".equals(childNodes.item(i).getNodeName())){
                continue;
            }

            Element bean = (Element) childNodes.item(i);
            String id = bean.getAttribute("id");

            String name = bean.getAttribute("name");

            String className = bean.getAttribute("class");

            String initMethod = bean.getAttribute("init-method");

            String destroyMethodName = bean.getAttribute("destroy-method");

            String beanScope = bean.getAttribute("scope");


            // 获取class
            Class<?> clazz = Class.forName(className);
            // 优先级 id>name
            String beanName = StrUtil.isNotEmpty(id)?id:name;
            if(StrUtil.isEmpty(beanName)){
                beanName=StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义bena
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethodName);
            if(StrUtil.isNotEmpty(beanScope)){
                beanDefinition.setScope(beanScope);
            }

            //读取属性
            for(int j =0 ; j<bean.getChildNodes().getLength();j++){
                if(!(bean.getChildNodes().item(j) instanceof  Element)){
                    continue;
                }
                if(!"property".equals(bean.getChildNodes().item(j).getNodeName())){
                    continue;
                }

                Element property = (Element) bean.getChildNodes().item(j);
                String attrName =property.getAttribute("name");
                String attrValue =property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                //获取属性值：引入对象，值对象
                Object value  =StrUtil.isNotEmpty(attrRef)? new BeanReference(attrRef) :attrValue;

                PropertyValue propertyValue = new PropertyValue(attrName,attrValue);

                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);


            }
            if(getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
            }
            getRegistry().registerBeanDefinition(beanName,beanDefinition);

        }

    }
}
