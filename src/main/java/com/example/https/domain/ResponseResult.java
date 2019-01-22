package com.example.https.domain;

import lombok.Data;

@Data
public class ResponseResult {
    public Boolean success = false;
    public String trace = "";
    public String message = "";
    public Object data = new Object();
}
