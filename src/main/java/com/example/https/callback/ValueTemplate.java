package com.example.https.callback;

import com.example.https.domain.Rings;
import com.example.https.domain.Sam;

public final class ValueTemplate {

    private Rings target;

    public ValueTemplate(Rings rings){
        this.target = rings;
    }

    public final void getName(){

        handleGetCallback(

                new GetCallback<String>() {
                    @Override
                    public String getValue(Rings rings) {
                        return rings.getName();
                    }
                }
        );
    }

    public final void getAge(){

        handleGetCallback(new GetCallback<Integer>() {

            @Override
            public Integer getValue(Rings rings) {
                return rings.getAge();
            }
        });
    }


    public final void compareToAge(Sam sam) {

        handleGetCallback(new GetCallback<Integer>() {

            @Override
            public Integer getValue(Rings rings) {
                return (rings.getAge() >= sam.getAge())? sam.getAge() : rings.getAge();
            }
        });
    }

    public final void setName(String name) {

        handleSetCallback(
                () -> {
                    target.setName(name);
                    return target;
                }
        );

    }

    public final void setAge(int age) {

        handleSetCallback(
                () -> {
                    target.setAge(age);
                    return target;
                }
        );
    }

    //template
    private <T> void handleGetCallback(GetCallback<T> getCallback){

        Thread thread =
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        T result = getCallback.getValue(target);
                        System.out.println(result);
                    }
                }
        );

        try {
            synchronized (this){
                Thread.sleep(3000);
                thread.start();
            }
        }
        catch (InterruptedException e){

            e.printStackTrace();
        }

    }


    //template
    private void handleSetCallback(SetCallback setCallback){

        Thread thread =
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                Rings result = setCallback.setValue();
                                System.out.println(result);
                            }
                        }
                );

        try{

            synchronized (this){
                Thread.sleep(3000);
                thread.start();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
