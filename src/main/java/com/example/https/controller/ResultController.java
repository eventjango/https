package com.example.https.controller;

import com.example.https.callback.ValueTemplate;
import com.example.https.domain.Frodo;
import com.example.https.domain.Kevin;
import com.example.https.domain.ResponseResult;
import com.example.https.domain.Sam;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ResultControllers.URL)
public class ResultController implements ResultControllers{

    @GetMapping("accept")
    public String accept(){
        return "accept";
    }

    @PostMapping("accept")
    public String accept(@RequestBody Map<String, Object> input){

        return
        Arrays.asList(new Kevin(), new Sam(), new Frodo()).stream()
                .collect(Collectors.toMap(
                        o -> o.getName(),
                        o -> o,
                        (o, o2) -> o,
                        () -> new HashMap<>()
                ))
                .toString();
    }
}
