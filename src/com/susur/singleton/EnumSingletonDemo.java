package com.susur.singleton;


/***
 * 用的最多的枚举类型
 */
public class EnumSingletonDemo {
    private EnumSingletonDemo(){

    }

    public enum EnumHolder{
        INSTANCE;
        private EnumSingletonDemo instance;
        EnumHolder(){
            this.instance = new EnumSingletonDemo();
        }
        public EnumSingletonDemo getInstacne(){
            return instance;
        }
    }

    public static EnumSingletonDemo getInstance(){
        return EnumHolder.INSTANCE.instance;
    }
}
