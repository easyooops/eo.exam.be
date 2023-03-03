package com.easyoops.biz.comn.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ComnCode {

    @Column(nullable = false, length = 10)
    private String comnGrpCd;

    @Id
    @Column(nullable = false, length = 10)
    private String comnCd;

    @Column(length = 50)
    private String comnGrpNm;

    @Column(length = 50)
    private String comnNm;

    @Column(length = 3)
    private String comnOrd;

    @Column(length = 256)
    private String comnDesc;

    @Column(nullable = false, length = 1)
    private String delYn;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false, length = 10, updatable = false)
    private String createId;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDate;

    @Column(nullable = false, length = 10)
    private String updateId;


}
