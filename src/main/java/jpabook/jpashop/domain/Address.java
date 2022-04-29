package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable             //어딘가에 내장될수 있다.
@Getter                 //값타입 SETTER를 어노테이션하면 변경이가능하다. 그러므로 게터만 제공
//setter를 사용할경우 변경포인트가 너무 많아서,유지보수가 어렵다.
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
        //jpa스펙상 entity나 임베디드 타입은 자바 기본 생성자를 public 또는 protected로 한다.
        //허나 public 보다는 protected로하는 것이 그나마 안전하다.
    }
    //생성자를 통해 값을 모두 초기화를 하여 값을 변경 불가능한 클래스를 만든다.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
