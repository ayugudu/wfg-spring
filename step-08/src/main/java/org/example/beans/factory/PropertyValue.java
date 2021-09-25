package org.example.beans.factory;

/**
 * 用于存储属性信息
 */
public class PropertyValue {

    private String valueName;

    private Object value;


     public PropertyValue(String valueName,Object value){
         this.valueName=valueName;
         this.value =value;
     }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
