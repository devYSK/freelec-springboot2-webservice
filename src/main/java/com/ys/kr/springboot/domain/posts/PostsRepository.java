package com.ys.kr.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Entity 클래스와 기본 Repository는 함께 움직여야 하므로 도메인 패키지에서 함께 관리합니다.
public interface PostsRepository extends JpaRepository<Posts, Long> { // < Entity 클래스, PK 타입 >

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
