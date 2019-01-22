package com.example.https;

import com.example.https.callback.ValueTemplate;
import com.example.https.domain.Frodo;
import com.example.https.domain.Kevin;
import com.example.https.domain.Sam;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CallbackTests {

    @Test
    public void load(){ }

    @Test
    public void getCallback(){

        assertThat(new ValueTemplate(new Kevin())).isNotEqualTo(null);

        new ValueTemplate(new Kevin()).getName();
        new ValueTemplate(new Frodo()).getAge();
        new ValueTemplate(new Kevin()).compareToAge(new Sam());
    }

    @Test
    public void setCallback(){

        new ValueTemplate(new Kevin()).setName("KEVIN");
        new ValueTemplate(new Sam()).setAge(11);
    }

    @Test
    public void getAndSet(){

        ValueTemplate valueTemplate = new ValueTemplate(new Kevin());
        valueTemplate.setName("KEVIN");
        valueTemplate.setAge(46);
    }

}
