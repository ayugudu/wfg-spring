package org.wfg.springframework;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性的集合
 */
public class PropertyValues {

 private  final List<PropertyValue> propertyValueList = new ArrayList<>();

 public void addPropertyValue(PropertyValue propertyValue){
     this.propertyValueList.add(propertyValue);
 }

 public PropertyValue[] getPropertyValues(){
     return propertyValueList.toArray(new PropertyValue[0]);
 }

 public PropertyValue getPropertyValue(String propertyName){
     for(PropertyValue pv: this.propertyValueList){
         if(pv.getName().equals(propertyName)){
             return  pv;
         }
     }
     return  null;
 }


}
