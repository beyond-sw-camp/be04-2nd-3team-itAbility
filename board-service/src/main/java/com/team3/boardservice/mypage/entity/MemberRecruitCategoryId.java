package com.team3.boardservice.mypage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
@ToString
@EqualsAndHashCode
public class MemberRecruitCategoryId implements Serializable {
    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "recruit_category_id")
    private Integer recruitCategoryId;

}
