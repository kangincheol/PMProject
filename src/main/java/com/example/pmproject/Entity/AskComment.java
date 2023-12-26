package com.example.pmproject.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "askComment")
@SequenceGenerator(sequenceName = "askComment_SEQ", name = "askComment_SEQ", allocationSize = 1)
public class AskComment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "askComment_SEQ")
    private Long askCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ask_id")
    private Ask ask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_name", referencedColumnName = "name")
    private Member member;

    @Column(nullable = false)
    private String content;
}
