package hello.hellospring.repository;


import static org.assertj.core.api.Assertions.assertThat;

import hello.hellospring.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        repository.deleteAll();
    }

    @Test
    void save() {
        //given
        Member member = createMember("spring");

        //when
        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        //then
        // TODO: 2023/11/22 실제 jpa에서도 parameter와 영속화된 객체 주소가 같나?
        // 현재는 같은 객체이기 때문에 주소가 같아 equals로 체크 가능하지만 반환된 객체가 새롭게 만들어진 객체라면 domain에 equalsAndHashCode구현이 필요함.
        assertThat(result).isEqualTo(member);
    }

    @Test
    void findByName() {
        //given
        Member member1 = createMember("spring1");
        Member member2 = createMember("spring2");

        //when
        Member result1 = repository.findByName("spring1").get();
        Member result2 = repository.findByName("spring2").get();

        //then
        assertThat(result1).isEqualTo(member1);
        assertThat(result2).isEqualTo(member2);
    }

    @Test
    void findAll() {
        //given
        Member member1 = createMember("spring1");
        Member member2 = createMember("spring2");

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result).hasSize(2)
                .containsExactly(member1, member2);
    }

    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        repository.save(member);
        return member;
    }

}