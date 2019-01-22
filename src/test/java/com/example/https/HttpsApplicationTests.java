package com.example.https;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpsApplicationTests {

    @Test
    public void contextLoads() { }

    @Test
    public void upper(){
        Hello helloProxy = (Hello) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{Hello.class},
                new UppercaseHandler(new HelloTarget())
        );

        /*assertThat(helloProxy.sayHello("kevin")).isEqualTo("hello".toUpperCase());*/
        System.out.println(helloProxy.sayHello("kevin"));
    }
}


interface Hello{

    String sayHello();

    String sayHello(String name);
}

class HelloTarget implements Hello{

    @Override
    public String sayHello() {
        return "hello";
    }

    @Override
    public String sayHello(String name) {
        return "hello : " + name;
    }
}


class UppercaseHandler implements InvocationHandler{

    private Hello target;

    public UppercaseHandler(Hello hello){
        this.target = hello;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        return  (String) method.invoke(target, objects);
    }
}

