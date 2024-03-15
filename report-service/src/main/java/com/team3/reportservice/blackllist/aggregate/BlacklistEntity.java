package com.team3.reportservice.blackllist.aggregate;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name="blacklist")
public class BlacklistEntity {

    @Id
    @Column(name="blacklist_id")
    private Long blacklistId;

    @Column(name="blacklist_date")
    private String blacklist_date;

    @Column(name = "member_id")
    private Long memberId;

}

