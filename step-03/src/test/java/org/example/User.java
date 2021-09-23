package org.example;

public class User{
     int age ;
     String name ;
/*
    public User(String name){
      this.name=name;
  }*/

    public User(int age){
        this.age=age;
    }

    public void test(){
        System.out.println("你好! "+age+name);
    }

}
