package com.ys.kr.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 *  항상 프로젝트의 최상단에 위치해야만 하는 클래스
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        /**
         *  SpringApplication.run 으로 인해 내장 WAS를 실행.
         */
    }
}
