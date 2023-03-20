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
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Integer examNo;

    @Column(updatable = false, length = 20, unique = true, nullable = false)
    private String pubExamNo;

    @Column(nullable = false, length = 1, columnDefinition = "Y")
    private String examOpenYn;

    @Column(nullable = false, length = 1, columnDefinition = "1")
    private String examRegTp;

    @Column(nullable = false, length = 1, columnDefinition = "N")
    private String examRndYn;

    @Column(nullable = true, length = 500)
    private String dumpFile;

    @Column(nullable = false)
    private int qstCnt;

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
