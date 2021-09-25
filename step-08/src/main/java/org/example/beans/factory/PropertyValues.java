package org.example.beans.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义bean的属性
 */
public class PropertyValues {

    private final List<PropertyValue> list = new ArrayList<>();


    public void addPropertyValue(PropertyValue propertyValue){
        list.add(propertyValue);
    }


    public PropertyValue[] getPropertyValues(){
       return  list.toArray(new PropertyValue[0]);
    }


    public PropertyValue getPropertyValue(String propertyName){
        for(PropertyValue pro : list){
            if(propertyName.equals(pro.getValueName())){
                return  pro;
            }
        }
        return  null;
    }


}
