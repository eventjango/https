package com.example.https;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VisitorTests {


    @Test
    public void visitTest(){

        Visitor elementVisitor = new ElementVisitor();
        elementVisitor.visit(new Element());
    }
}

class ElementVisitor implements Visitor{

    @Override
    public void visit(Acceptable acceptable) {

        new AcceptableTypeCaster().cast(acceptable, Element.class).accept(this);
    }
}

class Element implements Acceptable{

    @Override
    public void accept(Visitor visitor) {
        System.out.println("방문자 : " + visitor);
    }
}
class AcceptableTypeCaster{
    private TypeCaster typeCaster = (acceptable, object) -> acceptable;

    public <T> T cast(Acceptable acceptable, Class<T> expectType){
        T result = null;
        try{
            result = (T) typeCaster.apply(acceptable, expectType);
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }

        return result;
    }
}

interface TypeCaster<T>{
    T apply(Acceptable acceptable, Class<T> object);
}

interface Acceptable{
    void accept(Visitor visitor);
}

interface Visitor{
    void visit(Acceptable acceptable);
}
