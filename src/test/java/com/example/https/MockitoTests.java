package com.example.https;

import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class MockitoTests {

    @Test
    public void listMock(){
        LinkedList mockList = mock(LinkedList.class);

        when(mockList.get(0)).thenReturn("first");
        when(mockList.get(1)).thenReturn(new RuntimeException());


        System.out.println(mockList.get(1));

        verify(mockList).get(0);
    }
}
