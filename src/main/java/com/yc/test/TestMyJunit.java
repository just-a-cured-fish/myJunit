package com.yc.test;

import com.yc.annotation.*;

public class TestMyJunit {
    private static Cal c;

    @BeforeClass
    public static void bc(){
        System.out.println("beforeclass----");
        c=new Cal();
    }

    @Before
    public void before(){
        System.out.println("before----");
    }

    @Test
    public void add(){
        System.out.println(c.add(1, 2));
    }

    @Test
    public void sub(){
        System.out.println(c.sub(2,1));
    }

    @After
    public void after(){
        System.out.println("after----");
    }

    @AfterClass
    public void ac(){
        System.out.println("afterclass----");
    }

}

class Cal{

    public int add(int x,int y){
        return x+y;
    }

    public int sub(int x,int y){
        return x-y;
    }
}
