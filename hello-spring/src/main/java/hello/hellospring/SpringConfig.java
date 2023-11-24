package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.SpringDataJpaMemberRepository;
import hello.hellospring.service.MemberService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final SpringDataJpaMemberRepository repository;

    public SpringConfig(SpringDataJpaMemberRepository repository) {
        this.repository = repository;
    }

    @Bean
    public MemberService memberService() {
        // TODO: 2023/11/23 함수를 호출하면 application context에 등록된 객체를 가져옴?
        // 따로 객체 생성되는 것 아님?
        return new MemberService(repository);
    }

    @Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }
}
