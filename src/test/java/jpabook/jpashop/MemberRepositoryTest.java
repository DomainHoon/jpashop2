package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional          //트랜잭션이 test내부에 있으면 끝난후 롤백을 한다. 엔티티매니저에 의한 변화는 모두 트랜잭션에 의해 실행되어야 한다.
    @Rollback(false)
    public void testMember() throws Exception{
        Member member = new Member();
        member.setName("memberA");
        //Long save = memberRepository.save(member);
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    //H2 1.4.200 버전부터 MVCC 옵션이 제거되었습니다. 그래서 이제는 제거하는 것이 맞습니다.
        Assertions.assertThat(findMember).isEqualTo(member);        //true 같은 영속성안에서는 식별자를 같은 것으로 인식
        
    }


}