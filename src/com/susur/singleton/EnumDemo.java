package com.susur.singleton;

/***
 * 枚举类型
 */
public enum EnumDemo {
    //常量，只能实例化一次，不支持懒加载
    A,B,C,D;
    public static void m1(){
        System.out.println("method");
    }
    public static void main(String[] args){
        A.m1();
        B.m1();
        C.m1();
        D.m1();
        System.out.println(A.getClass().getName());
    }
}
