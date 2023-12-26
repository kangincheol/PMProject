package com.example.pmproject.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "productcomment")
@SequenceGenerator(sequenceName = "productComment_SEQ", name = "productComment_SEQ", allocationSize = 1)
public class ProductComment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productComment_SEQ")
    private Long productCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_name", referencedColumnName = "name")
    private Member member;

    @Column(nullable = false)
    private String content;
}
