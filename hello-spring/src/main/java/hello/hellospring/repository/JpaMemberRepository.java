package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // pk 기반이 아니면 jpql 작성 필요.
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                // nullable
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // jpql select 대상이 객체 자체.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
