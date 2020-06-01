package com.ys.kr.springboot.web.dto;

import com.ys.kr.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  Entity 클래스( Posts class) 와 유사한 형태인 Dto 클래스.
 *  Posts 클래스 대신 사용 (Posts 클래스는 도메인이기 때문)
 *  Posts 클래스를 기준으로 테이블이 생성되는데, 스키마가 변경 되기 때문
 *  Entity 클래스와 Controller에서 쓸 Dto는 분리해서 사용해야 합니다.
 */
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
