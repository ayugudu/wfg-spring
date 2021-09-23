package spring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 属性集合
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValueList =  new ArrayList<>();

    public void addPropertyValue(PropertyValue pv){this.propertyValueList.add(pv);}

   public PropertyValue[] getPropertyValue(){
        return  this.propertyValueList.toArray(new PropertyValue[0]);
   }
   public PropertyValue getPropertyValue(String valueName){
        for(PropertyValue pre:propertyValueList){
            if(pre.getName().equals(valueName)){
                return  pre;
            }
        }
        return  null;
   }

}
