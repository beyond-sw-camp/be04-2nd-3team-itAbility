package com.team3.itability.blackllist.dto;


import com.team3.itability.member.dto.MemberInfoDTO;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity(name="blacklist_dto")
@Table(name="blacklist")
public class BlacklistDTO {

    @Id
    @Column(name="blacklist_id")
    private Long blacklistId;

    @Column(name="blacklist_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date blackist_date;

    @Column(name = "member_id")
    private Long memberId;

}
