package hello.springmvc.example.v1.repository;

import hello.springmvc.example.v1.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>(); //서로 다른 Repository 객체가 공유하게 된다.
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        List<Member> all = findAll();
        for(Member m : all) {
            if(m.getLoginId().equals(loginId)) {
                return Optional.of(m);
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
