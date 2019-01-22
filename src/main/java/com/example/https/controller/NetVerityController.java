package com.example.https.controller;

import com.example.https.domain.ResponseResult;
import com.example.https.handler.NetVerifyCallbackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(NetVerityControllers.URL)
public class NetVerityController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final NetVerifyCallbackHandler netVerifyCallbackHandler;

    @Autowired
    public NetVerityController(NetVerifyCallbackHandler netVerifyCallbackHandler) {
        this.netVerifyCallbackHandler = netVerifyCallbackHandler;
    }

    @PostMapping("accept")
    public ResponseResult acceptCallback(@RequestBody  Map<String, Object> callback){

        logger.info("[call back 정보] : ${callback}");

        ResponseResult responseResult = new ResponseResult();

        try {
            String signature = callback.get("idCheckSignature").toString();
            logger.info("signature : ".concat(signature)); // null이 떨어지지 않으면 이 로그가 찍힐것이다.

            netVerifyCallbackHandler.handleCallback(callback);
            responseResult.success = true;
        }
        catch (Exception e){

            e.printStackTrace();
            responseResult.success = false;
        }

        return responseResult;
    }
}
