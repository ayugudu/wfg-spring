package org.example.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性集合定义
 */
public class PropertyValues {

  public final List<PropertyValue>  propertyValueList= new ArrayList<>();

    /**
     * 增加属性
     * @param propertyValue
     */
  public void addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }

    /**
     * 获取集合
     */
  public PropertyValue[] getPropertyValues(){
      return  propertyValueList.toArray(new PropertyValue[0]);
  }

    /**
     * 获取属性
     */

    public PropertyValue getPropertyValue(String propertyName){
        for(PropertyValue  pro : propertyValueList ){
            if(pro.getName().equals(propertyName)){
                return  pro;
            }
        }
        return  null;
    }

}
