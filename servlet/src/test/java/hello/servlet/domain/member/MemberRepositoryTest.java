package hello.servlet.domain.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
    }

    @Test
    void save() {
        //given
        Member member = new Member("kim", 20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member foundMember = memberRepository.findById(savedMember.getId());
        assertThat(foundMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        memberRepository.save(member1);
        Member member2 = new Member("member2", 30);
        memberRepository.save(member2);

        //when
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(members)
                .hasSize(2)
                .contains(member1, member2);
    }
}