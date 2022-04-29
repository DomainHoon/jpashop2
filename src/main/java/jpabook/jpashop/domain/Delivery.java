package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private  Address address;

    @Enumerated(EnumType.STRING)       //TYPE ORDINAL : 1,2,3순으로 설정이되는데 중간에 값이 들가면안됨
    private DeliveryStatus status;

}
