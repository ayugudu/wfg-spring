package org.example.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue pro){
         propertyValueList.add(pro);
    }


    public PropertyValue[] getPropertyValues(){

        return propertyValueList.toArray(new PropertyValue[0]);

    }


    public PropertyValue getPropertyValue(String propertyName){
        for(PropertyValue pv : this.propertyValueList){
            if(pv.getName().equals(propertyName)){
                return  pv;
            }
        }
        return  null;
    }



}
