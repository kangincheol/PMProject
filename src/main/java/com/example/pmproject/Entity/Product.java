package com.example.pmproject.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "product")
@SequenceGenerator(sequenceName = "product_SEQ", name = "product_SEQ", allocationSize = 1)
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_SEQ")
    private Long productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer price;

    @Column
    private String img;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    List<ProductComment> productCommentList;
}
