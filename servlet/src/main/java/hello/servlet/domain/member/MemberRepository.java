package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HashMap은 동시성 문제가 고려되어 있지 않으므로, 실무에서는 concurrentHahsMap, AtomicLong을 고려.
 */
public class MemberRepository {
    private Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    //싱글톤으로 만들기
    private static final MemberRepository instance = new MemberRepository();

    private MemberRepository() {
    }

    public static MemberRepository getInstance() {
        return instance;
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll() {
        //직접 store.values반환하지 않는 이유 -> 외부에서 값이 조작되어도 repository에 저장된 데이터는 변경되지 않는다.
        return new ArrayList<>(store.values());
    }

    public void deleteAll(){
        store.clear();
    }
}
