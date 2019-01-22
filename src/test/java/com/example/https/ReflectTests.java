package com.example.https;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.lang.reflect.Method;

@SpringBootTest
public class ReflectTests {

    @Test
    public void load(){

        try {
            Method length = String.class.getMethod("length");
            System.out.println(length.invoke("kevin"));
            System.out.println(length);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
