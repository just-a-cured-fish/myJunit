package com.yc.reflect;

import com.yc.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JunitRunner {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //class 加载
        Class cls=Class.forName("com.yc.test.TestMyJunit");
        //1.获取这个类中所有的方法
        Method[] methods = cls.getDeclaredMethods();
        List<Method> testMethods=new ArrayList<>();//存测试方法
        Method before=null;
        Method beforeClass=null;
        Method after=null;
        Method afterClass=null;
        //2.循环方法判断上面的注解
        for (Method method : methods) {
            //3.将这些方法分别存好  @Test对应方法有多个  其他注解对应的方法直接存
            if(method.isAnnotationPresent(BeforeClass.class)){
                beforeClass=method;
            }else if(method.isAnnotationPresent(Before.class)){
                before=method;
            }else if(method.isAnnotationPresent(Test.class)){
                testMethods.add(method);
            }else if(method.isAnnotationPresent(After.class)){
                after=method;
            }else if(method.isAnnotationPresent(AfterClass.class)){
                afterClass=method;
            }
        }
        //4.按生命周期调用
        if(testMethods.size()<=0 || testMethods==null){
            throw new RuntimeException("没有测试方法");
        }
        Object o=cls.newInstance();//实例化测试类
        if(beforeClass!=null){
            beforeClass.invoke(o,null);
        }
        for (Method method : testMethods) {
            if(before!=null){
                before.invoke(o,null);
            }
            method.invoke(o,null);
            if(after!=null){
                after.invoke(o,null);
            }
        }
        if(afterClass!=null){
            afterClass.invoke(o,null);
        }
    }
}
