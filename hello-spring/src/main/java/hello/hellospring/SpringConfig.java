package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(DataSource dataSource) {
        // TODO: 2023/11/23 함수를 호출하면 application context에 등록된 객체를 가져옴?
        // 따로 객체 생성되는 것 아님?
        return new MemberService(memberRepository(dataSource));
    }

    @Bean
    public MemberRepository memberRepository(DataSource dataSource) {
        //여기만 바꾸어주면 repository 교체됨.
        return new JdbcMemberRepository(dataSource);
    }
}
