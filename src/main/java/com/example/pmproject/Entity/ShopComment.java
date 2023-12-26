package com.example.pmproject.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "shopcomment")
@SequenceGenerator(sequenceName = "shopComment_SEQ", name = "shopComment_SEQ", allocationSize = 1)
public class ShopComment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shopComment_SEQ")
    private Long shopCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shop_id")
    private Shop shop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_name", referencedColumnName = "name")
    private Member member;

    @Column(nullable = false)
    private String content;
}
