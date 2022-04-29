package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //영속성 컨텐츠를 더티체킹을 하지 않아 리소스의 부하 방지 jpa에서의 모든 데이터변경,로직은 트랜잭션 안에있어야한다
//AllArgsConstructor     필드의 생성자를 만들어줌
@RequiredArgsConstructor        //allargs상위호환
public class MemberService {
    private final MemberRepository memberRepository;
    @Transactional      //쓰기에는 데이터변경이 이루어지기에 readOnly 사용하지 않는다.
    //회원가입
    public Long join(Member member) {

        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());//같은이름이 있는지 확인
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }

    }

    //회원 전체조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    //회원 단건조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
