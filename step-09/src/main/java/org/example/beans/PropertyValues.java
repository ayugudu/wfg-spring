package org.example.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
  private final List<PropertyValue>  propertyValues = new ArrayList<>();


  public void addPropertyValue(PropertyValue pv){
      propertyValues.add(pv);
  }

  public PropertyValue[] getPropertyValues(){
      return  this.propertyValues.toArray(new PropertyValue[0]);
  }


  public PropertyValue getPropertyValue(String propertyName){
      for(PropertyValue pv:this.propertyValues){
          if(pv.getName().equals(propertyName)){
             return  pv;
          }
      }

  return  null;
  }





}
