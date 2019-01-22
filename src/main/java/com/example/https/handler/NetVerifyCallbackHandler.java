package com.example.https.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class NetVerifyCallbackHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void handleCallback(Map<String,Object> callback) {

        if(Objects.isNull(callback)) throw new RuntimeException("Callback Data가 존재하지 않습니다");

        try{

            callback.forEach((key, value) -> logger.info(key + " == " + value)); // 일단 도대체 뭐가 들어오는지 Data를 전부 나열해보자
        }
        catch (Exception e){

            throw new RuntimeException("Callback Data의 정보를 순회하다가 문제가 생겼습니다");
        }
    }
}
