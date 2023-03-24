package com.easyoops.biz.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Integer ansNo;

    @Column(updatable = false, length = 10)
    private Integer examNo;

    @Column(updatable = false, length = 10)
    private Integer qstNo;

    @Column(nullable = false, length = 2000)
    private String ansCont;

    @Column(nullable = false, length = 1)
    private String ansHitYn;

    @Column(length = 10, updatable = false)
    private String createId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(length = 10)
    private String updateId;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDate;

}
