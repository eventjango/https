package com.example.https;

import com.example.https.domain.Frodo;
import com.example.https.domain.Kevin;
import com.example.https.domain.Rings;
import com.example.https.domain.Sam;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class AssertjTests {

    private Frodo frodo = new Frodo();
    private Sam sam = new Sam();
    private Kevin kevin = new Kevin();

    private List<Object> rings = new ArrayList<Object>()
    {
        {
            this.add(frodo);
            this.add(sam);
        }
    };

    @Test
    public void load(){
        assertThat(frodo).isNotNull();
        assertThat(rings).isNotNull();
    }

    @Test
    public void basic(){
        assertThat(frodo.getName()).isEqualTo("Frodo");
        assertThat(frodo).isNotEqualTo(sam);

        assertThat(sam.getName()).startsWith("Sa").endsWith("am");
        assertThat(sam.getAge()).isBetween(20, 22);
        assertThat(sam.getAge()).isGreaterThan(10);
    }

    @Test
    public void contain(){
        assertThat(rings.contains(Arrays.asList(sam, frodo)));
        assertThat(rings).hasSize(2).doesNotContain(kevin);
    }

    @Test
    public void as(){
        assertThat(kevin.getAge()).as("check %s's age", kevin.getName()).isEqualTo(40);
    }

    @Test
    public void thrown(){

        assertThatThrownBy(() -> { throw new Exception("boom!");}).hasMessage("boom!");

        Throwable throwable = catchThrowable(() -> {throw new Exception("boom!");});
        assertThat(throwable).hasMessageContaining("boom");
    }

    @Test
    public void extracting(){

        assertThat(rings).extracting("age").contains(20);
        assertThat(rings).extracting("name").contains("Sam");
        assertThat(rings).extracting("name", "age").contains(tuple("Sam", 21), tuple("Frodo", 20));
    }

    @Test
    public void filter(){
        assertThat(rings).filteredOn(o -> ((Rings)o).getName().contains("Sam")).containsOnly(sam)
                .extracting(o -> ((Rings)o).getAge()).contains(21);
    }
}
