package com.team3.reportservice.blackllist.dto;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class BlacklistDTO {

    private Long blacklistId;
    private String blacklist_date;
    private Long memberId;

}
