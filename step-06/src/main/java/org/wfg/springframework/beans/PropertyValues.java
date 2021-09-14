package org.wfg.springframework.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 属性集合
 */
public class PropertyValues {

    private List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue){
        this.propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValues(){

        return  propertyValueList.toArray(new PropertyValue[0]);
    }


     public PropertyValue getPropertyValue(String propertyName){
        for(PropertyValue pro : this.propertyValueList){
            if(pro.getName().equals(propertyName)){
                return  pro;
            }
        }
        return  null;
     }



}
