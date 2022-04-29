package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded           //내장타입을 포함했다. 라는 매핑을 해줌
    private Address address;

    @OneToMany(mappedBy = "member", cascade = ALL)         //종속관계형성
    private List<Order> orders = new ArrayList<>();     //컬렉션은 필드에서 바로 초기화 하는것이 안전

    //하이버네이트는 엔티티를 pesist(영속화)
}
