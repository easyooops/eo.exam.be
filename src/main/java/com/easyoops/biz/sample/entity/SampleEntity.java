package com.easyoops.biz.sample.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@Entity(name = "sample")
public class SampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no", nullable = false)
    private Integer no;

    @ApiModelProperty(value = "title", required = true, example = "title is ...")
    @Column(name = "title", length = 256)
    private String title;

    @ApiModelProperty(value = "contents", example = "contents is ...")
    @Column(name = "contents", length = 1024)
    private String contents;

    @Column(name = "createId", length = 10, updatable = false)
    private String createId;

    @CreationTimestamp
    @Column(name = "createDt", nullable = false, updatable = false)
    private LocalDateTime createDt;

    @Column(name = "updateId", length = 10)
    private String updateId;

    @UpdateTimestamp
    @Column(name = "updateDt", nullable = false)
    private LocalDateTime updateDt;

}
