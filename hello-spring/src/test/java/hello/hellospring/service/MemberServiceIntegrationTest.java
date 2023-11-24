package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    //소스코드는 생성자 주입 방식이 좋지만, 테스트 클래스는 다른 곳에서 재사용되지 않기 때문에 가장 편한 autowire사용
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

//    private MemberService memberService;
//    private MemberRepository memberRepository;
//
//    //생성자 주입 안되나? 왜 NoParametrResolver registered for parameter MemberService발생하지..
    // junit parameter resolver가 동작해 di해주기 위해서는 autowire 필수!
//    public MemberServiceIntegrationTest(MemberService memberService,
//                                        MemberRepository memberRepository) {
//        this.memberService = memberService;
//        this.memberRepository = memberRepository;
//    }


    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long memberId = memberService.join(member);

        //then
        Member foundMember = memberService.findMember(memberId).get();
        assertThat(foundMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void join_중복회원예외() {
        //given
        String duplicateName = "hello";
        createMember(duplicateName);
        Member newMember = new Member();
        newMember.setName(duplicateName);

        //when
        //만약 예외가 던져지지 않는다면 null이 반환됨.
        Throwable throwable = catchThrowable(() -> memberService.join(newMember));

        //then
        assertThat(throwable).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
        //given
        Long memberId1 = createMember("member1");
        Long memberId2 = createMember("member2");

        //when
        List<Member> members = memberService.findMembers();

        //then
        assertThat(members).hasSize(2);
    }

    @Test
    void findMember() {
        //given
        Long memberId1 = createMember("member1");
        Long memberId2 = createMember("member2");

        //when
        Optional<Member> member = memberService.findMember(memberId1);

        //then
        assertThat(member).isNotNull();
        assertThat(member.get().getId()).isEqualTo(memberId1);
        assertThat(member.get().getName()).isEqualTo("member1");
    }

    private Long createMember(String name) {
        Member member = new Member();
        member.setName(name);
        return memberService.join(member);
    }


}