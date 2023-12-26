package com.example.pmproject.Entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime regDate;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime modDate;
}
