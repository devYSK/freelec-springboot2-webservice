package springboot.web;

import com.ys.kr.springboot.Application;
import com.ys.kr.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // JUnit에 내장된 실행자 외에 다른 실행자 실행 테스트와 Junit사이의 연결자
@WebMvcTest(controllers = HelloController.class) // Spring MVC에 집중할 수 있는 어노테이션 Controller만 사용 가능
@ContextConfiguration(classes= Application.class)
public class HelloControllerTests {


    @Autowired  // 스프링이 관리하는 Bean 주입
    private MockMvc mvc;    // 웹api 테스트 사용시 사용.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
        /**
         * ⑤ mvc.perform(get(7hello"))
         * • MockMvc를 통해 /hello 주소로 HTTP GET 요청을 합니다.
         * • 체이닝이 지원되어 아래와같이 여러 검증기능을 이어서 선언할수있습니다.
         *
         * ⑥ .andExpect(status( ).isOk())
         * • mvc.perform의결과를 검증합니다.
         * - HTTP Header의 Status를검증합니다.
         * - 우리가흔히알고 있는 200,404, 500 등의 상태를 검증합니다.
         * • 여기선 0K 즉, 200인지 아닌지를 검증합니다.
         *
         * .andExpect(content( ).string(hello))
         * • mvc.perform의 결과를 검증합니다.
         * • 응답 본문의 내용을 검증합니다.
         * • Controller에서 “hello”를 리턴하기 때문에 이 값이 맞는지 검증합니다
         */
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;
        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }


}
