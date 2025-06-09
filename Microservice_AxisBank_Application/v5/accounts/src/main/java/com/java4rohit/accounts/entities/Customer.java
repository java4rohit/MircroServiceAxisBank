package com.java4rohit.accounts.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    @Column(name = "customer_id")
    private Long customerId;

    private String name;

    private String email;

    @Column(name = "mobile_number")
    private  String mobileNumber;

}
