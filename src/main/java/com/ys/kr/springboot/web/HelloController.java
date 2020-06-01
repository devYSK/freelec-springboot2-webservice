package com.ys.kr.springboot.web;

import com.ys.kr.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줌. @rESPONSEbODY를 각 메소드 마다 선언했던것을 한번에 사용할 수 있게 해줌
@RestController
public class HelloController {

    //Get의 요청을 받을수있는 API를 만들어 줌
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount ) {
        return new HelloResponseDto(name, amount);
    }

}
